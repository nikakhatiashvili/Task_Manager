package com.example.taskmanager.presentation.groups

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.taskmanager.R
import com.example.taskmanager.common.Result
import com.example.taskmanager.databinding.DialogInviteBinding
import com.example.taskmanager.databinding.TribeBottomSheetBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TribeBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: GroupsViewModel by viewModels()

    private val binding by viewBinding(TribeBottomSheetBinding::bind)

    companion object {
        private const val ARG_GROUP_ID = "groupIdToLeave"
        const val TAG = "TribeBottomSheet"
        fun newInstance(groupId: Long): TribeBottomSheet {
            val args = Bundle()
            args.putLong(ARG_GROUP_ID, groupId)

            val fragment = TribeBottomSheet()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tribe_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnInviteFriend.setOnClickListener {
            showDialog()
        }
        binding.btnLeaveGroup.setOnClickListener {
            val groupId = arguments?.getLong(ARG_GROUP_ID) ?: return@setOnClickListener
            viewModel.leaveGroup(groupId)
        }

        collectFlow(viewModel.leaveGroupState) { res ->
            if (res != null) {
                when (res) {
                    is Result.ApiSuccess -> {
                        Toast.makeText(
                            requireContext(),
                            res.data.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.getGroups()
                        dismiss()
                    }

                    is Result.ApiError -> {
                        Toast.makeText(
                            requireContext(),
                            res.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        dismiss()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun showDialog() {
        val dialogView = DialogInviteBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView.root)
            .create()

        collectFlow(viewModel.inviteState) { res ->
            if (res != null) {
                when (res) {
                    is Result.ApiSuccess -> {
                        Toast.makeText(
                            requireContext(),
                            res.data.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                        dismiss()
                    }

                    is Result.ApiError -> {
                        Toast.makeText(
                            requireContext(),
                            "there was an error inviting user",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialog.dismiss()
                        dismiss()
                    }

                    else -> {}
                }
            }
        }

        dialogView.btnInvite.setOnClickListener {
            val text = dialogView.etEmail.editText!!.text.toString()
            viewModel.inviteUser(text)
        }

        dialogView.btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}

