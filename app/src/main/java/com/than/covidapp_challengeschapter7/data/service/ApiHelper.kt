package com.than.covidapp_challengeschapter7.data.service

class ApiHelper(private val apiService: ApiService) {
    suspend fun getAllCountryCases() = apiService.getAllDataCovid()
    suspend fun getCountryCasesById(country: String) = apiService.getDataCovidById(country)
    suspend fun getAllDataCases() = apiService.getAllData()
}