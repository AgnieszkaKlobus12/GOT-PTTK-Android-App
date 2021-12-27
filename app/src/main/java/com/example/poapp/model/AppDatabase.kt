package com.example.poapp.model

import com.example.poapp.model.entities.Uzytkownik
import com.example.poapp.model.dao.UzytkownikDAO
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.poapp.model.dao.TurystaDAO
import com.example.poapp.model.entities.Turysta
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Database(entities = [Uzytkownik::class, Turysta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun uzytkownikDao(): UzytkownikDAO
    abstract fun turystaDao(): TurystaDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: AppDatabase) {
            val uzytkownikDAO = db.uzytkownikDao()
            val turystaDAO = db.turystaDao()
            val user1 = Uzytkownik(1, "abc", "abc", "abc", "abc", "abc", "2000-11-02", 1)
            Single.just(uzytkownikDAO.insert(user1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val turysta1 = Turysta(1, user1.id, 10, false)
            Single.just(turystaDAO.insert(turysta1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }


}