package com.example.object2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val nPoint = intent.getIntExtra("n", 0)
    val xMin = intent.getIntExtra("minX", 0)
    val xMax = intent.getIntExtra("maxX", 0)
    val yMin = intent.getIntExtra("minY", 0)
    val yMax = intent.getIntExtra("maxY", 0)

    val points = generatePoints(nPoint, xMin, xMax, yMin, yMax)
    val sortedPoints = sortPoints(points)
    displayPoints(sortedPoints)
    copyToClipboard(sortedPoints, this)

    val obj3Intent = packageManager.getLaunchIntentForPackage("com.example.object3")
    obj3Intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(obj3Intent)
  }

  override fun onNewIntent (intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    val obj3Intent: Intent? = packageManager.getLaunchIntentForPackage("com.example.object3")
    obj3Intent!!.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(obj3Intent)
  }

  private fun generatePoints (n: Int, xMin: Int, xMax: Int, yMin: Int, yMax: Int): List<Pair<Int, Int>> {
    return List(n) {
      Pair(Random.nextInt(xMin, xMax), Random.nextInt(yMin, yMax))
    }
  }

  private fun sortPoints (points: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    return points.sortedWith(compareBy({ it.first }, { it.second }))
  }

  private fun displayPoints (points: List<Pair<Int, Int>>) {
    val textView: TextView = findViewById(R.id.textView)
    var string = "X\t\t\tY\n"

    for (point in points) {
      string += "${point.first}\t\t\t${point.second}\n"
    }

    textView.text = string.trim('\n')
  }

  private fun copyToClipboard (points: List<Pair<Int, Int>>, context: Context) {
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("coords", points.joinToString("\n") { "${it.first}\t\t\t${it.second}" })
    clipboard.setPrimaryClip(clip)
  }
}