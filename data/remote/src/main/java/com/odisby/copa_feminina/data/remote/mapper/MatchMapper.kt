package com.odisby.copa_feminina.data.remote.mapper

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa_feminina.data.remote.model.MatchRemote
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
    val gmtDateTime = atZone(ZoneId.of("GMT"))
    val phoneTimeZoneDateTime = gmtDateTime.withZoneSameInstant(ZoneId.systemDefault())
    return phoneTimeZoneDateTime.toLocalDateTime()
}

fun LocalDateTime.getDate(): String {
    return DateTimeFormatter.ofPattern("dd/MM HH:mm").format(this)
}