package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity (tableName = "GrupyGorskie")
data class GrupaGorska (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nazwa: String,
    var kraj: String,
    var obszar: Blob //is this a correct data type?
)
