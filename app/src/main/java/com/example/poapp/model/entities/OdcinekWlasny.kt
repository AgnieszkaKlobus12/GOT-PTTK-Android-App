package com.example.poapp.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "OdcinkiWlasne") //dodać foreign keys
data class OdcinekWlasny(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val FKturysta: Int,
    var nazwa: String,
    var dlugosc: Int,
    var podejscie: Int,
    var FKpunktPoczatkowyWlasny: Int,
    val FKpunktKoncowyWsasny: Int,
    val FKpunktPosredniWlasny: Int,
    val FKpunktPoczatkowyOficjalny: Int,
    val FKpunktKoncowyOficjalny: Int,
    val FKpunktPosredniOficjalny: Int,
    val FKpasmoGorskie: Int
) {
    var punkty = podejscie / 100 + dlugosc / 1000
}

//CONSTRAINT POCZĄTEKwłasnyXORoficjalny
//CHECK ((OdcinkiWłasne.FKpunktPoczątkowyWłasny IS NULL OR OdcinkiWłasne.FKpunktPoczątkowyOficjalny IS NULL) AND (OdcinkiWłasne.FKpunktPoczątkowyWłasny IS NOT NULL OR OdcinkiWłasne.FKpunktPoczątkowyOficjalny IS NOT NULL)),
//CONSTRAINT KONIECwłasnyXORoficjalny
//CHECK ((OdcinkiWłasne.FKpunktKońcowyWłasny IS NULL OR OdcinkiWłasne.FKpunktKońcowyOficjalny IS NULL) AND (OdcinkiWłasne.FKpunktKońcowyWłasny IS NOT NULL OR OdcinkiWłasne.FKpunktKońcowyOficjalny IS NOT NULL)),
//CONSTRAINT POŚREDNIwłasnyNANDoficjalny
//CHECK (OdcinkiWłasne.FKpunktPośredniWłasny IS NOT NULL OR OdcinkiWłasne.FKpunktPośredniOficjalny IS NOT NULL));

