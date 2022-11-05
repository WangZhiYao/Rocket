package com.yizhenwind.rocket.core.framework.lazy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.collection.ArrayMap
import com.yizhenwind.rocket.core.framework.base.FragmentArgs
import java.lang.reflect.Method
import kotlin.reflect.KClass

internal val fragmentMethodSignature = arrayOf(Bundle::class.java)
internal val fragmentMethodMap = ArrayMap<KClass<out FragmentArgs>, Method>()

/**
 *
 *
 * @author WangZhiYao
 * @since 2022/9/3
 */
class FragmentArgsLazy<Args : FragmentArgs>(
    private val fragmentArgsClass: KClass<Args>,
    private val bundleProducer: () -> Bundle
) : Lazy<Args> {

    private var cached: Args? = null

    override val value: Args
        get() {
            var args = cached
            if (args == null) {
                val bundle = bundleProducer()
                val method: Method = fragmentMethodMap[fragmentArgsClass]
                    ?: fragmentArgsClass.java.getMethod("deserialize", *fragmentMethodSignature)
                        .also { method ->
                            // Save a reference to the method
                            fragmentMethodMap[fragmentArgsClass] = method
                        }

                @SuppressLint("BanUncheckedReflection") // needed for method.invoke
                @Suppress("UNCHECKED_CAST")
                args = method.invoke(null, bundle) as Args
                cached = args
            }
            return args
        }

    override fun isInitialized(): Boolean = cached != null
}