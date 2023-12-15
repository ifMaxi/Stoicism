package com.maxidev.stoic.ui.state

import com.maxidev.stoic.data.model.StoicModel

sealed interface NetTypeState {
    data class Success(val onSuccess: StoicModel): NetTypeState
    data class Error(val onError: Exception): NetTypeState
    data object Loading: NetTypeState
}