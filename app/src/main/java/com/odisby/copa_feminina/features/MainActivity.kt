package com.odisby.copa_feminina.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.odisby.copa_feminina.extensions.observe
import com.odisby.copa_feminina.ui.theme.CopaFemininaTheme
import com.odisby.notification_scheduler.NotificationMatcherWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeActions()
        setContent {
            CopaFemininaTheme {
                val state by viewModel.state.collectAsState()
                MainScreen(prevMatches = state.prevMatches, nextMatches = state.nextMatches, viewModel::toggleNotification)
            }
        }
    }
    private fun observeActions() {
        viewModel.action.observe(this) { action ->
            when (action) {
                is MainUiAction.MatchesNotFound -> TODO()
                MainUiAction.Unexpected -> TODO()
                is MainUiAction.DisableNotification ->
                    NotificationMatcherWorker.cancel(applicationContext, action.match)
                is MainUiAction.EnableNotification ->
                    NotificationMatcherWorker.start(applicationContext, action.match)
            }
        }
    }
}