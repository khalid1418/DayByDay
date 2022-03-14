package com.khalid.daybyday.dataLayer.roomData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteDataModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo
    val titleDate:String,
    @ColumnInfo
    val description:String,
    @ColumnInfo
    var isFav:Boolean=false
)
