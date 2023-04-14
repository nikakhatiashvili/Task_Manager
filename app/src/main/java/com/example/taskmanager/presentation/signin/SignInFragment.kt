package com.example.taskmanager.presentation.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentSignInBinding
import com.example.taskmanager.presentation.common.collectFlow
import com.example.taskmanager.presentation.common.gone
import com.example.taskmanager.presentation.common.viewBinding
import com.example.taskmanager.presentation.common.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupClickListeners() = with(binding) {
        tvSignUp.setOnClickListener {
            viewModel.goToSignUp()
        }
        btnSignIn.setOnClickListener {
            viewModel.signIn(
                etEmail.editText?.text.toString(),
                etPassword.editText?.text.toString()
            )
        }
    }

    private fun setupCollects() {
        collectFlow(viewModel.signInResultEvent) {
            it.apply(context)
        }
        collectFlow(viewModel.isLoading) {
            if (it) {
                binding.loadingProgressBar.visible()
                binding.btnSignIn.isEnabled = false
            } else {
                binding.btnSignIn.isEnabled = true
                binding.loadingProgressBar.gone()
            }
        }
    }
}
