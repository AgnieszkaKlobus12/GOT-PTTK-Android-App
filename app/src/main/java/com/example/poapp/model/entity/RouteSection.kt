package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "OdcinkiTras", foreignKeys = [
        ForeignKey(
            entity = Route::class,
            parentColumns = ["id"],
            childColumns = ["FKtrasa"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = MountainPassUser::class,
            parentColumns = ["id"],
            childColumns = ["FKodcinekWlasny"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = MountainPassOfficial::class,
            parentColumns = ["id"],
            childColumns = ["FKodcinekOficjalny"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class RouteSection(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKtrasa: Int,
    val FKodcinekWlasny: Int?,
    val FKodcinekOficjalny: Int?,
    var czasPrzejscia: Int
)

//    CHECK ((OdcinkiTras.FKodcinekWłasny IS NULL OR OdcinkiTras.FKodcinekOficjalny IS NULL) AND
//    (OdcinkiTras.FKodcinekWłasny IS NOT NULL OR OdcinkiTras.FKodcinekOficjalny IS NOT NULL)));
