package com.yizhenwind.rocket.feature.categorysubject.ui.subject

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yizhenwind.rocket.core.framework.base.BaseListFragment
import com.yizhenwind.rocket.core.framework.ext.navigate
import com.yizhenwind.rocket.core.framework.ext.setThrottleClickListener
import com.yizhenwind.rocket.core.framework.ext.showSnack
import com.yizhenwind.rocket.core.framework.mvi.IMVIHost
import com.yizhenwind.rocket.core.framework.widget.MessageDialog
import com.yizhenwind.rocket.core.model.Subject
import com.yizhenwind.rocket.feature.categorysubject.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

/**
 *
 * @author WangZhiYao
 * @since 2023/2/27
 */
@AndroidEntryPoint
class SubjectListFragment : BaseListFragment(),
    IMVIHost<SubjectListViewState, SubjectListSideEffect> {

    private val viewModel by viewModels<SubjectListViewModel>()

    private val navArgs by navArgs<SubjectListFragmentArgs>()

    override val adapter = SubjectAdapter()

    override fun initData() {
        viewModel.apply {
            observe(viewLifecycleOwner, state = ::render, sideEffect = ::handleSideEffect)
            observeSubjectListByCategoryId(navArgs.categoryId)
        }
    }

    override fun initView() {
        super.initView()
        adapter.onDeleteClickListener = { subject ->
            showDeleteSubjectDialog(subject)
        }
        binding.fab.apply {
            isVisible = true
            setImageResource(R.drawable.ic_round_add_white_24dp)
            setThrottleClickListener {
                navigate(SubjectListFragmentDirections.actionSubjectListToCreateSubject(navArgs.categoryId))
            }
        }
    }

    override suspend fun render(state: SubjectListViewState) {
        adapter.submitList(state.subjectList)
    }

    override fun handleSideEffect(sideEffect: SubjectListSideEffect) {
        binding.apply {
            when (sideEffect) {
                SubjectListSideEffect.DeleteSubjectSuccess ->
                    root.showSnack(R.string.subject_list_delete_subject_success, anchorView = fab)
                is SubjectListSideEffect.ShowError ->
                    root.showSnack(sideEffect.resId, anchorView = fab)
            }
        }
    }

    private fun showDeleteSubjectDialog(subject: Subject) {
        MessageDialog.Builder(childFragmentManager)
            .setTitle(getString(R.string.dialog_delete_subject_title))
            .setContent(getString(R.string.dialog_delete_subject_content, subject.content))
            .setPositive(getString(R.string.dialog_delete_subject_confirm))
            .setOnPositiveClickListener { dialog ->
                viewModel.deleteSubject(subject)
                dialog.dismiss()
            }
            .show()
    }

}