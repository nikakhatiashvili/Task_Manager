package com.example.taskmanager.data.network

import com.example.taskmanager.common.Result
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val getRow = getRawType(returnType)
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val getRowCallType = getRawType(callType)
        return when {
            getRow != Call::class.java -> null
            getRowCallType != Result::class.java -> null
            else -> {
                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                return NetworkResultCallAdapter(resultType)
            }
        }
    }

    companion object {
        fun create(): NetworkResultCallAdapterFactory = NetworkResultCallAdapterFactory()
    }
}
