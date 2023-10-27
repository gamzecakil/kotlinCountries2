package com.gamzeuysal.kotlincountries2.service

import com.gamzeuysal.kotlincountries2.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CounrtyAPI {

    //GET,POST
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //Tüm call'ları GET,POST burada yazabiliriz.
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>
}