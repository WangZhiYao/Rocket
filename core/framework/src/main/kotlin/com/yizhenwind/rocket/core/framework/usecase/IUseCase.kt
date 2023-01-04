package com.yizhenwind.rocket.core.framework.usecase

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/12/4
 */
interface IUseCase<T> {

    fun execute(): T

}