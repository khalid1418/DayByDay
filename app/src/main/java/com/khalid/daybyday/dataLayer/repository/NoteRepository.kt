package com.khalid.daybyday.dataLayer.repository

import android.util.Log
import com.khalid.daybyday.dataLayer.roomData.NoteDao
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override suspend fun updateNote(noteDataModel: NoteDataModel) {
        noteDao.updateNote(noteDataModel)
    }

    override suspend fun favList(): Flow<List<NoteDataModel?>?> {
       return noteDao.favNote()
    }


    override suspend fun delete(noteDataModel: NoteDataModel) {
    noteDao.delete(noteDataModel)
    }

    override suspend fun search(date: String): Flow<List<NoteDataModel?>> = flow {
        showNote().collect{ list ->
            emit(list!!.filter { it?.titleDate!!.contains(date,ignoreCase = true) })
        }
    }
}