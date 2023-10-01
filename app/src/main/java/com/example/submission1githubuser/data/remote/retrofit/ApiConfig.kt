package com.example.submission1githubuser.data.remote.retrofit


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private const val TOKEN = "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"
        fun getApiService(): ApiService {

            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

            val authInterceptor = Interceptor { chain ->
                val originalRequest = chain.request()
                val newRequest: Request = originalRequest.newBuilder()
                    .build()
                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}