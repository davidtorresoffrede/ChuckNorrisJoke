package d.offrede.search.presentation.view

import android.view.ViewGroup
import android.widget.TextView
import d.offrede.base.presentation.view.BaseViewHolder
import d.offrede.core.presentation.model.Joke
import d.offrede.search.R

class SearchViewHolder(itemView: ViewGroup, private val click: (Joke) -> Unit = { _ -> }) : BaseViewHolder<Joke>(itemView, R.layout.adapter_search) {
    override fun bind(item: Joke) {
        itemView.findViewById<TextView>(R.id.text).text = item.text

        itemView.setOnClickListener {
            click(item)
        }
    }
}