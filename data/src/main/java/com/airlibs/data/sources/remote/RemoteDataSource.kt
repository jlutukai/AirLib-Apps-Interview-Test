package com.airlibs.data.sources.remote

import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteDataSource{
    suspend fun getMedicines(): Response<ResponseBody>
}