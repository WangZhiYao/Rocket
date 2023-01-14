package com.yizhenwind.rocket.core.common.usecase

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/4
 */
interface IUseCase<T> {

    fun execute(): T

}