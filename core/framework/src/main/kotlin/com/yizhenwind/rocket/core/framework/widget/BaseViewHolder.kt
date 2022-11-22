package com.yizhenwind.rocket.core.framework.widget

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/10/30
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var onItemClickListener: ((T) -> Unit)? = null

    abstract fun bind(item: T)

}