package com.example.devsquad.data.data_source.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteRecipesImpl {
    companion object {
        val mainUrl = "https://www.themealdb.com/api/json/v1/1/"
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(mainUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }
}