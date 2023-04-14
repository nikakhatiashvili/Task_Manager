package com.example.taskmanager.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.databinding.FragmentTribeDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogFragment : DialogFragment() {

    private var _binding: FragmentTribeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTribeDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupClicks()
        return root
    }

    private fun setupClicks() {
        binding.create.setOnClickListener {
            val name = binding.etEmail.editText?.text.toString()
            val description = binding.etDescription.editText?.text.toString()
            val result = Bundle().apply {
                putString("name", name)
                putString("description", description)
            }
            parentFragmentManager.setFragmentResult("createTribe", result)
            dismiss()
        }
    }
}
