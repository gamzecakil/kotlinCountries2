package com.gamzeuysal.kotlincountries2.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gamzeuysal.kotlincountries2.model.Country

@Dao
interface CountryDao {

    //Data Access Object
    @Insert
    suspend fun insertAll(vararg  countries:Country):List<Long>

    //Insert -> INSERT INTO
    //suspend -> coroutine ,pause & resume
    //varargs -> multiple country object
    //List<Long> -> primary keys
    //vararg -> yukarıda 10 tane 15 tane gibi sayısı belirsiz country objesi veriyoruz.Ama tek tek veriyoruz ki bize farklı  List<Long> --> uuid objeleri döndürsün.

    @Query("SELECT * FROM  country")
    suspend fun  getAllCountries() : List<Country>

    @Query("SELECT * FROM Country WHERE  uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun  deleteAllCountries()
}