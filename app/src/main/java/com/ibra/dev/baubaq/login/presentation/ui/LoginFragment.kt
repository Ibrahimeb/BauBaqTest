package com.ibra.dev.baubaq.login.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibra.dev.baubaq.databinding.FragmentLoginBinding
import com.ibra.dev.baubaq.login.presentation.contract.FragmentLoginEvents
import com.ibra.dev.baubaq.login.presentation.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (viewModel.isAlreadyLogin()) {
            findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
        textWatcher()
        onClickListener()
    }

    private fun subscribeLiveData() {
        viewModel.eventsLiveDataLiveData.observe(viewLifecycleOwner, { event ->
            when (event) {
                FragmentLoginEvents.LaunchVerifyCredentials -> binding.apply {
                    viewModel.verifyCredentials(
                        textFieldUser.text.toString(),
                        textFieldPassword.text.toString()
                    )
                }
                is FragmentLoginEvents.SendErrorMessage -> Toast.makeText(
                    requireContext(),
                    getText(event.msg),
                    Toast.LENGTH_SHORT
                ).show()
                FragmentLoginEvents.GoToHome -> findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        })
    }

    private fun textWatcher() {
        binding.apply {
            textFieldUser.doAfterTextChanged {
                viewModel.userTextFieldValid = it?.isNotBlank() == true
            }
            textFieldPassword.doAfterTextChanged {
                viewModel.passwordTextFieldValid = it?.isNotBlank() == true
            }
        }
    }

    private fun onClickListener() {
        binding.apply {
            materialButtonLogin.setOnClickListener {
                viewModel.canVerifyCredentials()
            }
        }
    }
}