@file:Suppress("WildcardImport")

package com.example.taskmanager.presentation.signup


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentSignUpBinding
import com.example.taskmanager.presentation.common.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCollects()
    }

    private fun setupClickListeners() = with(binding) {
        tvSignIn.setOnClickListener {
            viewModel.goToSignIn()
        }
        btnSignUp.setOnClickListener {
            viewModel.signUp(
                etEmail.toStringTrim(),
                etPassword.toStringTrim(),
                etRepeatPassword.toStringTrim(),
                etName.toStringTrim()
            )
        }
    }

    private fun setupCollects() {
        collectFlow(viewModel.signUpResultEvent) {
            it.apply(context)
        }

        val progressBar = binding.loadingProgressBar
        collectFlow(viewModel.isLoading) { isLoading ->
            if (isLoading) progressBar.visible() else progressBar.gone()
        }
    }
}
