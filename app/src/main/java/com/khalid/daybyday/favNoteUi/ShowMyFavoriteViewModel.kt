package com.khalid.daybyday.favNoteUi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.khalid.daybyday.dataLayer.repository.NoteRepository
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShowMyFavoriteViewModel(val repository: NoteRepository):ViewModel() {

    val _favList = MutableStateFlow<List<NoteDataModel?>?>(emptyList())
    val favList: StateFlow<List<NoteDataModel?>?> = _favList.asStateFlow()
    val favListLiveData = favList.asLiveData()


    init {
        showFev()
    }

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
}