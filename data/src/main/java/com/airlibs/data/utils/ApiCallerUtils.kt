package com.airlibs.data.utils

import com.airlibs.data.BuildConfig
import com.airlibs.data.R
import com.google.gson.GsonBuilder
import com.airlibs.domain.models.response.common.ErrorResponse
import com.airlibs.domain.models.data.utils.UiText
import com.airlibs.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            if (throwable is CancellationException) {
                throw throwable
            }
            throwable.printStackTrace()
            when (throwable) {

                is UnknownHostException -> ResultWrapper.GenericError(
                    errorMessage = UiText.StringResource(R.string.network_error_message)
                )
                is IOException -> ResultWrapper.GenericError(
                    errorMessage = UiText.StringResource(R.string.network_error_message)
                )
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = parseErrorBody(throwable)
                    ResultWrapper.GenericError(
                        code = code,
                        errorMessage = if (errorResponse?.errors?.get(0)?.message != null) {
                            UiText.DynamicString(errorResponse.errors[0].message)
                        } else {
                            UiText.StringResource(R.string.unknown_error_message)
                        }
                    )
                }
                is IllegalStateException -> ResultWrapper.GenericError(
                    errorMessage = UiText.StringResource(R.string.error_on_getting_response)
                )
                else -> {
                    val errorMessage = if (BuildConfig.DEBUG) {
                        if (throwable.message != null) {
                            UiText.DynamicString(throwable.message!!)
                        } else {
                            UiText.StringResource(R.string.unknown_error_message)
                        }
                    } else {
                        UiText.StringResource(R.string.unknown_error_message)
                    }
                    ResultWrapper.GenericError(
                        null,
                        errorMessage = errorMessage
                    )
                }
            }
        }
    }
}

suspend fun <T> flowSafeCall(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): Flow<ResultWrapper<T>> = flow {
    try {
        emit(ResultWrapper.Success(block.invoke()))
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        emit(
            when (throwable) {

                is IOException -> ResultWrapper.GenericError(
                    errorMessage = UiText.StringResource(R.string.network_error_message)
                )
                is IllegalStateException -> ResultWrapper.GenericError(
                    errorMessage = UiText.StringResource(R.string.error_on_getting_response)
                )
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = parseErrorBody(throwable)
                    ResultWrapper.GenericError(
                        code = code,
                        errorMessage = if (errorResponse?.errors?.get(0)?.message != null) {
                            UiText.DynamicString(errorResponse.errors[0].message)
                        } else {
                            UiText.StringResource(R.string.unknown_error_message)
                        }
                    )
                }
                else -> {
                     val errorMessage = if (BuildConfig.DEBUG) {
                        if (throwable.message != null) {
                            UiText.DynamicString(throwable.message!!)
                        } else {
                            UiText.StringResource(R.string.unknown_error_message)
                        }
                    } else {
                        UiText.StringResource(R.string.unknown_error_message)
                    }
                    ResultWrapper.GenericError(
                        null,
                        errorMessage = errorMessage
                    )
                }
            }
        )
    }
}.flowOn(dispatcher)

fun parseErrorBody(throwable: HttpException): ErrorResponse? = try {
    throwable.response()?.errorBody()?.charStream()?.let {
        val gson = GsonBuilder()
            .setLenient()
            .serializeNulls().create()
        gson.fromJson(it, ErrorResponse::class.java)
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}