package com.odisby.copa.womens.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.odisby.copa.womens.domain.model.Match
import com.odisby.copa.womens.domain.repositories.MatchesRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(): Flow<List<Match>> {
        return repository.getMatches()
    }
}