package com.khalid.daybyday.showListNoteUi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khalid.daybyday.R
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import com.khalid.daybyday.databinding.NoteItemBinding

class NoteAdapter(private val onFavClicked:(NoteDataModel) -> Unit ,private val onDeleteClick:(NoteDataModel) -> Unit, private val onItemClicked: (NoteDataModel) -> Unit) :
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

    class NoteViewHolder(var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataModel: NoteDataModel) {
            binding.apply {

                title.text = dataModel.titleDate
                description.text = dataModel.description
                if (dataModel.isFav){
                    fav.playAnimation()
                }else{
                    fav.frame=1
                }




            }

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteAdapter.NoteViewHolder {
        return NoteAdapter.NoteViewHolder(
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        Log.e("TAG", "onBindViewHolder: ${current.titleDate}", )
        holder.itemView.setOnClickListener {
            onItemClicked(current)

        }
        Log.e("TAG", "onBindViewHolder:${current.isFav} ", )
        holder.binding.fav.setOnClickListener {
            if (!current.isFav){
                holder.binding.fav.playAnimation()

            }else if (current.isFav==true){
                holder.binding.fav.frame=1
            }
            onFavClicked(current)
        }
//        holder.binding.delete.setOnClickListener {
//            onDeleteClick(current)
//        }

    }
}