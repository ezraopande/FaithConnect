import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezra.newmvvm.data.slider.SliderResponse
import com.ezra.newmvvm.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SliderViewModel : ViewModel() {
    private val _sliderList = MutableStateFlow<List<SliderResponse>>(emptyList())
    val sliderList: StateFlow<List<SliderResponse>> = _sliderList

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchSliders()
    }

    private fun fetchSliders() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSliders()
                _sliderList.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
