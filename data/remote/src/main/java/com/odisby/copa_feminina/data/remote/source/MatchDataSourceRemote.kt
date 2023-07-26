package com.odisby.copa_feminina.data.remote.source

import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa_feminina.data.remote.extensions.getOrThrowDomainError
import com.odisby.copa_feminina.data.remote.mapper.toDomain
import com.odisby.copa_feminina.data.remote.services.MatchesServices
import com.odisby.data.source.MatchesDataSource
import javax.inject.Inject

class MatchDataSourceRemote @Inject constructor(
    private val service: MatchesServices
) : MatchesDataSource.Remote {

    override suspend fun getMatches(): List<MatchDomain> {
        return runCatching {
            service.getMatches()
        }.getOrThrowDomainError().toDomain()
    }
}
