package com.yizhenwind.rocket.feature.subject.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.yizhenwind.rocket.core.common.constant.DeepLink
import com.yizhenwind.rocket.core.framework.R
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
@AndroidEntryPoint
class SubjectListFragment : BaseListFragment(), IMVIHost<SubjectListViewState, Nothing> {

    private val viewModel by viewModels<SubjectListViewModel>()

    override val adapter: SubjectAdapter = SubjectAdapter()

    override fun initData() {
        viewModel.observe(viewLifecycleOwner, state = ::render)
    }

    override fun initView() {
        super.initView()
        adapter.onItemClickListener = { subject ->

        }
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                navigate {
                    module(DeepLink.Module.SUBJECT)
                    path(DeepLink.Path.CREATE)
                }
            }
        }
    }

    override suspend fun render(state: SubjectListViewState) {
        adapter.submitList(state.subjectList)
    }

}