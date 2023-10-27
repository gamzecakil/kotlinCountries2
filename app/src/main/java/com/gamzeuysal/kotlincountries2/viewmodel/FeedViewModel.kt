package com.gamzeuysal.kotlincountries2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries2.model.Country
import com.gamzeuysal.kotlincountries2.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel(){

    //verieri servisten alacagız
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    //dumy datalar olusturalım
    fun refreshData(){
    getDataFromAPI()

    }
   private fun getDataFromAPI(){
      //mutable live data tetikleyelim
       countryLoading.value = true

       disposable.add(
           countryApiService.getData()
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                   override fun onSuccess(t: List<Country>) {
                      //veriler gelmiş yüklenmiş
                       //mutable live datayı tetikleki o da observer olan activity ya da fragment'lara iletsin
                       countries.value = t
                       countryLoading.value = false
                       countryError.value = false
                   }

                   override fun onError(e: Throwable) {
                       countryError.value = true
                       countryLoading.value = false
                       e.printStackTrace()
                   }

               })
       )
   }
}