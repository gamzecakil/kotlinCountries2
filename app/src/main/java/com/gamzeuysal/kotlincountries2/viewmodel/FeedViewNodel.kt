package com.gamzeuysal.kotlincountries2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries2.model.Country

class FeedViewNodel : ViewModel(){

    val countries = MutableLiveData<ArrayList<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    //dumy datalar olusturalım
    fun refreshData(){
        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ss.com")
        val  country2 = Country("France","Europe","Paris","Euro","French","www.ss.com")
        val country3 = Country("Germany","Europe","Berlin","Euro","German","www.ss.com")

        val countyrList = arrayListOf<Country>(country,country2,country3)

        //MutableLiveData'lara verileri verelim
        countries.value = countyrList
        countryError.value = false
        countryLoading.value = false


    }

}