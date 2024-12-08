package com.airlibs.data.sources.remote

import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: AirLibsApiService
) : RemoteDataSource {
    override suspend fun getMedicines(): Response<ResponseBody> =
        apiService.getMedicines()
}