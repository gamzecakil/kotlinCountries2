package com.gamzeuysal.kotlincountries2.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzeuysal.kotlincountries2.model.Country
import com.gamzeuysal.kotlincountries2.service.CountryAPIService
import com.gamzeuysal.kotlincountries2.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FeedViewModel(application : Application) : BaseViewModel(application){

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
                       //aldığımız verileri sqlite kaydedelim.
                       storeInSqlite(t)
                   }

                   override fun onError(e: Throwable) {
                       countryError.value = true
                       countryLoading.value = false
                       e.printStackTrace()
                   }

               })
       )
   }
    private fun showCountries(countryList : List<Country>)
    {
        //veriler gelmiş yüklenmiş
        //mutable live datayı tetikleki o da observer olan activity ya da fragment'lara iletsin
        countries.value = countryList
        countryLoading.value = false
        countryError.value = false
    }
    private fun storeInSqlite(list : List<Country>)
    {
        //verileri database kaydederken ana thread bloklamamak adına coroutine kullanalım.
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong =  dao.insertAll(*list.toTypedArray()) //list --> individual
            var i = 0
            while(i < list.size)
            {
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCountries(list)
        }

    }
}