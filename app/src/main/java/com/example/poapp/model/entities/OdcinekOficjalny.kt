package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    tableName = "OdcinkiOficjalne", foreignKeys = [
        ForeignKey(
            entity = PunktOficjalny::class,
            parentColumns = ["id"],
            childColumns = ["FKpunktPoczatkowy"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = PunktOficjalny::class,
            parentColumns = ["id"],
            childColumns = ["FKpunktKoncowy"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = PunktOficjalny::class,
            parentColumns = ["id"],
            childColumns = ["FKpunktPosredni"],
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
data class OdcinekOficjalny(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    var punkty: Int,
    var FKpunktPoczatkowy: Int,
    var FKpunktKoncowy: Int,
    var FKpunktPosredni: Int?,
    var FKpasmoGorskie: Int
)
