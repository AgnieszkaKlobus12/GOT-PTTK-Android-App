package com.example.poapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity (
    tableName = "PasmaGorskie", foreignKeys = [
        ForeignKey(
            entity = GrupaGorska::class,
            parentColumns = ["id"],
            childColumns = ["FKgrupaGorska"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class PasmoGorskie (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var obszar: ByteArray?,
    val FKgrupaGorska: Int
)
