package com.yizhenwind.rocket.core.framework.base

import androidx.fragment.app.Fragment

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface FragmentArgs {

    fun newInstance(): Fragment

}