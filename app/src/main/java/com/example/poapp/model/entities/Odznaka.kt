package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Odznaki", foreignKeys = [
        ForeignKey(
            entity = Turysta::class,
            parentColumns = ["nrKsiazeczki"],
            childColumns = ["FKturysta"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Odznaka (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKturysta: Int,
    var wymaganePunkty: Int,
    var dataPrzyznania: String, //YYYY-MM-DD
    var rodzaj: String, //enum
    var stopien: String //enum
)