package com.airlibs.data.retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.airlibs.data.sources.remote.AirLibsApiService
import com.airlibs.data.util.MockResponseFileReader
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.Test

@RunWith(JUnit4::class)
class ApiServiceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: AirLibsApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(AirLibsApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulGetRequest() {
        val mockedResponse = MockResponseFileReader("drugsApi/success_response.json").content
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { apiService.getMedicines() }
        assertTrue(response.isSuccessful)
    }
}