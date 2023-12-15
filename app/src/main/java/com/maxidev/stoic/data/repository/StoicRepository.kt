package com.maxidev.stoic.data.repository

import com.maxidev.stoic.data.model.StoicModel

interface StoicRepository {
    suspend fun fetchRandomStoicQuote(): StoicModel
}