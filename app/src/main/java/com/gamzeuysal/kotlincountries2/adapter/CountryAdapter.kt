package com.gamzeuysal.kotlincountries2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gamzeuysal.kotlincountries2.R
import com.gamzeuysal.kotlincountries2.databinding.ItemRowBinding
import com.gamzeuysal.kotlincountries2.model.Country
import com.gamzeuysal.kotlincountries2.util.dowloadFromUrl
import com.gamzeuysal.kotlincountries2.util.placeholderProgressBar
import com.gamzeuysal.kotlincountries2.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class CountryAdapter (val countyList : ArrayList<Country>) :RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

/*
    class  CountryViewHolder( var view :View):RecyclerView.ViewHolder(view){

    }
 */
    //Data Binding
class  CountryViewHolder( var view :ItemRowBinding):RecyclerView.ViewHolder(view.root) {

}

    //layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        //Data Binding
        val view  = DataBindingUtil.inflate<ItemRowBinding>(inflater,R.layout.item_row,parent,false)
        return CountryViewHolder(view)
    }
//item
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
    /*
        holder.itemView.textName.text = countyList[position].countryName
        holder.itemView.textRegion.text = countyList[position].countryRegion
     //bir itema tıklandıgında diğer fragmenta geçelim
     holder.itemView.setOnClickListener {
         val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countyList[position].uuid)
         Navigation.findNavController(it).navigate(action)
     }
    //Glide
    countyList[position].imageUrl?.let {
        holder.itemView.imageView.dowloadFromUrl(it,placeholderProgressBar(holder.itemView.context)

    )
    }
*/
        //Data Binding
        holder.view.country = countyList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
       return countyList.size
    }
    fun updataCountyList(newCountryList : List<Country>)
    {
        countyList.clear()
        countyList.addAll(newCountryList)
        notifyDataSetChanged()//adapter'a verinin değiştiğini bildirmek için kullanıyoruz.
    }

    override fun onCountryClicked(view: View) {
        val uuid = view.countryUuidtext.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}