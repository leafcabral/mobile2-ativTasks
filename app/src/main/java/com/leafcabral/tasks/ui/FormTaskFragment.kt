package com.leafcabral.tasks.ui

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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.leafcabral.tasks.R
import com.leafcabral.tasks.data.model.Status
import com.leafcabral.tasks.data.model.Task
import com.leafcabral.tasks.databinding.FragmentFormTaskBinding
import com.leafcabral.tasks.util.DatabaseManager
import com.leafcabral.tasks.util.initToolbar
import com.leafcabral.tasks.util.showBottomSheet

class FormTaskFragment : Fragment() {

	private var _binding: FragmentFormTaskBinding? = null
	private val binding get() = _binding!!

	private lateinit var auth: FirebaseAuth
	private lateinit var database: DatabaseReference

	private var status = Status.TODO
	// private var newTask = true

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentFormTaskBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initToolbar(binding.toolbar)

		auth = Firebase.auth
		database = Firebase.database.reference

		binding.apply {
			buttonSalvar.setOnClickListener {
				validateAndCreateTask()
			}
			
			radioGroup.setOnCheckedChangeListener { _, id ->
				status = when (id) {
					R.id.radio_todo -> {Status.TODO}
					R.id.radio_doing -> {Status.DOING}
					R.id.radio_done -> {Status.DONE}
					else -> throw Error("bomboclat wallahi")
				}
			}
		}
	}

	private fun validateAndCreateTask() {
		val description = binding.inputDescricao.text.toString().trim()

		if (description.isBlank()) {
			showBottomSheet(message = getString(R.string.form_description_empty))
			return
		}

		// if (newTask), se for usar
		saveTask(Task(createRandomKey(), description, status))
	}

	private fun createRandomKey() = database.push().key.toString()

	private fun saveTask(task: Task) {
		binding.progressBar.isVisible = true

		DatabaseManager
			.createTask(auth.currentUser?.uid ?: "", task)
			.addOnCompleteListener { result ->
				if (!result.isSuccessful) {
					showBottomSheet(message = getString(R.string.error_generic))
				}

				Toast.makeText(requireContext(), R.string.text_save_success_form_task_fragment, Toast.LENGTH_SHORT).show()
				findNavController().popBackStack()

				binding.progressBar.isVisible = false
			}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}