package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "OdcinkiOficjalne", foreignKeys = [
        ForeignKey(
            entity = Trasa::class,
            parentColumns = ["id"],
            childColumns = ["FKtrasa"],
            onDelete = ForeignKey.RESTRICT
        )//, ForeignKey() odcinek własny i oficjalny
    ]
)
data class OdcinekOficjalny(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    var punkty: Int,
    var FKpunktPoczatkowy: Int,
    var FKpunktKoncowy: Int,
    var FKpunktPosredni: Int,
    var FKpasmoGorskie: Int
)
