package com.maxidev.stoic.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class StoicModel(
    @SerialName("text")
    val text: String,
    @SerialName("author")
    val author: String
)