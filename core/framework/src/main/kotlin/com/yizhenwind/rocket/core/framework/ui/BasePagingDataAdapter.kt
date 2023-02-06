package com.yizhenwind.rocket.core.framework.ui

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
abstract class BasePagingDataAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffCallback) {

    var onItemClickListener: ((T) -> Unit)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }
}