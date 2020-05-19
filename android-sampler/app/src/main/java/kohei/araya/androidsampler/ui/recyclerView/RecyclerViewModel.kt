package kohei.araya.androidsampler.ui.recyclerView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kohei.araya.androidsampler.business.usecase.GetRecyclerViewItems

class RecyclerViewModel : ViewModel() {

    fun getItems() = liveData {
        val items = GetRecyclerViewItems().get()
        emit(items)
    }
}
