package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinalActivity : AppCompatActivity() {
  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_final)
    displayEnteredText()
    setupButtonClickListeners()
  }

  private fun displayEnteredText () {
    val text = intent.extras?.getString("data_key") ?: return
    val enteredTextView = findViewById<TextView>(R.id.finalTextView)
    enteredTextView.text = text
  }

  private fun setupButtonClickListeners () {
    val backToMainButton: Button = findViewById(R.id.finalButton)
    backToMainButton.setOnClickListener {
      startActivity(Intent(this, MainActivity::class.java))
    }
  }
}