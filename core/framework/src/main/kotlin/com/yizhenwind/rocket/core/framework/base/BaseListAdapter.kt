package com.yizhenwind.rocket.core.framework.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yizhenwind.rocket.core.framework.widget.BaseViewHolder

/**
 *
 * @author WangZhiYao
 * @since 2023/1/20
 */
abstract class BaseListAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    var onItemClickListener: ((T) -> Unit)? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}