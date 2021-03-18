package d.offrede.categories.presentation.view

import android.view.ViewGroup
import android.widget.TextView
import d.offrede.base.presentation.view.BaseViewHolder
import d.offrede.categories.R

class CategoriesViewHolder(itemView: ViewGroup, private val click: (String) -> Unit = { _ -> }) : BaseViewHolder<String>(itemView, R.layout.adapter_categories) {
    override fun bind(item: String) {
        itemView.findViewById<TextView>(R.id.text).text = item

        itemView.setOnClickListener {
            click(item)
        }
    }
}