package com.khalid.daybyday.utils

import android.app.Application
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.dataLayer.roomData.NoteDataBase

class DayByDayApplication:Application() {
    val database:NoteDataBase by lazy { NoteDataBase.getDataBase(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}