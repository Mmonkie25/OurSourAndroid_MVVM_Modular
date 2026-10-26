package com.oursour.view.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.componentes.CampoTexto
import com.oursour.view.componentes.TituloPantalla
import com.oursour.view.tema.AmbarSecundario
import com.oursour.view.tema.TextoSecundario

@Composable
fun AccesoScreen(
    registro: Boolean, nombre: String, correo: String, contrasena: String,
    cambiarNombre: (String) -> Unit, cambiarCorreo: (String) -> Unit,
    cambiarContrasena: (String) -> Unit, enviar: () -> Unit, cambiarModo: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
        TituloPantalla(if (registro) "Crear cuenta" else "Bienvenido de vuelta")
        Text("OUR SOUR · COCKTAILS & MORE", color = AmbarSecundario)
        Spacer(Modifier.height(24.dp))
        if (registro) CampoTexto("Nombre", nombre, cambiarNombre, Icons.Default.Person)
        CampoTexto("Correo electrónico", correo, cambiarCorreo, Icons.Default.Email, KeyboardType.Email)
        CampoTexto("Contraseña", contrasena, cambiarContrasena, Icons.Default.Lock, esContrasena = true)
        Spacer(Modifier.height(18.dp))
        BotonPrincipal(if (registro) "Crear cuenta" else "Ingresar", enviar)
        TextButton(onClick = cambiarModo, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            androidx.compose.material3.Text(if (registro) "Ya tengo una cuenta" else "Crear una cuenta", color = AmbarSecundario)
        }
    }
}
