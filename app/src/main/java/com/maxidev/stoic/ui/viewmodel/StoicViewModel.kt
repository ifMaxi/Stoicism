package com.maxidev.stoic.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.stoic.data.repository.StoicRepositoryImpl
import com.maxidev.stoic.ui.state.NetTypeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class StoicViewModel @Inject constructor(
    private val repositoryImpl: StoicRepositoryImpl
): ViewModel() {
//    private val _randomStoicState: NetTypeState by mutableStateOf(NetTypeState.Loading)
//    var randomStoicState = _randomStoicState

    var randomStoicState: NetTypeState by mutableStateOf(NetTypeState.Loading)
        private set

    init {
        getRandomStoicQuote()
    }

    fun getRandomStoicQuote() {
        viewModelScope.launch {
            Log.d("ViewModel", "ViewmodelScope thread: ${Thread.currentThread().name}")

            randomStoicState = NetTypeState.Loading
            randomStoicState = try {
                NetTypeState.Success(repositoryImpl.fetchRandomStoicQuote())
            } catch (e: IOException) {
                NetTypeState.Error(Exception(e))
            } catch (e: HttpException) {
                NetTypeState.Error(Exception(e))
            }
        }
    }
}