package com.yizhenwind.rocket.core.framework.widget

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.annotation.LayoutRes

/**
 * Only use for {@link android.widget.AutoCompleteTextView} android:inputType="none"
 *
 * @author WangZhiYao
 * @since 2023/2/10
 */
class NoFilterArrayAdapter<T>(
    context: Context,
    @LayoutRes resource: Int
) : ArrayAdapter<T>(context, resource) {

    private val noOpFilter = object : Filter() {

        private val noOpResult = FilterResults()

        override fun performFiltering(constraint: CharSequence?): FilterResults = noOpResult

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

        }

    }

    override fun getFilter(): Filter = noOpFilter

}