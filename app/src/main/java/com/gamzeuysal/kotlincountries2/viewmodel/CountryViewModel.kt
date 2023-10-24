package com.gamzeuysal.kotlincountries2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries2.model.Country

class CountryViewModel :ViewModel(){
    val countryLiveData = MutableLiveData<Country>()

   //dumy datalar olusturalım
    fun getDataFromRoom(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        //MutableLiveData'lara verileri verelim
        countryLiveData.value = country
    }
}