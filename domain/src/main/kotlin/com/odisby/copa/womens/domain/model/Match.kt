package com.odisby.copa.womens.domain.model

import java.time.LocalDateTime

typealias MatchDomain = Match

data class Match(
    val id: Int,
    val date: LocalDateTime,
    val name: String,
    val teamA: String,
    val teamB: String,
    val stadium: String,
    val hasTeamNames: Boolean,
    val finishGame: Boolean,
    val score: Score,
    val notificationEnabled: Boolean = false,
)
