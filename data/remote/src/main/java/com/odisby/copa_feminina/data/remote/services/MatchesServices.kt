package com.odisby.copa_feminina.data.remote.services

import com.odisby.copa_feminina.data.remote.model.MatchRemote
import retrofit2.http.GET

interface MatchesServices {
    @GET("api.json")
    suspend fun getMatches(): List<MatchRemote>
}
