package com.example.taskmanager.common

sealed class Result<T> {

    class ApiSuccess<T>(val data: T) : Result<T>()

    class ApiError<T>(val code: Int?, val message: String?) : Result<T>()

    class ApiException<T>(val e: Throwable) : Result<T>()
}

//fun <T, O> Result<T>.map(transform: (T) -> O): Result<O> =
//    when (this) {
//        is Result.ApiError -> Result.ApiError(code, message)
//        is Result.ApiException -> Result.ApiException(e)
//        is Result.ApiSuccess -> Result.ApiSuccess(transform(data))
//    }
//
//fun <T, R, O> Result<T>.merges(second: Result<R>, transform: (T, R) -> O): Result<O> {
//    val first = this
//    return when {
//        first is Result.ApiSuccess && second is Result.ApiSuccess ->
//            Result.ApiSuccess(transform(first.data, second.data))
//        first is Result.ApiError -> Result.ApiError(first.code, first.message)
//        first is Result.ApiException -> Result.ApiException(first.e)
//        second is Result.ApiError -> Result.ApiError(second.code, second.message)
//        second is Result.ApiException -> Result.ApiException(second.e)
//        else -> error("Result cannot have this type")
//    }
//}
//
//fun <T1, T2, O> merge(t1: Result<T1>, t2: Result<T2>,  transform: (T1, T2) -> O): Result<O> {
//    val all = listOf(t1, t2,)
//    all.firstOrNull { it is Result.ApiError<*> }?.let {
//        it as Result.ApiError
//        return Result.ApiError(it.code, it.message)
//    }
//    all.firstOrNull { it is Result.ApiException<*> }?.let {
//        it as Result.ApiException
//        return Result.ApiException(it.e)
//    }
//    t1 as Result.ApiSuccess<T1>
//    t2 as Result.ApiSuccess<T2>
//    return Result.ApiSuccess(transform(t1.data, t2.data))
//}
