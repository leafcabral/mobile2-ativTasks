package com.leafcabral.tasks.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.initToolbar(toolbar: Toolbar) {
	val superActivity = activity as AppCompatActivity
	superActivity.setSupportActionBar(toolbar)
	superActivity.title = ""
	superActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

	toolbar.setNavigationOnClickListener {
		activity?.onBackPressedDispatcher?.onBackPressed()
	}

}