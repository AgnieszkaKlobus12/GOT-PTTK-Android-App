package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "PunktyOficjalne", foreignKeys = [
        ForeignKey(
            entity = PasmoGorskie::class,
            parentColumns = ["id"],
            childColumns = ["FKpasmoGorskie"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class PunktOficjalny (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    var szerokoscGeo: Double,
    var dlugoscGeo: Double,
    val FKpasmoGorskie: Int
)
