package pe.edu.ulima.pm20232.aulavirtual.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pe.edu.ulima.pm20232.aulavirtual.ui.theme.Orange400

@Composable
fun ButtonWithIcon(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier? = Modifier.fillMaxWidth().height(45.dp),
    backgroundColor: Color? = Orange400
) {
    Button(
        onClick = { onClick() },
        modifier = modifier!!,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor!!, // Button background color
            contentColor = Color.White // Text and icon color
        ),
        contentPadding = PaddingValues(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}