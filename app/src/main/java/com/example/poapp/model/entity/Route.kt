package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Trasy", foreignKeys = [
        ForeignKey(
            entity = Tourist::class,
            parentColumns = ["nrKsiazeczki"],
            childColumns = ["FKturysta"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Route(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val FKturysta: Int,
    var dataPrzejscia: String, //YYYY-MM-DD
    var status: String, //enum
    var punkty: Int
)
