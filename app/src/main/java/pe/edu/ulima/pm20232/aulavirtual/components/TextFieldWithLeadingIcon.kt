package pe.edu.ulima.pm20232.aulavirtual.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray1200
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray400
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Gray800
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Orange400
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Orange800
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.White400

@Composable
fun TextFieldWithLeadingIcon(
    leadingIcon: ImageVector? = null,
    isPassword: Boolean? = false,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) Color.Blue else Color.Gray
    if (leadingIcon != null){
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                //.border(1.dp, borderColor)
                .padding(5.dp)
                .background(color = Color.Transparent),
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(text = placeholder, fontSize = 16.sp,color = Color.Gray)
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isSystemInDarkTheme()) Gray1200 else Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = if(isSystemInDarkTheme()) Color.DarkGray else Orange800
            ),
            visualTransformation = if (isPassword == false) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(18.dp)
                        .clickable { /* Handle icon click if needed */ }
                )
            },
        )
    }else{
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                //.border(1.dp, borderColor)
                .padding(1.dp)
                .background(color = Color.Transparent),
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(text = placeholder, fontSize = 5.sp)
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = if (isSystemInDarkTheme()) Color.LightGray else Orange800
            ),
            visualTransformation = if (isPassword == false) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = null,

            )
    }
}
