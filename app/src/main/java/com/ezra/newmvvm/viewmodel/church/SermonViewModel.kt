package com.ezra.newmvvm.viewmodel.church

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.data.sermons.PastorResponse
import com.ezra.newmvvm.data.sermons.SermonResponse
import com.ezra.newmvvm.network.ChurchRetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SermonViewModel : ViewModel() {
    private val _sermons = MutableStateFlow<List<SermonResponse>>(emptyList())
    val sermons: StateFlow<List<SermonResponse>> = _sermons

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Map to cache pastor information
    private val _pastors = mutableMapOf<String, PastorResponse>()
    private val _pastorNames = MutableStateFlow<Map<String, String>>(emptyMap())
    val pastorNames: StateFlow<Map<String, String>> = _pastorNames

    init {
        fetchSermonsPeriodically()
    }

    private fun fetchSermonsPeriodically() {
        viewModelScope.launch {
            while (true) {
                fetchSermons()
                delay(190000) // Delay for 3 minutes 10 seconds
            }
        }
    }

    private suspend fun fetchSermons() {
        _isLoading.value = true
        try {
            val fetchedSermons = ChurchRetrofitInstance.api.getSermons()
            _sermons.value = fetchedSermons

            // Fetch all pastors' details if not already fetched
            fetchedSermons.forEach { sermon ->
                if (!_pastors.containsKey(sermon.pastor)) {
                    fetchPastor(sermon.pastor)
                }
            }
        } catch (e: Exception) {
            _sermons.value = emptyList()
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun fetchPastor(id: String) {
        try {
            val pastor = ChurchRetrofitInstance.api.getPastor(id)
            _pastors[id] = pastor
            _pastorNames.value = _pastorNames.value.toMutableMap().apply {
                put(id, pastor.name)
            }
        } catch (e: Exception) {
            _pastorNames.value = _pastorNames.value.toMutableMap().apply {
                put(id, "Unknown Pastor")
            }
        }
    }

    fun getPastorName(pastorId: String): String {
        return _pastorNames.value[pastorId] ?: "Unknown Pastor"
    }
}
