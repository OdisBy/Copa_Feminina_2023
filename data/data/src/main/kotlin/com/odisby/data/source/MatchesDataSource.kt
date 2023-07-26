package com.odisby.data.source

import com.odisby.copa.womens.domain.model.Match
import kotlinx.coroutines.flow.Flow

sealed interface MatchesDataSource {
    interface Remote : MatchesDataSource {
        suspend fun getMatches(): List<Match>
    }
    interface Local : MatchesDataSource {
        fun getActiveNotificationIds(): Flow<Set<String>>
        suspend fun enableNotificationFor(id: String)
        suspend fun disableNotificationFor(id: String)
    }
}
