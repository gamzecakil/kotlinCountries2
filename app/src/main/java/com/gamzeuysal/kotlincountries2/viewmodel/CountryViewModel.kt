package com.gamzeuysal.kotlincountries2.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries2.model.Country
import com.gamzeuysal.kotlincountries2.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application:Application) :BaseViewModel(application){
    val countryLiveData = MutableLiveData<Country>()

   //dumy datalar olusturalım
    fun getDataFromRoom(uuid :Int){
       launch {
           val dao = CountryDatabase(getApplication()).countryDao()
           val country = dao.getCountry(uuid)
           countryLiveData?.value = country
       }
    }
}