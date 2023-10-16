package pe.edu.ulima.pm20232.aulavirtual.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import pe.edu.ulima.pm20232.aulavirtual.R
import pe.edu.ulima.pm20232.aulavirtual.components.ButtonWithIcon
import pe.edu.ulima.pm20232.aulavirtual.components.TextFieldWithLeadingIcon
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.LoginScreenViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.RegisterViewModel
import pe.edu.ulima.pm20232.aulavirtual.screenmodels.ResetPasswordViewModel
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray800
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Orange400
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.White400

@Composable
fun BackR(navController:NavHostController) {
   Box(
      modifier = Modifier
         .padding(1.dp)
         .size(50.dp)
   ) {
      IconButton(
         onClick = {
            navController.navigate("login")
         },
         modifier = Modifier.fillMaxSize()
      ) {
         Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = if(isSystemInDarkTheme()) Color.White else Color.Black
         )
      }
   }
}

@Composable
fun TopScreenR() {
   Box(
      modifier = Modifier
         .fillMaxWidth()
         .background(if (isSystemInDarkTheme()) Color.Black else Gray1200),
      contentAlignment = Alignment.TopCenter
   ) {
      Column(
         modifier = Modifier
            .padding(top = 1.dp)
            .fillMaxWidth(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         val paddingPercentage = 1
         val paddingValue = with(LocalDensity.current) {
            (paddingPercentage * 0.02f * 2.dp.toPx()).dp
         }
         Image(
            painter = painterResource(id = R.drawable.ic_ulima),
            contentDescription = "Universidad de Lima",
            modifier = Modifier.size(70.dp),
            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.White else Orange400)
         )

      }
   }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterR(screenWidthDp: Int, screenHeightDp: Int, viewModel: RegisterViewModel, navController: NavHostController) {
   var termsDisabled = true

   Box(
      modifier = Modifier
         .fillMaxSize()
         .padding(top = (screenHeightDp * (0.01)).dp)
         .background(if (isSystemInDarkTheme()) Color.Black else Gray1200),
   ) {
      Box(
         modifier = Modifier
            .fillMaxSize()
            .padding(
               start = (screenWidthDp * 0.1).dp,
               top = (1.dp),
               end = (screenWidthDp * 0.1).dp,
               bottom = (40.dp)
            ),
      ) {
         Box(
            modifier = Modifier
               .fillMaxSize()
               .border(1.dp, Gray800)
               .background(if (isSystemInDarkTheme()) Gray1200 else White400)
               .padding(20.dp)
         ) {
            Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               Text(text = "CREAR CUENTA", fontSize = 15.sp, color = Color.DarkGray)
               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Person,
                  placeholder = "Nombres",
                  text = viewModel.Nombre,
                  onTextChanged = {
                     viewModel.Nombre = it
                  }
               )
               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Person,
                  text = viewModel.Apellidos,
                  placeholder = "Apellidos",
                  onTextChanged = {
                     viewModel.Apellidos = it
                  }
               )

               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.AccountBox,
                  text = viewModel.DNI,
                  placeholder = "DNI",
                  onTextChanged = {
                     viewModel.DNI = it
                  }
               )
               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Email,
                  text = viewModel.Correo,
                  placeholder = "Correo",
                  onTextChanged = {
                     viewModel.Correo = it
                  }
               )

               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Phone,
                  text = viewModel.Telefono,
                  placeholder = "Telefono",
                  onTextChanged = {
                     viewModel.Telefono = it
                  }
               )

               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Lock,
                  text = viewModel.Contraseña,
                  placeholder = "Contraseña",
                  onTextChanged = {
                     viewModel.Contraseña = it
                  }
               )

               TextFieldWithLeadingIcon(
                  leadingIcon = Icons.Default.Lock,
                  text = viewModel.Repetir,
                  placeholder = "Repetir Contraseña",
                  onTextChanged = {
                     viewModel.Repetir = it
                  }
               )

               Text(viewModel.message)
               Row(
                  modifier = Modifier
                     .fillMaxWidth()
                     .padding(top = 2.dp),
                  horizontalArrangement = Arrangement.Center,
               ) {
                  ButtonWithIcon("CREAR CUENTA", Icons.Default.Person, {
                     viewModel.access(navController)
                  })
               }
            }
         }
      }
   }
}


@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navController: NavHostController) {
   val configuration = LocalConfiguration.current
   val screenWidthDp = configuration.screenWidthDp
   val screenHeightDp = configuration.screenHeightDp

   Column(
      modifier = Modifier
         .fillMaxSize()
         .background(if (isSystemInDarkTheme()) Color.Black else Gray1200)
   ) {
      BackR(navController)
      TopScreenR()
      RegisterR(screenWidthDp, screenHeightDp, viewModel, navController)
   }
}

