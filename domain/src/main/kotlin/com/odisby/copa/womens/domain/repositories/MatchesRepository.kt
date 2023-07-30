package com.odisby.copa.womens.domain.repositories

import com.odisby.copa.womens.domain.model.MatchDomain
import kotlinx.coroutines.flow.Flow

interface MatchesRepository {
    suspend fun getMatches(): Flow<Pair<List<MatchDomain>, List<MatchDomain>>>
    suspend fun enableNotificationFor(id: String)
    suspend fun disableNotificationFor(id: String)
}
