package com.khalid.daybyday.dataLayer.repository

import com.khalid.daybyday.dataLayer.roomData.NoteDataBase
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel

interface Repository {
    suspend fun addNote(noteDataModel: NoteDataModel)
}