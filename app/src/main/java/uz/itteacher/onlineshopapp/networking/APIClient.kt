package uz.itteacher.onlineshopapp.networking

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
   const val BASE_URL = "https://dummyjson.com"

    fun getInstance(): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}