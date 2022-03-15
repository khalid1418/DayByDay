package com.khalid.daybyday.showListNoteUi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory


class ShowListNoteFragment : Fragment() {

    private var _binding:FragmentShowListNoteBinding? = null
    private val binding get() = _binding
    private val viewModel:ShowListNoteViewModel by activityViewModels {
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowListNoteBinding.inflate(inflater , container , false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addNoteFab?.setOnClickListener {
            val action = ShowListNoteFragmentDirections.actionShowListNoteFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }

        val adapter = NoteAdapter({viewModel.isFav(it)},{viewModel.delete(it)},{
            val action = ShowListNoteFragmentDirections.actionShowListNoteFragmentToDetailFragment(it.id , it.titleDate , it.description)
            findNavController().navigate(action)
        })
        binding?.recyclerView?.adapter = adapter
        viewModel.allNoteLiveData.observe(viewLifecycleOwner, {
            it.let {
                adapter.submitList(it)
            }
        })

        binding?.favList?.setOnClickListener {
            viewModel.favListLiveData.observe(viewLifecycleOwner,{
                it.let {
                    adapter.submitList(it)
                }
            })
        }

        binding?.search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                query.let {
                    viewModel.search(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false

            }


        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}