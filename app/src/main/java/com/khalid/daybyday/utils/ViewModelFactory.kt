package com.khalid.daybyday.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khalid.daybyday.addNoteUi.AddNoteViewModel
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.editNoteUi.EditNoteViewModel
import com.khalid.daybyday.favNoteUi.ShowMyFavoriteViewModel
import com.khalid.daybyday.showListNoteUi.ShowListNoteViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(val repository: NoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AddNoteViewModel::class.java) ->{
                @Suppress("UNCHECKED_CAST")
            return AddNoteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ShowListNoteViewModel::class.java)->{
                @Suppress("UNCHECKED_CAST")
                return ShowListNoteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EditNoteViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return EditNoteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ShowMyFavoriteViewModel::class.java)->{
                @Suppress("UNCHECKED_CAST")
                return ShowMyFavoriteViewModel(repository) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}