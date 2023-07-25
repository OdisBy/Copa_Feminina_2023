package com.odisby.copa.womens.domain.model

typealias MatchDomain = Match

data class Match(
    val id: String,
    val group: String?,
    val teamA: String,
    val teamB: String,
    val stadium: String,
    val gmt: String,
    val hasTeamNames: String,
    val notificationEnabled: Boolean = false,
)
