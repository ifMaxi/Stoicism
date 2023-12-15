package com.maxidev.stoic.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.stoic.data.remote.ApiService
import com.maxidev.stoic.data.repository.StoicRepositoryImpl
import com.maxidev.stoic.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @[Singleton Provides]
    fun providesRetrofit(): Retrofit {
        val contentType = MediaType.get("application/json")

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @[Singleton Provides]
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @[Singleton Provides]
    fun providesRepository(apiService: ApiService): StoicRepositoryImpl {
        return StoicRepositoryImpl(apiService)
    }
}