package com.khalid.daybyday.addNoteUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository):ViewModel() {


    private fun insert(dataModel: NoteDataModel){
       viewModelScope.launch {
           repository.addNote(dataModel)
       }
    }



    private fun getNewTaskEntry(
        title: String,
        description: String,
    ): NoteDataModel {
        return NoteDataModel(
            titleDate = title,
            description = description,

        )

    }

    fun addNewProduct(
        title: String,
        description: String,

    ) {
        val newTask = getNewTaskEntry(title, description)
        insert(newTask)

    }

    fun isEntryValid(
        title: String,
        description: String,
    ): Boolean {
        if (title.isBlank() || description.isBlank()) {
            return false
        }
        return true
    }
}
