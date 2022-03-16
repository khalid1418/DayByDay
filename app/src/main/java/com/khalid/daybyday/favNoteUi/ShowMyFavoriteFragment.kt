package com.khalid.daybyday.favNoteUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.khalid.daybyday.R
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding
import com.khalid.daybyday.databinding.FragmentShowMyFavoriteBinding
import com.khalid.daybyday.showListNoteUi.NoteAdapter
import com.khalid.daybyday.showListNoteUi.ShowListNoteFragmentDirections
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory


class ShowMyFavoriteFragment : Fragment() {
    private var _binding: FragmentShowMyFavoriteBinding? = null
    private val binding get() = _binding
    private val viewModel:ShowMyFavoriteViewModel by activityViewModels{
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentShowMyFavoriteBinding.inflate(inflater , container , false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NoteAdapter({viewModel.isFav(it)},{
            val action = ShowMyFavoriteFragmentDirections.actionShowMyFavoriteFragmentToDetailFragment(
                it.id,
                it.titleDate,
                it.description
            )
            findNavController().navigate(action)
        })
        binding?.recyclerView2?.adapter = adapter

        viewModel.favListLiveData.observe(viewLifecycleOwner) {
            it.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}