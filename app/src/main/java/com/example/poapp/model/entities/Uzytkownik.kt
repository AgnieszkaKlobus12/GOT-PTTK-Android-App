package com.example.poapp.model.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Entity (tableName = "Uzytkownicy")
data class Uzytkownik (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var login: String,
    var haslo: String,
    var email: String,
    var imie: String,
    var nazwisko: String,
    var dataUrodzenia: String, //YYYY-MM-DD
    var rola: Int // 0-turysta, 1-przodownik, 2-pracownik, 3-członek
    ) {
    @RequiresApi(Build.VERSION_CODES.O)
    @ColumnInfo(name = "wiek") var wiek = Duration.between(Instant.now(),LocalDateTime.parse(dataUrodzenia, DateTimeFormatter.ofPattern("u-M-d")).atOffset(ZoneOffset.UTC).toInstant()).toDays()/365
}