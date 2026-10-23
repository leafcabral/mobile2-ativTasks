package com.leafcabral.tasks.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.leafcabral.tasks.R
import com.leafcabral.tasks.databinding.FragmentRegisterBinding
import com.leafcabral.tasks.util.initToolbar
import com.leafcabral.tasks.util.showBottomSheet

class RegisterFragment : Fragment() {

	private var _binding: FragmentRegisterBinding? = null
	private val binding get() = _binding!!

	private lateinit var auth: FirebaseAuth

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentRegisterBinding.inflate(inflater, container, false)
		auth = Firebase.auth

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initToolbar(binding.toolbar)

		binding.buttonRegister.setOnClickListener {
			validateAndRegister()
		}
	}

	private fun validateAndRegister() {
		val email = binding.inputEmail.text.toString().trim()
		val password = binding.inputSenha.text.toString().trim()

		if (email.isBlank()) {
			showBottomSheet(message = getString(R.string.register_email_empty))
			return
		}
		if (password.isBlank()) {
			showBottomSheet(message = getString(R.string.register__password_empty))
			return
		}

		registerUser(email, password)
	}

	private fun registerUser(email: String, password: String) {
		binding.progressBar.isVisible = true

		auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
			try {
				val exception = task.exception
				if (!task.isSuccessful && exception != null) {
					throw Exception(exception.message)
				}

				findNavController().navigate(R.id.action_global_homeFragment)
			} catch (e: Exception) {
				Toast.makeText(
					requireContext(),
					e.message.toString(),
					Toast.LENGTH_SHORT
				).show()
			} finally {
				binding.progressBar.isVisible = false
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}