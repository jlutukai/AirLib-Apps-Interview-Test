package com.airlibs.data.sources.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface AirLibsApiService {
    @GET("v3/793f843d-a940-40e1-9b46-cb3f247284c5")
    suspend fun getMedicines(): Response<ResponseBody>


}