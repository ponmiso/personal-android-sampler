package kohei.araya.androidsampler.business.model

enum class MainItems {
    RECYCLER_VIEW {
        override fun toString() = "RecyclerView"
    },
    SNACK_BAR {
        override fun toString() = "SnackBar"
    },
    SCOPE_FUNCTIONS {
        override fun toString() = "スコープ関数"
    }
}