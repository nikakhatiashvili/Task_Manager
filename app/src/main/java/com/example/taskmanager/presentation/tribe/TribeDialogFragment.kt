package com.example.taskmanager.presentation.tribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.presentation.common.toast
import com.example.taskmanager.R
import com.example.taskmanager.databinding.TribeDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TribeDialogFragment : DialogFragment() {

    private var _binding: TribeDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TribeDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupClicks()
        return root
    }

    private fun setupClicks() {
        binding.create.setOnClickListener {
            val desc = binding.etDescription.editText?.text
            val name = binding.etName.editText?.text
            if (desc.isNullOrEmpty() || name.isNullOrEmpty()) {
                requireContext().toast(requireContext().getString(R.string.All_fields_must_me_full))
            } else {
                val result = Bundle().apply {
                    putString("name", name.toString())
                    putString("description", desc.toString())
                }
                parentFragmentManager.setFragmentResult("createTask", result)
                dismiss()
            }
        }
    }
}
