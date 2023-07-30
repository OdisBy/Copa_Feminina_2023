package com.odisby.copa.womens.domain.usecase

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.repositories.MatchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(): Flow<Pair<List<MatchDomain>, List<MatchDomain>>> {
        return repository.getMatches()
    }
}