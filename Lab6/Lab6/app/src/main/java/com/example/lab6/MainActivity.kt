package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
  private lateinit var pointsNumber: EditText
  private lateinit var minX: EditText
  private lateinit var maxX: EditText
  private lateinit var performButton: Button
  private lateinit var minY: EditText
  private lateinit var maxY: EditText
  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    pointsNumber = findViewById(R.id.number_text)
    minX = findViewById(R.id.xMin_text)
    maxX = findViewById(R.id.xMax_text)
    performButton = findViewById(R.id.perform_button)
    minY = findViewById(R.id.yMin_text)
    maxY = findViewById(R.id.yMax_text)

    performButton.setOnClickListener {
      launchObject2Intent()
    }
  }

  private fun launchObject2Intent () {
    val object2Intent: Intent? = packageManager.getLaunchIntentForPackage("com.example.object2")
    object2Intent?.apply {
      addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

      putExtra("n", pointsNumber.text.toString().toIntOrNull() ?: 0)
      putExtra("minX", minX.text.toString().toIntOrNull() ?: 0)
      putExtra("maxX", maxX.text.toString().toIntOrNull() ?: 0)
      putExtra("minY", minY.text.toString().toIntOrNull() ?: 0)
      putExtra("maxY", maxY.text.toString().toIntOrNull() ?: 0)

      startActivity(this)
    }
  }
}