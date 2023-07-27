package com.odisby.data.repository

import com.odisby.copa.womens.domain.model.Match
import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.repositories.MatchesRepository
import com.odisby.data.source.MatchesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MatchesDataSource.Remote,
    private val localDataSource: MatchesDataSource.Local
) : MatchesRepository {
    override suspend fun getMatches(): Flow<List<MatchDomain>> {
        return flowOf(remoteDataSource.getMatches())
            .combine(localDataSource.getActiveNotificationIds()) { matches: List<MatchDomain>, ids: Set<String> ->
                matches.map { match ->
                    match.copy(notificationEnabled = ids.contains(match.id.toString()))
                }
            }
    }

    override suspend fun enableNotificationFor(id: String) {
        localDataSource.enableNotificationFor(id)
    }

    override suspend fun disableNotificationFor(id: String) {
        localDataSource.disableNotificationFor(id)
    }
}