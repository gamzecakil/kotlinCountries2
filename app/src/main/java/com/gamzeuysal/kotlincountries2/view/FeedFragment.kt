package com.gamzeuysal.kotlincountries2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamzeuysal.kotlincountries2.R
import com.gamzeuysal.kotlincountries2.adapter.CountryAdapter
import com.gamzeuysal.kotlincountries2.viewmodel.CountryViewModel
import com.gamzeuysal.kotlincountries2.viewmodel.FeedViewNodel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewNodel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        button.setOnClickListener {
            //argument deger gönderme
           // val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(50)
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }

 */
        //viewmodel initialize
        viewModel = ViewModelProviders.of(this).get(FeedViewNodel::class.java)
        //view modela verileri yükleyelim
        viewModel.refreshData()//view modeli teteikledim

        //recylerview de gösterelim
        recyclerViewCountryList.layoutManager = LinearLayoutManager(context)
        recyclerViewCountryList.adapter = countryAdapter //şurada adapterdaki list  bostu observeLiveData daki    notifyDataSetChanged() ile adapter daki listi guncelledik.

        //MutableLiveData'larda herhangi bir değişiklilik var mı gözlemleyelim.
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {  countries ->
            countries?.let {
                recyclerViewCountryList.visibility = View.VISIBLE
                countryAdapter.updataCountyList(countries)//adapter'daki liste parametresini dolduruyoruz
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {  error ->
            error?.let {
                //hata varsa hata mesajı çıkar
                if(error){
                    textCountryError.visibility = View.VISIBLE
                }else{
                    textCountryError.visibility = View.GONE
                }

            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {  loading ->
            loading?.let {
                if(loading){
                    //veriler yükleniyorken progressbar gözükürken recylerview ve hata mesajının gözükmemesi gerekir
                    progressBarCountryLoading.visibility = View.VISIBLE
                    recyclerViewCountryList.visibility = View.GONE
                    textCountryError.visibility = View.GONE
                }
                else{
                    progressBarCountryLoading.visibility = View.GONE
                }
            }

        })
    }
}