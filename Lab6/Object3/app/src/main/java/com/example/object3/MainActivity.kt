package com.example.object3

import android.content.ClipboardManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class MainActivity : AppCompatActivity() {

  private lateinit var lineGraphView: GraphView
  private lateinit var clipboardData: String
  private lateinit var xAxisBounds: Pair<Double, Double>
  private lateinit var yAxisBounds: Pair<Double, Double>

  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onWindowFocusChanged (hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) {
      initializeGraphView()
      processClipboardData()
    }
  }

  private fun initializeGraphView () {
    lineGraphView = findViewById(R.id.idGraphView)
    lineGraphView.removeAllSeries()
    lineGraphView.viewport.isScrollable = true
    lineGraphView.viewport.isScalable = true
    lineGraphView.viewport.setScalableY(false)
    lineGraphView.viewport.setScrollableY(false)
  }

  private fun processClipboardData () {
    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipboardText = clipboard.primaryClip?.getItemAt(0)?.text?.toString()
    clipboardData = clipboardText.toString()

    val graphSeries = createSeriesFromClipboardData(clipboardData)

    graphSeries.isDrawDataPoints = true
    graphSeries.dataPointsRadius = 10f
    graphSeries.color = Color.GREEN
    lineGraphView.animate()

    setGraphViewMinMax(graphSeries)
    lineGraphView.addSeries(graphSeries)
  }

  private fun createSeriesFromClipboardData (str: String): LineGraphSeries<DataPoint> {
    val dataPointStrings = str.trimEnd('\n').split("\n").toMutableList().also { it.removeAt(0) }
    val dataPoints = mutableListOf<DataPoint>()

    for (i in dataPointStrings.indices) {
      val (x, y) = dataPointStrings[i].split("\t\t\t").map { it.toDouble() }
      dataPoints.add(DataPoint(x, y))
      updateMinMaxValues(x, y, i, dataPointStrings.size)
    }

    return LineGraphSeries(dataPoints.toTypedArray())
  }

  private fun setGraphViewMinMax (series: LineGraphSeries<DataPoint>) {
    lineGraphView.viewport.setMinX(xAxisBounds.first)
    lineGraphView.viewport.setMaxX(xAxisBounds.second)
    lineGraphView.viewport.setMinY(yAxisBounds.first)
    lineGraphView.viewport.setMaxY(yAxisBounds.second)
  }

  private fun updateMinMaxValues (x: Double, y: Double, index: Int, size: Int) {
    if (index == 0) {
      xAxisBounds = Pair(x, x)
      yAxisBounds = Pair(y, y)
    } else if (index == size - 1) {
      xAxisBounds = xAxisBounds.copy(second = x)
    }

    yAxisBounds = Pair(yAxisBounds.first.coerceAtMost(y), yAxisBounds.second.coerceAtLeast(y))
  }
}