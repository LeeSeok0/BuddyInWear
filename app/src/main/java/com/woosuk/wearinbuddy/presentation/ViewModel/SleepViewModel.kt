import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosuk.wearinbuddy.presentation.api.RetrofitInstance
import com.woosuk.wearinbuddy.presentation.data.sleep.SleepData
import com.woosuk.wearinbuddy.presentation.data.sleep.SleepRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await
import java.time.LocalTime

class SleepViewModel : ViewModel() {

    private val _sleepData = MutableStateFlow(SleepData(0, 0.0, LocalTime.MIN, LocalTime.MIN))
    val sleepData: StateFlow<SleepData> = _sleepData

    fun fetchSleepData(userId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSleepData(SleepRequest(userId)).await()
                if (response.isNotEmpty()) {
                    val sleepResponse = response[0]
                    _sleepData.value = SleepData(
                        sleepResponse.id,
                        sleepResponse.sleep_duration,
                        sleepResponse.sleep_bedtime_start,
                        sleepResponse.sleep_bedtime_end
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
