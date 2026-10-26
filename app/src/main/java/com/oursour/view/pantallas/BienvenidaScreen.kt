package com.oursour.view.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oursour.view.componentes.BotonPrincipal
import com.oursour.view.tema.*

@Composable
fun BienvenidaScreen(entrar: () -> Unit, registrar: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(110.dp).background(SuperficieOscura, RoundedCornerShape(28.dp)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.LocalBar, null, tint = AmbarSecundario, modifier = Modifier.size(64.dp))
        }
        Spacer(Modifier.height(22.dp))
        Text("OUR SOUR", color = TextoPrincipal, fontSize = 34.sp, letterSpacing = 4.sp)
        Text("COCKTAILS & MORE", color = AmbarSecundario, fontSize = 12.sp, letterSpacing = 4.sp)
        Spacer(Modifier.height(36.dp))
        Text("Tu próximo cóctel comienza aquí.", color = TextoPrincipal, fontSize = 18.sp)
        Spacer(Modifier.height(24.dp))
        BotonPrincipal("Iniciar sesión", entrar)
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick = registrar, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextoPrincipal)) { Text("Crear cuenta") }
    }
}
