package com.leafcabral.tasks.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.leafcabral.tasks.R
import com.leafcabral.tasks.databinding.FragmentRegisterBinding
import com.leafcabral.tasks.util.initToolbar
import com.leafcabral.tasks.util.showBottomSheet

class RegisterFragment : Fragment() {

	private var _binding: FragmentRegisterBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentRegisterBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initToolbar(binding.toolbar)

		binding.buttonRegister.setOnClickListener {
			if (areInputsValid()) {
				Toast.makeText(requireContext(), "Compila OK", Toast.LENGTH_SHORT).show()
			}
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
			showBottomSheet(message = getString(R.string.register_email_empty))
			return false
		}
		if (senha.isBlank()) {
			showBottomSheet(message = getString(R.string.register__password_empty))
			return false
		}

		return true
	}
}