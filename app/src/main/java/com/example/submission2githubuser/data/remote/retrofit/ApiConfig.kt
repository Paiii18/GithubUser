package com.example.submission2githubuser.data.remote.retrofit


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
                    .addHeader("Authorization","token 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")
                    .build()
                chain.proceed(newRequest)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)

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