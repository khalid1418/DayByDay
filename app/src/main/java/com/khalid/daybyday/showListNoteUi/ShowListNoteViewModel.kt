package com.khalid.daybyday.showListNoteUi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShowListNoteViewModel(val repository: NoteRepository):ViewModel() {


    val _allNotes= MutableStateFlow<List<NoteDataModel?>?>(emptyList())
    val allNotes: StateFlow<List<NoteDataModel?>?> = _allNotes.asStateFlow()
    val allNoteLiveData = allNotes.asLiveData()


    init {
        showNote()
    }

    fun showNote(){
        viewModelScope.launch {
            repository.showNote().collect{ task ->
                _allNotes.update { task }
                Log.e("TAG", "showNote:${_allNotes.value} ", )
            }
        }
    }
    fun favUpdate(noteDataModel: NoteDataModel){
        viewModelScope.launch {
            repository.favUpdate(noteDataModel)
        }
    }
    fun isFav (noteDataModel: NoteDataModel) {
        noteDataModel.isFav = true
        favUpdate(noteDataModel)

    }

}