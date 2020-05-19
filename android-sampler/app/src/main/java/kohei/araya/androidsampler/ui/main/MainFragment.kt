package kohei.araya.androidsampler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kohei.araya.androidsampler.R
import kohei.araya.androidsampler.business.model.MainItems
import kohei.araya.androidsampler.ui.recyclerView.RecyclerViewFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        viewModel.getItems().observe(viewLifecycleOwner, Observer { items ->
            viewManager = LinearLayoutManager(this.context)
            viewAdapter = MainAdapter(
                items,
                onClickMainListItem = { mainItems ->
                    val fragment = when (mainItems) {
                        MainItems.RECYCLER_VIEW -> RecyclerViewFragment.newInstance()
                        MainItems.SNACK_BAR -> Fragment() // TODO
                        MainItems.SCOPE_FUNCTIONS -> Fragment() // TODO
                    }

                    // 画面遷移
                    activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                        ?.replace(R.id.main_layout, fragment)?.commit()
                }
            )

            view?.let {
                recyclerView = it.findViewById<RecyclerView>(R.id.main_recycler_view).apply {
                    setHasFixedSize(true) // リストの高さが固定の場合true
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
        })
    }

    class MainAdapter(
        // リストに表示する内容
        private val dataList: List<MainItems>,
        // リストがクリックされた際の処理
        private val onClickMainListItem: (mainListItem: MainItems) -> Unit
    ) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

        class MainHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MainHolder {
            val textView = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_item,
                parent,
                false
            ) as TextView
            return MainHolder(textView)
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            // リスト項目表示
            holder.textView.text = dataList[position].toString()
            // 選択イベント(リスナーを呼ぶだけ)
            holder.textView.setOnClickListener { onClickMainListItem(dataList[position]) }
        }

        override fun getItemCount() = dataList.size
    }
}
