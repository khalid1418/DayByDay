package com.khalid.daybyday.detailNoteUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khalid.daybyday.R
import com.khalid.daybyday.databinding.FragmentDetailBinding
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val navArgument: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    alltask()
    }

    fun alltask() {
        binding?.title?.text = navArgument.title
        binding?.description?.text = navArgument.description
        binding?.edit?.setOnClickListener {
            complete()
        }

    }
    fun complete(){
        val action = DetailFragmentDirections.actionDetailFragmentToEditNoteFragment(navArgument.id , navArgument.title , navArgument.description)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}