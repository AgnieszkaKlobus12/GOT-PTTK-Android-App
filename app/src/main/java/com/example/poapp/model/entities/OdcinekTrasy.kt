package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "OdcinkiTras", foreignKeys = [
        ForeignKey(
            entity = Trasa::class,
            parentColumns = ["id"],
            childColumns = ["FKtrasa"],
            onDelete = ForeignKey.RESTRICT
        )//, ForeignKey() odcinek własny i oficjalny
    ]
)
data class OdcinekTrasy(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKtrasa: Int,
    val FKodcinekWlasny: Int,
    val FKodcinekOficjalny: Int,
    var czasPrzejscia: Int
)

//    CHECK ((OdcinkiTras.FKodcinekWłasny IS NULL OR OdcinkiTras.FKodcinekOficjalny IS NULL) AND
//    (OdcinkiTras.FKodcinekWłasny IS NOT NULL OR OdcinkiTras.FKodcinekOficjalny IS NOT NULL)));