package com.example.taskmanager.presentation.common

import android.content.Context
import android.widget.Toast

fun Context.showMessage(value: String) = Toast.makeText(this, value, Toast.LENGTH_LONG).show()
