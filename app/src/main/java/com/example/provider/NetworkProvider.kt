package com.example.provider

import android.util.Log
import com.example.model.SigninResponse


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

const val NP_TAG = "NetworkProvider"
private interface ApiService {

    @POST("auth/signin/")
    @FormUrlEncoded
    fun loginService(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<SigninResponse>
}

object NetworkProvider {

//    val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

    var data: SigninResponse? = null
    fun login(username: String, password: String): SigninResponse? {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl())
             .addConverterFactory(MoshiConverterFactory.create()).build()
         val service = retrofit.create(ApiService::class.java)
         service.loginService(username, password).enqueue(object: Callback<SigninResponse>{
             override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                 if(response.isSuccessful)
                     data = response.body()
                 else
                     Log.d(NP_TAG,"Respuesta inesperada ${response.errorBody()}")
             }
             override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                 Log.d(NP_TAG,"Error in retrofit cause ${t.message}")
             }

         })
         Log.d(NP_TAG, data.toString())
         return data
    }

    private fun baseUrl(): String = "http://10.0.2.2:1515/"


}