package com.odisby.copa_feminina.features

import androidx.lifecycle.viewModelScope
import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.usecase.GetMatchesUseCase
import com.odisby.copa_feminina.core.BaseViewModel
import com.odisby.copa_feminina.data.remote.NotFoundException
import com.odisby.copa_feminina.data.remote.UnexpectedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase
) : BaseViewModel<MainUiState, MainUiAction>(MainUiState()) {
//    init {
//        fetchMatches()
//    }
//    private fun fetchMatches() = viewModelScope.launch {
//        getMatchesUseCase()
//            .flowOn(Dispatchers.Main)
//            .catch {
//                when(it) {
//                    is NotFoundException -> sendAction(MainUiAction.MatchesNotFound(it.message ?: "Erro sem mensagem"))
//                    is UnexpectedException -> sendAction(MainUiAction.Unexpected)
//                }
//            }
//            .collect { matches ->
//                setState {
//                    copy(matches = matches)
//                }
//            }
//    }
}

data class MainUiState(
    val matches: List<MatchDomain> = emptyList()
)

sealed class MainUiAction {
    object Unexpected: MainUiAction()
    data class MatchesNotFound(val message: String): MainUiAction()
}