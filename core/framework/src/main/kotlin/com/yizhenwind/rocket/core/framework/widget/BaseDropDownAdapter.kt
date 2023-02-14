package com.yizhenwind.rocket.core.framework.widget

import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable

/**
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
abstract class BaseDropDownAdapter<T> : BaseAdapter(), Filterable {

    private var itemList: List<T> = emptyList()

    fun submitList(itemList: List<T>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): T = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getFilter(): Filter = noOpFilter

    private val noOpFilter = object : Filter() {

        private val noOpResult = FilterResults()

        override fun performFiltering(constraint: CharSequence?): FilterResults = noOpResult

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

        }

        @Suppress("UNCHECKED_CAST")
        override fun convertResultToString(resultValue: Any?): CharSequence =
            if (resultValue == null) "" else convertToString(resultValue as T)

    }

    abstract fun convertToString(item: T): CharSequence

}