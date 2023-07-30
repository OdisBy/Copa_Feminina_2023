package com.odisby.copa_feminina.features

import androidx.lifecycle.viewModelScope
import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.usecase.DisableNotificationUseCase
import com.odisby.copa.womens.domain.usecase.EnableNotificationUseCase
import com.odisby.copa.womens.domain.usecase.GetMatchesUseCase
import com.odisby.copa_feminina.core.BaseViewModel
import com.odisby.copa_feminina.data.remote.NotFoundException
import com.odisby.copa_feminina.data.remote.UnexpectedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase,
    private val enableNotificationUseCase: EnableNotificationUseCase,
    private val disableNotificationUseCase: DisableNotificationUseCase,
) : BaseViewModel<MainUiState, MainUiAction>(MainUiState()) {
    init {
        fetchMatches()
    }
    private fun fetchMatches() = viewModelScope.launch {
        getMatchesUseCase()
            .flowOn(Dispatchers.Main)
            .catch {
                when(it) {
                    is NotFoundException -> sendAction(MainUiAction.MatchesNotFound(it.message ?: "Erro sem mensagem"))
                    is UnexpectedException -> sendAction(MainUiAction.Unexpected)
                }
            }
            .collect { matches ->
                setState {
                    copy(matches = matches)
                }
            }
    }

    fun toggleNotification(match: MatchDomain) = viewModelScope.launch {
        runCatching {
            withContext(Dispatchers.Main) {
                val action = if (match.notificationEnabled) {
                    disableNotificationUseCase(match.id.toString())
                    MainUiAction.DisableNotification(match)
                } else {
                    enableNotificationUseCase(match.id.toString())
                    MainUiAction.EnableNotification(match)
                }
                sendAction(action)
            }
        }
    }
}

data class MainUiState(
    val matches: List<MatchDomain> = emptyList()
)

sealed interface MainUiAction {
    object Unexpected: MainUiAction
    data class MatchesNotFound(val message: String) : MainUiAction
    data class EnableNotification(val match: MatchDomain) : MainUiAction
    data class DisableNotification(val match: MatchDomain) : MainUiAction
}