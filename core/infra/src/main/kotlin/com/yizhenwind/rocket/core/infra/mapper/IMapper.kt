package com.yizhenwind.rocket.core.infra.mapper

/**
 *
 * @author WangZhiYao
 * @since 2022/3/9
 */
interface IMapper<in I, out O> {

    fun map(input: I): O
}