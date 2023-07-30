package com.odisby.data.repository

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.repositories.MatchesRepository
import com.odisby.data.source.MatchesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MatchesDataSource.Remote,
    private val localDataSource: MatchesDataSource.Local
) : MatchesRepository {
    override suspend fun getMatches(): Flow<Pair<List<MatchDomain>, List<MatchDomain>>> {
        return flowOf(remoteDataSource.getMatches())
            .combine(localDataSource.getActiveNotificationIds()) { matches: List<MatchDomain>, ids: Set<String> ->
                val now = LocalDateTime.now()
                val pastMatches = mutableListOf<MatchDomain>()
                val futureMatches = mutableListOf<MatchDomain>()

                matches.forEach { match ->
                    if (match.finishGame) {
                        pastMatches.add(match)
                    } else {
                        val updatedMatch = match.copy(notificationEnabled = ids.contains(match.id.toString()))
                        futureMatches.add(updatedMatch)
                    }
                }
                pastMatches to futureMatches
            }
    }

    override suspend fun enableNotificationFor(id: String) {
        localDataSource.enableNotificationFor(id)
    }

    override suspend fun disableNotificationFor(id: String) {
        localDataSource.disableNotificationFor(id)
    }
}