package kohei.araya.androidsampler.ui.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kohei.araya.androidsampler.R
import kohei.araya.androidsampler.business.model.RecyclerViewItems
import java.util.*

class RecyclerViewFragment : Fragment() {

    companion object {
        fun newInstance() = RecyclerViewFragment()
    }

    private lateinit var viewModel: RecyclerViewModel
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
        viewModel = ViewModelProvider.NewInstanceFactory().create(RecyclerViewModel::class.java)
        viewModel.getItems().observe(viewLifecycleOwner, Observer { items ->
            viewManager = LinearLayoutManager(context)
            viewAdapter = RecyclerViewAdapter(
                items,
                onClickMainListItem = {
                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
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

    class RecyclerViewAdapter(
        // リストに表示する内容
        private val dataList: List<RecyclerViewItems>,
        // リストがクリックされた際の処理
        private val onClickMainListItem: (mainListItem: RecyclerViewItems) -> Unit
    ) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

        class RecyclerViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerViewHolder {
            val textView = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_item,
                parent,
                false
            ) as TextView
            return RecyclerViewHolder(textView)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            // リスト項目表示
            holder.textView.text =
                String.format(Locale.JAPAN, "%s%d", dataList[position].toString(), position)

            // 選択イベント(リスナーを呼ぶだけ)
            holder.textView.setOnClickListener { onClickMainListItem.invoke(dataList[position]) }
        }

        override fun getItemCount() = dataList.size
    }
}
