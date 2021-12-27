package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(
    tableName = "Dowody", foreignKeys = [
        ForeignKey(
            entity = Przodownik::class,
            parentColumns = ["nrLegitymacji"],
            childColumns = ["FKprzodownik"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Dowod (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var zdjecie: Blob, //is this a correct data type?
    val FKprzodownik: Int
)