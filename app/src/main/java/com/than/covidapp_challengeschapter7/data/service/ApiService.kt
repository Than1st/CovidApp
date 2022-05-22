package com.than.covidapp_challengeschapter7.data.service

import com.than.covidapp_challengeschapter7.data.model.GetAllCountryCases
import com.than.covidapp_challengeschapter7.data.model.GetAllData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("covid-19/countries?sort=cases")
    suspend fun getAllDataCovid(): List<GetAllCountryCases>

    @GET("covid-19/countries/{country}")
    suspend fun getDataCovidById(@Path("country") country: String): GetAllCountryCases

    @GET("covid-19/all")
    suspend fun getAllData(): GetAllData
}