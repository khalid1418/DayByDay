package com.khalid.daybyday.dataLayer.roomData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteDataModel::class] , version = 3 , exportSchema = false)
abstract class NoteDataBase():RoomDatabase(){
    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDataBase?=null
        fun getDataBase(context: Context):NoteDataBase{
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext , NoteDataBase::class.java , "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}