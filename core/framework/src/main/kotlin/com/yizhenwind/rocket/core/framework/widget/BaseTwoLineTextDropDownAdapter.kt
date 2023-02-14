package com.yizhenwind.rocket.core.framework.widget

import android.view.View
import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemDropDownTwoLineTextBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
abstract class BaseTwoLineTextDropDownAdapter<T> : BaseDropDownAdapter<T>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            parent.viewBinding(ItemDropDownTwoLineTextBinding::inflate)
        } else {
            convertView.tag as ItemDropDownTwoLineTextBinding
        }

        getItem(position).apply {
            binding.tvDropDownContentTop.text = convertToString(this)
            binding.tvDropDownContentBottom.text = contentBottom(this)
        }

        return binding.root.apply { tag = binding }
    }

    abstract fun contentBottom(item: T): CharSequence

}