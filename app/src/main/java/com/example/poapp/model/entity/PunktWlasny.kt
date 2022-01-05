package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "PunktyWlasne", foreignKeys = [
        ForeignKey(
            entity = Turysta::class,
            parentColumns = ["nrKsiazeczki"],
            childColumns = ["FKturysta"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = PasmoGorskie::class,
            parentColumns = ["id"],
            childColumns = ["FKpasmoGorskie"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class PunktWlasny(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKturysta: Int,
    var nazwa: String,
    var szerokoscGeo: Double,
    var dlugoscGeo: Double,
    val FKpasmoGorskie: Int
)