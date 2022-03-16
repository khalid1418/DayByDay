package com.khalid.daybyday.dataLayer.repository

import com.khalid.daybyday.dataLayer.roomData.NoteDataBase
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun addNote(noteDataModel: NoteDataModel)

    suspend fun showNote() : Flow<List<NoteDataModel?>?>
    suspend fun favUpdate(noteDataModel: NoteDataModel)
    suspend fun updateNote(noteDataModel: NoteDataModel)
    suspend fun favList():Flow<List<NoteDataModel?>?>
    suspend fun delete(noteDataModel: NoteDataModel)
    suspend fun search(date:String):Flow<List<NoteDataModel?>>
}