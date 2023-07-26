package com.odisby.copa_feminina.data.remote.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa_feminina.data.remote.model.MatchRemote
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

internal fun List<MatchRemote>.toDomain() = map { it.toDomain() }
fun MatchRemote.toDomain(): MatchDomain {
    return MatchDomain(
        id, date, name, teamA, teamB, stadium, hasTeamNames, finishGame, score
    )
}

private fun LocalDateTime.toPhoneTimeZone(): LocalDateTime {
    return atZone(ZoneId.systemDefault()).toLocalDateTime()
}
