package com.khalid.daybyday.detailNoteUi

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.khalid.daybyday.R
import com.khalid.daybyday.dataLayer.roomData.NoteDataModel
import com.khalid.daybyday.databinding.FragmentDetailBinding
import com.khalid.daybyday.databinding.FragmentShowListNoteBinding
import com.khalid.daybyday.showListNoteUi.ShowListNoteViewModel
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val navArgument: DetailFragmentArgs by navArgs()
    private val viewModel: ShowListNoteViewModel by activityViewModels {
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        return binding?.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_note_fragment_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    alltask()
        (activity as AppCompatActivity).supportActionBar?.title = "Details ${navArgument.title}"


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_delete -> confirmItemDelete()
        }
        return super.onOptionsItemSelected(item)
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

    private fun confirmItemDelete() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.delete(NoteDataModel(navArgument.id,navArgument.title,navArgument.description))
            Toast.makeText(requireContext(),"Successfully Removed '${navArgument.title}'",
                Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete'${navArgument.title}' ?")
        builder.setMessage("Are you sure you want remove '${navArgument.title}' ?")
        builder.create().show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}