package pe.edu.ulima.pm20232.aulavirtual.screenmodels

import android.app.Notification
import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.ulima.pm20232.aulavirtual.configs.BackendClient
import pe.edu.ulima.pm20232.aulavirtual.models.Generation
import pe.edu.ulima.pm20232.aulavirtual.services.GenerationService
import pe.edu.ulima.pm20232.aulavirtual.services.UserService



class LoginScreenViewModel: ViewModel() {
    var user: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var message: String by mutableStateOf("")
    var bottomSheetCollapse: Boolean by mutableStateOf(true)
    var termsAndConditionsChecked: Boolean by mutableStateOf(false)

    private val coroutine: CoroutineScope = viewModelScope

    fun access(navController: NavController): Unit{
        println("BTN PRESSED")
        println(user)
        println(password)

        val userservice=UserService()

        val userId=userservice.checkUser(user,password)

        if (userId != 0) {
            println("Usuario válido. ID: $userId")
            message="Bienvenido"
            viewModelScope.launch {
                delay(1000)
                user=""
                password=""
                message=""
                navController.navigate("home?user_id=${userId}")
            }
        } else {

            message = "Usuario o contraseña incorrectos"
            user=""
            password=""
            viewModelScope.launch {
                delay(1000)
                message = ""
            }

        }



        /*coroutine.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = userService.findOne(user, password)?.execute()
                    if (response != null) {
                        if (response.body() != 1) {
                            val userId = response.body()!!
                            println("userId: " + userId)
                        } else {
                            // Maneja errores
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {

            }
        }*/
        /*
        if(user == "admin" && password == "123"){
            navController.navigate("profile")
        }else{
            val userService: UserService = UserService()
            val userId = userService.checkUser(user, password)
            if(userId != 0){
                navController.navigate("home?user_id=${userId}")
            }else{
                message = "Usuario y contraseña no coinciden"
            }
        }
         */
    }


}
