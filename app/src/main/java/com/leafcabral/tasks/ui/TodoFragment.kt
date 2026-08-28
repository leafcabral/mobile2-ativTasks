package com.leafcabral.tasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafcabral.tasks.R
import com.leafcabral.tasks.data.model.Status
import com.leafcabral.tasks.data.model.Task
import com.leafcabral.tasks.databinding.FragmentTodoBinding
import com.leafcabral.tasks.ui.adapter.TaskAdapter
import com.leafcabral.tasks.ui.taskAdapter


private lateinit var taskAdapter: TaskAdapter


class TodoFragment : Fragment() {
	private var _binding: FragmentTodoBinding? = null
	private val binding get() = _binding!!


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentTodoBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.floatingActionButton.setOnClickListener {
			findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
		}

		initRecyclerViewTask()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


	private fun initRecyclerViewTask() {
		taskAdapter = TaskAdapter(requireContext()) { task, option -> optionSelected(task, option)}
		binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerViewTask.setHasFixedSize(true)

		binding.recyclerViewTask.adapter = taskAdapter
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