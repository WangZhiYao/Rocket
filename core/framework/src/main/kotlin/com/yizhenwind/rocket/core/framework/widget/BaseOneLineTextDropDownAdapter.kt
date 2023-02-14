package com.yizhenwind.rocket.core.framework.widget

import android.view.View
import android.view.ViewGroup
import com.yizhenwind.rocket.core.framework.databinding.ItemDropDownOneLineTextBinding
import com.yizhenwind.rocket.core.framework.ext.viewBinding

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/11
 */
abstract class BaseOneLineTextDropDownAdapter<T> : BaseDropDownAdapter<T>() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            parent.viewBinding(ItemDropDownOneLineTextBinding::inflate)
        } else {
            convertView.tag as ItemDropDownOneLineTextBinding
        }

        binding.tvDropDownContent.text = convertToString(getItem(position))

        return binding.root.apply { tag = binding }
    }

}