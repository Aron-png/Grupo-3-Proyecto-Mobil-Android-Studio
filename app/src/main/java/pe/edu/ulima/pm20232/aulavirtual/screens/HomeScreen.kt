package pe.edu.ulima.pm20232.aulavirtual.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import pe.edu.ulima.pm20232.aulavirtual.models.Pokemon
import pe.edu.ulima.pm20232.aulavirtual.services.PokemonService
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.HomeScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200

var routine=false

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExercisesGrid(navController: NavController, model: HomeScreenViewModel){
    var intValue by remember { mutableStateOf(0) }
    //val pokemons by model.pokemons.collectAsState()
    val exercises by model.exercises.collectAsState()
    var ShowDetailExercise by remember { mutableStateOf(false) }
    var ShowDetailRoutine by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4) // Specify the number of columns
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
                            Log.d("BB",exercises[intValue].videoUrl.split("watch?v=")[0])
                            intValue = exercises[i].id.toInt() - exercises[0].id.toInt()
                            if (routine) {
                                ShowDetailRoutine = true
                            } else {
                                ShowDetailExercise = true
                            }
                            //navController.navigate("pokemon/edit?pokemon_id=${intValue}")
                        },
                )
                Text(exercises[i].name)
            }
        }
    }

    if (ShowDetailExercise) {

        AlertDialog(
            onDismissRequest = {
                ShowDetailExercise = false
            },

            title = {
                Text(
                    text = exercises[intValue].name,
                    color = Color.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            },
            text={
                Column(modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(Color.White, shape = RectangleShape)
                    .padding(16.dp)){

                    Text(
                        text = "exercise id : ${exercises[intValue].description}",
                    )
                    Row(){
                        /*val context = LocalContext.current
                        val exoPlayer = remember {
                            ExoPlayer.Builder(context).build().apply {
                                setMediaItem(MediaItem.fromUri(Uri.parse(exercises[intValue].videoUrl)))
                                prepare()
                            }
                        }
                        val isPlaying = exoPlayer.isPlaying ?: false

                        AndroidView(
                            factory = { context ->
                                PlayerView(context).apply {
                                    player = exoPlayer
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )

                        IconButton(
                            onClick = {
                                if (isPlaying) {
                                    exoPlayer.pause()
                                } else {
                                    exoPlayer.play()
                                }
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Bottom)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Filled.KeyboardArrowRight else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                        }*/
                        YoutubePlayer(youtubeVideoId = exercises[intValue].videoUrl.split("watch?v=")[1], lifecycleOwner = LocalLifecycleOwner.current)
                    }
                }
            },


            confirmButton = {

            },
            dismissButton = {

            }
        )
    }

    if(ShowDetailRoutine){
        //Insertar codigo para ventana al hacer click en rutina
    }
}

@Composable
fun SelectOpitions(model: HomeScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    // val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        Modifier.padding(bottom = 20.dp)
    ) {
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
fun HomeScreen(navController: NavController, model: HomeScreenViewModel, Routine: Boolean){
    routine=Routine
    model.listAll()
    model.getGenerations()
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

@Composable
fun YoutubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
) {

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        })

}