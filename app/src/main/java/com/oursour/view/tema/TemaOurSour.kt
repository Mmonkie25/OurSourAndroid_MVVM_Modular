package com.oursour.view.tema

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val EsquemaOscuro = darkColorScheme(
    primary = NaranjaPrimario, secondary = AmbarSecundario,
    background = FondoPrincipal, surface = SuperficieOscura,
    onPrimary = FondoPrincipal, onSecondary = FondoPrincipal,
    onBackground = TextoPrincipal, onSurface = TextoPrincipal, error = ErrorOurSour
)

@Composable
fun OurSourTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaOscuro, content = content)
}
