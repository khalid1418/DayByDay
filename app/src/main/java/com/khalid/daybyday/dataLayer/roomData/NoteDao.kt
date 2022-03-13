package com.khalid.daybyday.dataLayer.roomData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteDataModel: NoteDataModel)

    @Query("SELECT * FROM NOTEDATAMODEL")
    fun showNote() : Flow<List<NoteDataModel?>?>
}