package com.example.poapp.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (
    tableName = "Pracownicy", foreignKeys = [
        ForeignKey(
            entity = Uzytkownik::class,
            parentColumns = ["id"],
            childColumns = ["FKuzytkownik"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Pracownik (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKuzytkownik: Int,
    var nazwaOrganizacji: String, //enum
    var dataZatrudnienia: String //YYYY-MM-DD
)