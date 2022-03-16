package com.khalid.daybyday.showListNoteUi

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.khalid.daybyday.R
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding
import com.khalid.daybyday.favNoteUi.ShowMyFavoriteFragmentDirections
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory


class ShowListNoteFragment : Fragment() {

    private var _binding: FragmentShowListNoteBinding? = null
    private val binding get() = _binding
    private val viewModel: ShowListNoteViewModel by activityViewModels {
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }
    val adapter = NoteAdapter({viewModel.isFav(it) }, { viewModel.delete(it) }, {
        val action = ShowListNoteFragmentDirections.actionShowListNoteFragmentToDetailFragment(
            it.id,
            it.titleDate,
            it.description
        )
        findNavController().navigate(action)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowListNoteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addNoteFab?.setOnClickListener {
            val action =
                ShowListNoteFragmentDirections.actionShowListNoteFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }


        binding?.recyclerView?.adapter = adapter
        viewModel.allNoteLiveData.observe(viewLifecycleOwner) {
            it.let {
                adapter.submitList(it)
            }
        }

        binding?.search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.search(it)
                }
                return true

            }


        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu , menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.myfav ->{
                val action = ShowListNoteFragmentDirections.actionShowListNoteFragmentToShowMyFavoriteFragment()
                findNavController().navigate(action)

            }
        }
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}