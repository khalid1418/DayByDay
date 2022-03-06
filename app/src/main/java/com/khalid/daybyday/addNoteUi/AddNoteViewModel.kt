package com.khalid.daybyday.addNoteUi

import androidx.lifecycle.ViewModel

class AddNoteViewModel:ViewModel() {



//    private fun getNewTaskEntry(
//        title: String,
//        description: String,
//        date: String,
//    ): DataModel {
//        return DataModel(
//            title = title,
//            description = description,
//            datetask = date
//        )
//
//    }
//
//    fun addNewProduct(
//        title: String,
//        description: String,
//        date: String,
//    ) {
//        val newTask = getNewTaskEntry(title, description, date)
//        insert(newTask)
//
//    }

    fun isEntryValid(
        title: String,
        description: String,
        date: String,
    ): Boolean {
        if (title.isBlank() || description.isBlank() || date.isBlank()) {
            return false
        }
        return true
    }
}
