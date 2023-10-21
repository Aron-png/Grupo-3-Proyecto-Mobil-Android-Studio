package pe.edu.ulima.pm20232.aulavirtual.components

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pe.edu.ulima.pm20232.aulavirtual.PrActivity
import pe.edu.ulima.pm20232.aulavirtual.configs.TopBarScreen

@Composable
fun TopNavigationBar(navController: NavController, screens: List<TopBarScreen>, userId:Int) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val launcherActivity = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        // Handle the result here
        val resultCode = result.resultCode
        val data = result.data
        // Handle the result as needed
    }
    TopAppBar(
        title = { Text(text = "ULima GYM") },
        /*navigationIcon = {
            IconButton(
                onClick = {
                    // Handle navigation icon click (e.g., open drawer or navigate back)
                }
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        },*/
        actions = {
            IconButton(
                onClick = {
                    isMenuExpanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
            }
            DropdownMenu(
                expanded = isMenuExpanded,
                onDismissRequest = { isMenuExpanded = false },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                screens.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        onClick = {
                            // Handle menu item click
                            isMenuExpanded = false
                            if(item.route == "ver_perfil"){
                                val intent = Intent(context, PrActivity::class.java)
                                intent.putExtra("userId", userId)
                                launcherActivity.launch(intent)
                            }else if(item.onClick == null && item.route != null){
                            navController.navigate(item.route)}
                            else{
                                item.onClick?.let{it()}
                            }
                        }
                    ) {
                        Text(text = item.title)
                    }
                }
            }
        },
        // modifier = Modifier.fillMaxSize()
    )
}
