package com.example.poapp.model.entities

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
    var obszar: Blob, //is this a correct data type?
    val FKgrupaGorska: Int
)
