package kohei.araya.androidsampler.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kohei.araya.androidsampler.business.usecase.GetMainItems

class MainViewModel : ViewModel() {

    fun getItems() = liveData {
        val items = GetMainItems().get()
        emit(items)
    }
}
