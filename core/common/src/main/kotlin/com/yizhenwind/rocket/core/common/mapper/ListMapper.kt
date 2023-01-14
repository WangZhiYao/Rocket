package com.yizhenwind.rocket.core.common.mapper

/**
 *
 * @author WangZhiYao
 * @since 2022/3/9
 */
class ListMapper<in I, out O>(private val mapper: IMapper<I, O>) : IMapper<List<I>, List<O>> {

    override fun map(input: List<I>): List<O> = input.map { mapper.map(it) }

}