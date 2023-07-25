package com.odisby.copa.womens.domain.model

import java.time.LocalDateTime

typealias MatchDomain = Match

data class Match(
    val id: String,
    val name: String,
    val teamA: String,
    val teamB: String,
    val stadium: String,
    val date: LocalDateTime,
    val hasTeamNames: String,
    val notificationEnabled: Boolean = false,
)
