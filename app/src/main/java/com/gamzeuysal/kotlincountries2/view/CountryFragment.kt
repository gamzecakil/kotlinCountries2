package com.gamzeuysal.kotlincountries2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gamzeuysal.kotlincountries2.R
import com.gamzeuysal.kotlincountries2.util.dowloadFromUrl
import com.gamzeuysal.kotlincountries2.util.placeholderProgressBar
import com.gamzeuysal.kotlincountries2.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {

    private lateinit var viewModel :CountryViewModel
    private  var countryUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
            println("CountryUuid : "+countryUuid)
        }

        //view model initialize
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        //view modelimizi dolduralım
        viewModel.getDataFromRoom(countryUuid)


        // live data'ı gözlemleyelim
        observeLiveData()
    }

    private fun observeLiveData()
    {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {  country ->
            country?.let {
                //veriler gelince
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryRegion.text = country.countryRegion
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                context?.let {
                    countryImage.dowloadFromUrl(country?.imageUrl!!, placeholderProgressBar(it))
                }

            }
        })
    }

}