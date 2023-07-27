package com.odisby.copa_feminina.features

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.odisby.copa_feminina.extensions.observe
import com.odisby.copa_feminina.ui.theme.CopaFemininaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopaFemininaTheme {
                val state by viewModel.state.collectAsState()
                Log.e("MainActivity", "OnCreate: ${state.matches}")
            }
        }
    }

}