package com.oursour.view.componentes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import com.oursour.view.tema.*

@Composable
fun TituloPantalla(texto: String) {
    Text(texto, color = TextoPrincipal, fontSize = 26.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun BotonPrincipal(texto: String, accion: () -> Unit) {
    Button(
        onClick = accion, modifier = Modifier.fillMaxWidth().heightIn(min = 50.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = NaranjaPrimario, contentColor = FondoPrincipal)
    ) { Text(texto, fontWeight = FontWeight.Bold) }
}

@Composable
fun BotonSecundario(texto: String, accion: () -> Unit) {
    OutlinedButton(
        onClick = accion, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AmbarSecundario)
    ) { Text(texto) }
}

@Composable
fun CampoTexto(
    etiqueta: String, valor: String, alCambiar: (String) -> Unit,
    icono: ImageVector, tipo: KeyboardType = KeyboardType.Text, esContrasena: Boolean = false
) {
    OutlinedTextField(
        value = valor, onValueChange = alCambiar, label = { Text(etiqueta) },
        leadingIcon = { Icon(icono, contentDescription = null) },
        visualTransformation = if (esContrasena) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = tipo),
        modifier = Modifier.fillMaxWidth(), singleLine = true, colors = coloresCampo()
    )
}

@Composable
fun coloresCampo() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = TextoPrincipal, unfocusedTextColor = TextoPrincipal,
    focusedBorderColor = NaranjaPrimario, unfocusedBorderColor = TextoSecundario,
    focusedLabelColor = NaranjaPrimario, unfocusedLabelColor = TextoSecundario,
    cursorColor = NaranjaPrimario, focusedLeadingIconColor = AmbarSecundario,
    unfocusedLeadingIconColor = TextoSecundario
)

@Composable
fun FilaResumen(etiqueta: String, valor: String, destacado: Boolean = false) {
    Row(Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(etiqueta, color = if (destacado) TextoPrincipal else TextoSecundario, modifier = Modifier.weight(1f))
        Text(valor, color = if (destacado) AmbarSecundario else TextoPrincipal, fontWeight = FontWeight.Bold)
    }
}

fun formatoMoneda(valor: Int): String = "$" + "%,d".format(valor).replace(",", ".")
