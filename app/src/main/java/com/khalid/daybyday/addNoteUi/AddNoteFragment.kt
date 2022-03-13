package com.khalid.daybyday.addNoteUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.khalid.daybyday.databinding.FragmentAddNoteBinding
import com.khalid.daybyday.utils.DayByDayApplication
import com.khalid.daybyday.utils.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment : Fragment() {
private var _binding:FragmentAddNoteBinding?=null
    private val binding get() = _binding
    private var datePick = ""
    private val viewModel:AddNoteViewModel by activityViewModels{
        ViewModelFactory((activity?.application as DayByDayApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater , container ,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.showdate?.setOnClickListener {
            showDatePicker()
        }
        binding?.save?.setOnClickListener{
            addNewTask()
        }
    }

    fun showDatePicker() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            datePick = convertMillisecondsToReadableDate(it, "YYYY, MMMM dd ")
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
    private fun addNewTask() {
        if (isEntryValid()) {
            viewModel.addNewProduct(
                binding?.date?.text.toString(),
                binding?.descriotion?.text.toString(),

            )
            val action =AddNoteFragmentDirections.actionAddNoteFragmentToShowListNoteFragment()
            findNavController().navigate(action)
        }
    }

}