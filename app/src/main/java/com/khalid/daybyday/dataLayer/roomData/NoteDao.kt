package com.khalid.daybyday.dataLayer.roomData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteDataModel: NoteDataModel)

    @Delete
    suspend fun delete(noteDataModel: NoteDataModel)

    @Query("SELECT * FROM NOTEDATAMODEL")
    fun showNote() : Flow<List<NoteDataModel?>?>

    @Query("SELECT * FROM NOTEDATAMODEL WHERE isFav = 1")
    fun favNote():Flow<List<NoteDataModel?>?>

    @Update
    suspend fun updateNote(noteDataModel: NoteDataModel)


    @Update
    suspend fun favUpdate(noteDataModel: NoteDataModel)

//    @Query("SELECT * FROM NOTEDATAMODEL WHERE description = :date")
//    fun search(date:String):Flow<List<NoteDataModel?>?>
}