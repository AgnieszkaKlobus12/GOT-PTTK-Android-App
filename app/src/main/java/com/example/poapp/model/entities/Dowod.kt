package com.example.poapp.model.entities

import androidx.room.ColumnInfo
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
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var zdjecie: ByteArray?,
    val FKprzodownik: Int
)