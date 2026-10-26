# Our Sour — Kotlin + Jetpack Compose + MVVM (modularizado)

El proyecto está separado en clases y archivos pequeños para facilitar el mantenimiento.

## Paquetes
- `model`: modelos de dominio y entidad local Room.
- `view`: contenedor de la aplicación, navegación, tema, componentes y pantallas.
- `viewmodel`: estado y lógica de presentación.
- `dao`: DAO de ingredientes frecuentes.
- `data`: configuración Room.

## Pantallas separadas
`BienvenidaScreen.kt`, `AccesoScreen.kt`, `InicioScreen.kt`, `IngredientesScreen.kt`, `RecetasScreen.kt`, `DetalleRecetaScreen.kt`, `CatalogoScreen.kt`, `CarroScreen.kt`, `DespachoScreen.kt`, `PedidosScreen.kt`, `AdministracionScreen.kt`.

## Antes de ejecutar
Agrega en `app/build.gradle.kts` dependencias compatibles con las versiones de tu proyecto:
```kotlin
implementation(platform("androidx.compose:compose-bom:<version>"))
implementation("androidx.activity:activity-compose:<version>")
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.ui:ui-tooling-preview")
implementation("androidx.compose.material3:material3")
implementation("androidx.compose.material:material-icons-extended")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:<version>")
implementation("androidx.navigation:navigation-compose:<version>")
implementation("androidx.room:room-runtime:<version>")
implementation("androidx.room:room-ktx:<version>")
ksp("androidx.room:room-compiler:<version>")
```
Activa el plugin KSP compatible con Kotlin. Las versiones deben coincidir con tu proyecto Android.

## Integración backend
Esta versión usa datos de demostración en el ViewModel. Retrofit, JWT y endpoints reales deben conectarse después de revisar las clases/controladores del backend de tu compañero. El pago es solo simulado.
