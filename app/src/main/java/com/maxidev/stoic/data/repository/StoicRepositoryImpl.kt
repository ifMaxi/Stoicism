package com.maxidev.stoic.data.repository

import com.maxidev.stoic.data.model.StoicModel
import com.maxidev.stoic.data.remote.ApiService
import javax.inject.Inject

class StoicRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): StoicRepository {
    override suspend fun fetchRandomStoicQuote(): StoicModel =
        apiService.apiRandomQuote()
}