package com.odisby.copa_feminina.data.remote.model

import com.odisby.copa.womens.domain.model.Score

internal typealias MatchRemote = Match
data class Match(
    val id: Int,
    val date: String,
    val name: String,
    val teamA: String,
    val teamB: String,
    val stadium: String,
    val hasTeamNames: Boolean,
    val finishGame: Boolean,
    val score: Score,
)