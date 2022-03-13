package com.khalid.daybyday.showListNoteUi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import com.khalid.daybyday.databinding.NoteItemBinding

class NoteAdapter(private val onItemClicked: (NoteDataModel) -> Unit) :
    ListAdapter<NoteDataModel, NoteAdapter.NoteViewHolder>(DiffCallback) {


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NoteDataModel>() {
            override fun areItemsTheSame(
                oldItem: NoteDataModel,
                newItem: NoteDataModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: NoteDataModel,
                newItem: NoteDataModel
            ): Boolean {
                return oldItem.id == newItem.id

            }

        }

    }

    class NoteViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataModel: NoteDataModel) {
            binding.apply {

                title.text = dataModel.titleDate
                description.text = dataModel.description

            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        Log.e("TAG", "onBindViewHolder: ${current.titleDate}", )
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }


    }
}