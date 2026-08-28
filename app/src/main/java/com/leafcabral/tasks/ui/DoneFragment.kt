package com.leafcabral.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafcabral.tasks.data.model.Status
import com.leafcabral.tasks.data.model.Task
import com.leafcabral.tasks.databinding.FragmentDoneBinding
import com.leafcabral.tasks.ui.adapter.TaskAdapter



class DoneFragment : Fragment() {
	private var _binding: FragmentDoneBinding? = null
	private val binding get() = _binding!!

	private lateinit var taskAdapter: TaskAdapter

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentDoneBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initRecyclerViewTask()
		getTask()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


	private fun getTask() {
		val taskList = listOf(
			Task("8", "Atualizar o Android Studio", Status.DONE),
			Task("9", "Acompanhar o Google I/O", Status.DONE),
			Task("10", "Keep Android Open", Status.DONE),
			Task("11", "Consertar leitor de PDF", Status.DONE)
		)

		taskAdapter.submitList(taskList)
	}


	private fun initRecyclerViewTask() {
		taskAdapter = TaskAdapter(requireContext()) { task, option -> optionSelected(task, option)}
		with(binding.recyclerViewTask) {
			layoutManager = LinearLayoutManager(requireContext())
			setHasFixedSize(true)
			adapter = taskAdapter
		}
	}

	private fun optionSelected(task: Task, option: Int) {
		when (option) {
			TaskAdapter.SELECT_REMOVER -> {
				Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_EDIT -> {
				Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_DETAILS -> {
				Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
			}

			TaskAdapter.SELECT_NEXT -> {
				Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
			}
		}
	}
}