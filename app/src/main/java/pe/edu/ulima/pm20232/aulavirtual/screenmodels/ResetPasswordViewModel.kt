package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import pe.edu.ulima.pm20232.aulavirtual.services.UserService

class ResetPasswordViewModel: ViewModel() {
    var DNI:String by mutableStateOf("")
    var Correo:String by mutableStateOf("")
    var message:String by mutableStateOf("")


    fun access(navController: NavController): Unit{
        println("BTN PRESSED")
        println(DNI)
        println(Correo)
        navController.navigate("login")
        message = "Contraseña cambiadda"
    }
}