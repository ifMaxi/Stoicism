package com.maxidev.stoic.data.remote

import com.maxidev.stoic.data.model.StoicModel
import com.maxidev.stoic.utils.Constants.RANDOM_QUOTE
import retrofit2.http.GET

interface ApiService {
    @GET(RANDOM_QUOTE)
    suspend fun apiRandomQuote(): StoicModel
}