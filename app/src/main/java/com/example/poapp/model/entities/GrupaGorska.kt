package com.example.poapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity (tableName = "GrupyGorskie")
data class GrupaGorska (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    var kraj: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var obszar: ByteArray?
)
