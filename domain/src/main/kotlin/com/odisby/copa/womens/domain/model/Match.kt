package com.odisby.copa.womens.domain.model

import java.time.LocalDateTime

typealias MatchDomain = Match

data class Match(
    val id: Int,
    val date: LocalDateTime,
    val name: String,
    val teamA: TeamDomain,
    val teamB: TeamDomain,
    val stadium: String,
    val finishGame: Boolean,
    val score: Score,
    val notificationEnabled: Boolean = false,
)
