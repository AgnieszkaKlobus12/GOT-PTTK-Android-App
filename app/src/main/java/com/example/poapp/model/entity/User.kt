package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Uzytkownicy")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var login: String,
    var haslo: String,
    var email: String,
    var imie: String,
    var nazwisko: String,
    var dataUrodzenia: String, //YYYY-MM-DD
    var rola: Int // 0-turysta, 1-przodownik, 2-pracownik, 3-członek
) {
//    @RequiresApi(Build.VERSION_CODES.O)
//    @ColumnInfo(name = "wiek") var wiek = Duration.between(Instant.now(),LocalDateTime.parse(dataUrodzenia, DateTimeFormatter.ofPattern("uuuu-MM-dd")).atOffset(ZoneOffset.UTC).toInstant()).toDays()/365
}