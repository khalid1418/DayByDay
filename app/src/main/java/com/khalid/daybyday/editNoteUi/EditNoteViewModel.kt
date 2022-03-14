package com.khalid.daybyday.editNoteUi

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.launch

class EditNoteViewModel(val repository: NoteRepository) : ViewModel() {

    fun updateNote(noteDataModel: NoteDataModel) {
        viewModelScope.launch {
            repository.updateNote(noteDataModel)
            Log.e("TAG", "updateNote: ${noteDataModel}", )
        }
    }


    private fun getNoteEntry(
        title: String,
        description: String,
            id:Int
    ): NoteDataModel {
        return NoteDataModel(
            titleDate = title,
            description = description,
            id = id

            )

    }

    fun editProduct(
        title: String,
        description: String,
        id: Int

        ) {
        val newTask = getNoteEntry(title, description ,id)
        updateNote(newTask)

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
