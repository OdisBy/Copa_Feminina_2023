package com.odisby.copa_feminina.data.remote.mapper

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.model.Team
import com.odisby.copa_feminina.data.remote.R
import com.odisby.copa_feminina.data.remote.model.MatchRemote
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun List<MatchRemote>.toDomain() = map { it.toDomain() }
fun MatchRemote.toDomain(): MatchDomain {
    return MatchDomain(
        id = id,
        date = date.toDeviceTimeZoneString(),
        name = name,
        teamA = teamA.toTeam(hasTeamNames),
        teamB = teamB.toTeam(hasTeamNames),
        stadium = stadium,
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

fun String.toDeviceTimeZoneString(): LocalDateTime {
    val parsedDateTime = LocalDateTime.parse(this)
    return parsedDateTime.toPhoneTimeZone()
}

private fun String.toTeam(hasTeamNames: Boolean): Team {
    if(hasTeamNames) {
        return Team(
            flag = getTeamFlag(this),
            displayName = Locale("", this).isO3Country
        )
    }

    return Team(
        flag = null,
        displayName = null
    )
}

private fun getTeamFlag(team: String): String {
    return team.map {
        String(Character.toChars(it.code + 127397))
    }.joinToString("")
}