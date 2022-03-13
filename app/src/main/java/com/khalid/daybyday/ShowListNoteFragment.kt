package com.khalid.daybyday

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding


class ShowListNoteFragment : Fragment() {

    private var _binding:FragmentShowListNoteBinding? = null
    private val binding get() = _binding


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
    }
}