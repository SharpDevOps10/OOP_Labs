package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class Module2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_module2)
    initializeSpinner()
    setupButtonClickListeners()
  }

  private fun initializeSpinner () {
    val groupsIM2X = arrayOf("IM-21", "IM-22\uD83E\uDD70", "IM-23", "IM-24")

    val groupAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, groupsIM2X)
    groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    val groupSpinner: Spinner = findViewById(R.id.spinner)
    groupSpinner.adapter = groupAdapter
  }

  private fun setupButtonClickListeners () {
    val acceptButton: Button = findViewById(R.id.acceptButton2)
    val cancelButton: Button = findViewById(R.id.cancelButton2)
    val groupSpinner: Spinner = findViewById(R.id.spinner)

    acceptButton.setOnClickListener {
      val selectedItem = groupSpinner.selectedItem.toString()
      navigateToFinalActivity(selectedItem)
    }

    cancelButton.setOnClickListener {
      onBackPressed()
    }
  }

  private fun navigateToFinalActivity (data: String) {
    val intent = Intent(this, FinalActivity::class.java)
    intent.putExtra("data_key", data)
    startActivity(intent)
  }
}