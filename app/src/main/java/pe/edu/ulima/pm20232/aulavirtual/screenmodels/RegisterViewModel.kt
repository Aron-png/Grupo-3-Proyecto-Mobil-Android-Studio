package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import pe.edu.ulima.pm20232.aulavirtual.models.User
import pe.edu.ulima.pm20232.aulavirtual.services.MemberService
import pe.edu.ulima.pm20232.aulavirtual.services.UserService

class RegisterViewModel: ViewModel() {
    var Nombre:String by mutableStateOf("")
    var Apellidos:String by mutableStateOf("")
    var DNI:String by mutableStateOf("")
    var Correo:String by mutableStateOf("")
    var Telefono:String by mutableStateOf("")
    var Contraseña:String by mutableStateOf("")
    var Repetir:String by mutableStateOf("")
    var message:String by mutableStateOf("")

    fun access(navController: NavController): Unit{
        println("BTN PRESSED")
        println(Nombre)
        println(Apellidos)
        println(DNI)
        println(Correo)
        println(Telefono)
        println(Contraseña)
        println(Repetir)

        val memberservice= MemberService()
        val UserId=memberservice.adduser(Nombre,Apellidos, DNI, Correo , Telefono,Contraseña)

        if(UserId){
            message = "Usuario Registrado"
            navController.navigate("login")
            Nombre=""
            Apellidos=""
            DNI=""
            Correo=""
            Telefono=""
            Contraseña=""
            Repetir=""
            message=""

        }
        else{
            message="Ocurrio un error"
        }


    }
}