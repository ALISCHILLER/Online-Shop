package com.msa.onlineshopzar.data.remote.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


class MakeSafeApiCall @Inject constructor(
    @ApplicationContext val context: Context
) {
     suspend fun <T> makeSafeApiCall(api: suspend () -> Response<T?>) = flow {
        emit(Resource.loading())
        if (context.isNetworkAvailable()) {
            val response = api.invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    emit(Resource.success(response.body()))
                }else
                    emit(Resource.error(error = MsaError(code = ErrorCode.NULL_RESPONSE_BODY)))
            } else {
                     emit(Resource.error(response.body(), error = MsaError(response.code(),response.message())))
            }
        } else {
            emit(Resource.error(error = MsaError(code = ErrorCode.NETWORK_NOT_AVAILABLE)))
        }
    }.catch { ex ->
        emit(Resource.error(error = MsaError(code = ErrorCode.CATCH_BODY,message = ex.toString())))
        Timber.e(ex)
    }

}