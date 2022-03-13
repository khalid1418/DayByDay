package com.khalid.daybyday.dataLayer.repository

import com.khalid.daybyday.dataLayer.roomData.NoteDao
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel

class NoteRepository(private val noteDao: NoteDao) : Repository {
    override suspend fun addNote(noteDataModel: NoteDataModel) {
        noteDao.insert(noteDataModel)
    }
}