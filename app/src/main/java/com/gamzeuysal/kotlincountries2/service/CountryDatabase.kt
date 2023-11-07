package com.gamzeuysal.kotlincountries2.service


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gamzeuysal.kotlincountries2.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase() {

    abstract fun countryDao() : CountryDao

    //Veri tabanımızdan sadece tek bir obje oluşturulmasını istiyoruz.Farklı thread'lerden ulaşılmak istenirse conflict olusmaması adına Singleton olusturuyoruz.
    //Her yerden ulaşabiliyoruz
    //@Volatile tanımlanan değişken de diğer thread'lere de görünür duruma geçiyor.Burada da farklı threadler coroutine kullanmasaydık @Volatile değişkeni kullanmaya gerek yoktu
    //Singleton
    companion object{

        @Volatile private  var instance :CountryDatabase? = null

        private val lock = Any()
        //instance yoksa olustur varsa olan instance üzerinden devam et
        //synchronized aynı anda iki thread gelip bu objeye ulaşamasın diye
        operator fun invoke(context:Context) = instance ?: synchronized(lock){
          //varsa instance döndür yoksa instance olustur
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countrydatabase").build()
    }

}