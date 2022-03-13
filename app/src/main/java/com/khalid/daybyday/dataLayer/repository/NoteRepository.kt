package com.khalid.daybyday.dataLayer.repository

import com.khalid.daybyday.dataLayer.roomData.NoteDao
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) : Repository {
    override suspend fun addNote(noteDataModel: NoteDataModel) {
        noteDao.insert(noteDataModel)
    }

    override suspend fun showNote(): Flow<List<NoteDataModel?>?> {
        return noteDao.showNote()
    }

    override suspend fun favUpdate(noteDataModel: NoteDataModel) {
        noteDao.favUpdate(noteDataModel)
    }
}