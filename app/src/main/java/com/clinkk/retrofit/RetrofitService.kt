package com.clinkk.retrofit


import com.clinkk.viewHospitals.bean.ClinicBean
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitService {
    private val BASE_URL = "https://staging-api.clinikk.com/v1/clinic/"
    private var client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(returnInterceptor())
        .build()
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)

        .build()
        .create(RetrofitApi::class.java)
    fun searchWithOutLocation(): Call<ClinicBean> {
        return api.searchHospital()
    }
    fun searchWithLocation(lat: String, long: String, radius: String): Call<ClinicBean> {
        return api.searchHospitalWithLocation(lat,long, radius)
    }

    private fun returnInterceptor(): HttpLoggingInterceptor {

       return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    }
}