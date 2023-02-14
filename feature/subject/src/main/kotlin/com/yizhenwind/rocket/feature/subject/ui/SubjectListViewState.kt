package com.yizhenwind.rocket.feature.subject.ui

import com.yizhenwind.rocket.core.framework.mvi.IViewState
import com.yizhenwind.rocket.core.model.Subject

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/2/13
 */
data class SubjectListViewState(val subjectList: List<Subject> = emptyList()) : IViewState