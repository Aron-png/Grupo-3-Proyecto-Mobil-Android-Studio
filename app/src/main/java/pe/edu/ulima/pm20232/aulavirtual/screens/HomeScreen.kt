package pe.edu.ulima.pm20232.aulavirtual.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.services.ExerciseMemberService
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExercisesGrid(navController: NavController, model: HomeScreenViewModel){
    var intValue by remember { mutableStateOf(0) }
    val exercises by model.exercises.collectAsState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(exercises.size) { i ->
            Column(){
                println(exercises[i].imageUrl)
                Image(
                    painter = rememberImagePainter(data = exercises[i].imageUrl),
                    contentDescription = exercises[i].name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            intValue = exercises[i].id.toInt()
                            //navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                        },
                )
                Text(exercises[i].name)
            }
        }
    }
}

@Composable
fun SelectOpitions(model: HomeScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    // val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val exerciseMemberService = ExerciseMemberService()
    val memberId = model.userId
    val assignedExerciseCount = exerciseMemberService.getExerciseCountForMember(memberId)
    val trainedBodyParts = exerciseMemberService.countUniqueBodyPartIds(memberId)

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        Modifier.padding(bottom = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = assignedExerciseCount.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 54.sp,
                )
                Text(
                    text = "Ejercicios Asignados",
                )
            }
            Column (
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = trainedBodyParts.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 54.sp,
                )
                Text(
                    text = "Partes del Cuerpo \nEntrenadas",
                    textAlign = TextAlign.Center,
                )
            }
        }
        Divider()
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = {Text("Lista de Partes del Cuerpo")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledLabelColor = Gray1200, // Change the label color when disabled
                disabledBorderColor = Gray1200, // Change the border color when disabled
                disabledTextColor = Gray1200
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            for ((key, value) in model.bodyPartsMap) {
                DropdownMenuItem(onClick = {
                    model.filterByBodyParts(key)
                    selectedText = value
                    expanded = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}
@Composable
@SuppressLint("StateFlowValueCalledInComposition")
fun HomeScreen(navController: NavController, model: HomeScreenViewModel) {
    model.getBodyParts()
    model.listAllExercises()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        SelectOpitions(model)
        ExercisesGrid(navController, model)
    }
}