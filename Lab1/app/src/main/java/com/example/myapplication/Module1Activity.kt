package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Module1Activity : AppCompatActivity() {
  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_module1)

    val acceptButton: Button = findViewById(R.id.acceptButton1)
    val editTextView: EditText = findViewById(R.id.editText1)
    val cancelButton: Button = findViewById(R.id.cancelButton1)

    acceptButton.setOnClickListener {
      val enteredText = editTextView.text.toString()
      if (enteredText.isBlank()) Toast.makeText(this, "Enter text!", Toast.LENGTH_SHORT).show()
      else navigateToFinalActivity(enteredText)
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