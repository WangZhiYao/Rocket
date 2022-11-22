package com.yizhenwind.rocket.core.framework.base

import androidx.fragment.app.Fragment

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/7/29
 */
interface IFragmentArgs {

    /**
     * @return returns a new instance of that fragment
     */
    fun newInstance(): Fragment

}