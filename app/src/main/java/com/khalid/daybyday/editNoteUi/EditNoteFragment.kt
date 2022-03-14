package com.khalid.daybyday.editNoteUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.khalid.daybyday.databinding.FragmentEditNoteBinding
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class EditNoteFragment : Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding
    private val navArgument: EditNoteFragmentArgs by navArgs()
    private val viewModel:EditNoteViewModel by activityViewModels {
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentEditNoteBinding.inflate(inflater , container , false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.date?.setText(navArgument.title)
        binding?.descriotion?.setText(navArgument.description)
        binding?.showdate?.setOnClickListener {
            showDatePicker()
        }
        binding?.save?.setOnClickListener {
            editNote()
        }
    }
    fun showDatePicker() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            var datePick = convertMillisecondsToReadableDate(it, "YYYY, MMMM dd ")
            binding?.date?.setText(datePick)


        }

    }
    private fun convertMillisecondsToReadableDate(
        dateMilliseconds: Long,
        datePattern: String
    ): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(dateMilliseconds))

    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding?.date?.text.toString(),
            binding?.descriotion?.text.toString()
        )
    }
    private fun editNote() {
        if (isEntryValid()) {
            viewModel.editProduct(
                binding?.date?.text.toString(),
                binding?.descriotion?.text.toString(),
                navArgument.id

                )
            val action = EditNoteFragmentDirections.actionEditNoteFragmentToShowListNoteFragment()
            findNavController().navigate(action)
        }
    }

}