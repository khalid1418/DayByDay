package com.khalid.daybyday.dataLayer.roomData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteDataModel: NoteDataModel)

    @Query("SELECT * FROM NOTEDATAMODEL")
    fun showNote() : Flow<List<NoteDataModel?>?>
    @Update
    suspend fun favUpdate(noteDataModel: NoteDataModel)

    @Update
    suspend fun updateNote(noteDataModel: NoteDataModel)

}