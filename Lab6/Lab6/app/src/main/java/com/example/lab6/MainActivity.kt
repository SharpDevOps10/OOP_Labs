package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
  private lateinit var nEditText: EditText
  private lateinit var minEditText: EditText
  private lateinit var maxEditText: EditText
  private lateinit var submitButton: Button
  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    nEditText = findViewById(R.id.n_edit_text)
    minEditText = findViewById(R.id.min_edit_text)
    maxEditText = findViewById(R.id.max_edit_text)
    submitButton = findViewById(R.id.submit_button)

    submitButton.setOnClickListener {
      launchObject2Intent()
    }
  }

  private fun launchObject2Intent () {
    val object2Intent: Intent? = packageManager.getLaunchIntentForPackage("com.example.object2")
    object2Intent!!.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    object2Intent.putExtra("n", nEditText.text.toString().toInt())
    object2Intent.putExtra("min", minEditText.text.toString().toFloat())
    object2Intent.putExtra("max", maxEditText.text.toString().toFloat())
    startActivity(object2Intent)
  }

}