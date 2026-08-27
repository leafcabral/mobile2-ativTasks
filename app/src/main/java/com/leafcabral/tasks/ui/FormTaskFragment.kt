package com.leafcabral.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.leafcabral.tasks.R
import com.leafcabral.tasks.databinding.FragmentFormTaskBinding
import com.leafcabral.tasks.databinding.FragmentRecoverAccountBinding
import com.leafcabral.tasks.util.initToolbar


class FormTaskFragment : Fragment() {

	private var _binding: FragmentFormTaskBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentFormTaskBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initToolbar(binding.toolbar)

		binding.buttonSalvar.setOnClickListener {
			if (areInputsValid()) {
				Toast.makeText(requireContext(), "Executa OK", Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


	private fun areInputsValid(): Boolean {
		val description = binding.inputDescricao.text.toString().trim()

		if (description.isBlank()) {
			Toast.makeText(requireContext(), "Preencha uma descrição", Toast.LENGTH_SHORT).show()
			return false
		}

		return true
	}
}