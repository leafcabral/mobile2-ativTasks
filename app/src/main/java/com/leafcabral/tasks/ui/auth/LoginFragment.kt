package com.leafcabral.tasks.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.leafcabral.tasks.R
import com.leafcabral.tasks.databinding.FragmentLoginBinding
import com.leafcabral.tasks.util.showBottomSheet

class LoginFragment : Fragment() {

	private var _binding: FragmentLoginBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentLoginBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.buttonLogin.setOnClickListener {
			if (areInputsValid()) {
				findNavController().navigate(R.id.action_global_homeFragment)
			}
		}
		binding.buttonRegister.setOnClickListener {
			findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
		}
		binding.buttonRecover.setOnClickListener {
			findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


	private fun areInputsValid(): Boolean {
		val email = binding.inputEmail.text.toString().trim()
		val senha = binding.inputSenha.text.toString().trim()

		if (email.isBlank()) {
			showBottomSheet(message = getString(R.string.email_empty))
			return false
		}
		if (senha.isBlank()) {
			showBottomSheet(message = getString(R.string.password_empty))
			return false
		}

		return true
	}
}