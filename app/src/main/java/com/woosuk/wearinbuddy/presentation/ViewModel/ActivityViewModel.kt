import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosuk.wearinbuddy.presentation.api.RetrofitInstance
import com.woosuk.wearinbuddy.presentation.data.activity.ActivityData
import com.woosuk.wearinbuddy.presentation.data.activity.ActivityRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await
import java.time.LocalTime

class ActivityViewModel : ViewModel() {

    private val _activityData = MutableStateFlow(ActivityData(0,  0, 0.0))
    val activityData: StateFlow<ActivityData> = _activityData

    fun fetchActivityData(userId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getActivityData(ActivityRequest(userId)).await()
                if (response.isNotEmpty()) {
                    val activityResponse = response[0]
                    _activityData.value = ActivityData(
                        activityResponse.id,
                        activityResponse.steps,
                        activityResponse.calories
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
