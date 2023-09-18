package com.example.myapplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button


class MainActivity : AppCompatActivity() {
  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val firstWork: Button = findViewById(R.id.work0Button)
    val secondWork: Button = findViewById(R.id.work3Button)

    firstWork.setOnClickListener {
      startActivity(Intent(this, Module1Activity::class.java))
    }

    secondWork.setOnClickListener {
      startActivity(Intent(this, Module2Activity::class.java))
    }
  }

  override fun onBackPressed () {
    this.finishAffinity()
  }
}