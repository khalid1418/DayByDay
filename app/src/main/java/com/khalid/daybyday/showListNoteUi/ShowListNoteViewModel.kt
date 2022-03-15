package com.khalid.daybyday.showListNoteUi

import android.util.Log
import androidx.lifecycle.LiveData
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


    val _favList = MutableStateFlow<List<NoteDataModel?>?>(emptyList())
    val favList:StateFlow<List<NoteDataModel?>?> = _favList.asStateFlow()
    val favListLiveData = favList.asLiveData()

//    val _search = MutableStateFlow<List<NoteDataModel?>?>(emptyList())
//    val search :StateFlow<List<NoteDataModel?>?> = _search.asStateFlow()
//    val searchLiveData = search.asLiveData()

    init {
        showNote()
        showFev()
    }

    fun showNote(){
        viewModelScope.launch {
            repository.showNote().collect{ task ->
                _allNotes.update { task }
                Log.e("TAG", "showNote:${_allNotes.value} ", )
            }
        }
    }
//
    fun showFev(){
        viewModelScope.launch {
            repository.favList().collect{ favTask ->
                _favList.update { favTask }
                Log.d("TAG", "showFev: $_favList ")
            }
        }
    }


    fun favUpdate(noteDataModel: NoteDataModel){
        viewModelScope.launch {
            repository.favUpdate(noteDataModel)
        }
    }
    fun isFav (noteDataModel: NoteDataModel) {
        if (noteDataModel.isFav){
            noteDataModel.isFav = false
        }else if (!noteDataModel.isFav)
        {noteDataModel.isFav = true}

        favUpdate(noteDataModel)

    }
    fun delete(noteDataModel: NoteDataModel){
        viewModelScope.launch {
            repository.delete(noteDataModel)
        }
    }

    fun search(data: String){
        viewModelScope.launch {
                repository.search(data).collect{ searchTask ->
                    _allNotes.update { searchTask }
                }
        }
    }

}