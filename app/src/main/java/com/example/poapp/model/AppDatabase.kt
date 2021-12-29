package com.example.poapp.model

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.poapp.model.dao.*
import com.example.poapp.model.entities.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Database(
    entities = [Uzytkownik::class, Dowod::class, DowodOdcinka::class, GrupaGorska::class, OdcinekOficjalny::class, OdcinekTrasy::class, OdcinekWlasny::class, Odznaka::class, PasmoGorskie::class, Pracownik::class, Przodownik::class, PunktOficjalny::class, PunktWlasny::class, Trasa::class, Turysta::class, UprawnieniaPrzodownika::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun uzytkownikDao(): UzytkownikDAO
    abstract fun turystaDao(): TurystaDAO
    abstract fun trasaDao(): TrasaDAO
    abstract fun odcinekOficjlanyDao(): OdcinekOficjalnyDAO
    abstract fun punktOficjalnyDao(): PunktOficjalnyDAO
    abstract fun odcinekTrasyDao(): OdcinekTrasyDAO

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
            val odcinekOficjalnyDAO = db.odcinekOficjlanyDao()
            val punktOficjalnyDAO = db.punktOficjalnyDao()
            val trasaDAO = db.trasaDao()
            val odcinekTrasyDao = db.odcinekTrasyDao()
            val user1 =
                Uzytkownik(1, "login", "password", "abc@gmail.com", "Jane", "Doe", "2000-11-02", 1)
            Single.just(uzytkownikDAO.insert(user1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val turysta1 = Turysta(1, user1.id, 10, false)
            Single.just(turystaDAO.insert(turysta1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val punkto1 = PunktOficjalny(1, "", 12.44543, 12.4323, 1)
            Single.just(punktOficjalnyDAO.insert(punkto1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val punkto2 = PunktOficjalny(2, "", 13.44543, 13.4323, 1)
            Single.just(punktOficjalnyDAO.insert(punkto2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val odcinek1 = OdcinekOficjalny(1, "", 10, 1, 2, null, 1)
            Single.just(odcinekOficjalnyDAO.insert(odcinek1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val trasa1 = Trasa(1, 1, "2021-11-12", "zaakceptowana", 10)
            Single.just(trasaDAO.insert(trasa1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
            val odTr = OdcinekTrasy(1, 1, null, 1, 120)
            Single.just(odcinekTrasyDao.insert(odTr))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }


}