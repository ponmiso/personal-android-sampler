package kohei.araya.androidsampler.business.usecase

import kohei.araya.androidsampler.business.model.RecyclerViewItems

class GetRecyclerViewItems {
    fun get(): List<RecyclerViewItems> = (1..30).map { RecyclerViewItems.RECYCLER_VIEW }
}