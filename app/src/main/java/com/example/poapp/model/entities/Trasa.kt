package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "Trasy", foreignKeys = [
        ForeignKey(
            entity = Turysta::class,
            parentColumns = ["nrKsiazeczki"],
            childColumns = ["FKturysta"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Trasa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKturysta: Int,
    var dataPrzejscia: String, //YYYY-MM-DD
    var status: String, //enum???
    var punkty: Int
)
