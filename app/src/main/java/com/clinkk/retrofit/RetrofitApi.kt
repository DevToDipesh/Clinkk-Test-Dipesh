package com.clinkk.retrofit


import com.clinkk.viewHospitals.bean.ClinicBean
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @GET("search")
    @Headers(
        "Connection:keep-alive","Content-type:application/json"
    )    fun searchHospital(): Call<ClinicBean>
    /**Register into App*/

    @GET("search")
    @Headers(
        "Connection:keep-alive","Content-type:application/json"
    )
    fun searchHospitalWithLocation(@Query("lat")lat: String, @Query("lon")long: String, @Query("radius")radius: String): Call<ClinicBean>
    /**Profile Details*/

}