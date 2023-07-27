package com.odisby.copa_feminina.data.remote.mapper

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa_feminina.data.remote.model.MatchRemote
import java.time.LocalDateTime
import java.time.ZoneId

internal fun List<MatchRemote>.toDomain() = map { it.toDomain() }
fun MatchRemote.toDomain(): MatchDomain {
    val phoneLocalDateTime = run {
        val parsedDateTime = LocalDateTime.parse(date)
        parsedDateTime.toPhoneTimeZone()
    }

    return MatchDomain(
        id = id,
        date = phoneLocalDateTime,
        name = name,
        teamA = teamA,
        teamB = teamB,
        stadium = stadium,
        hasTeamNames = hasTeamNames,
        finishGame = finishGame,
        score = score,

    )
}

private fun LocalDateTime.toPhoneTimeZone(): LocalDateTime {
    return atZone(ZoneId.systemDefault()).toLocalDateTime()
}
