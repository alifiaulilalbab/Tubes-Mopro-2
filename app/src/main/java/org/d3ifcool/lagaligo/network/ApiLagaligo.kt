package org.d3ifcool.lagaligo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.d3ifcool.lagaligo.model.ResponseLagaligo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

enum class ApiStatus { LOADING, SUCCESS, FAILED }

object ApiLagaligo {
    private val okHttpBuilder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build()
   val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://firebasestorage.googleapis.com/v0/b/lagaligo-ac5de.appspot.com/o/")
    .client(okHttpBuilder)
    .build()

    val service: ApiLagaligoService by lazy {
        retrofit.create(ApiLagaligoService::class.java)
    }

    interface ApiLagaligoService {
        @GET("lagaligo.json?alt=media&token=d5cddfd9-e982-40c0-a9fe-a562316eefbf\n")
        suspend fun getListLagaligo(): ResponseLagaligo
    }

}