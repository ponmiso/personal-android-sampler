package kohei.araya.androidsampler.business.usecase

import kohei.araya.androidsampler.business.model.MainItems

class GetMainItems {
    fun get(): List<MainItems> {
        return MainItems.values().toList()
    }
}