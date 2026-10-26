package com.oursour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oursour.view.OurSourApp
import com.oursour.view.tema.OurSourTheme
import com.oursour.viewmodel.OurSourViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OurSourTheme {
                OurSourApp(viewModel = viewModel<OurSourViewModel>())
            }
        }
    }
}
