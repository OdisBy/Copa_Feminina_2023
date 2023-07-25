package com.odisby.copa.womens.domain.repositories

import kotlinx.coroutines.flow.Flow
import com.odisby.copa.womens.domain.model.Match

interface MatchesRepository {
    suspend fun getMatches(): Flow<List<Match>>
    suspend fun enableNotificationFor(id: String)
    suspend fun disableNotificationFor(id: String)
}
