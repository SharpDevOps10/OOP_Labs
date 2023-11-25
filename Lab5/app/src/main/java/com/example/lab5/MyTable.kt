package com.example.lab5

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileInputStream

class MyTable : Fragment() {
  private val shapeCoordinates = mutableListOf<ShapeCoordinate>()
  private lateinit var tableLayout: TableLayout
  private lateinit var view: CustomDrawingView
  private lateinit var fragmentView: View
  private lateinit var saveImageButton: ImageButton
  private lateinit var loadImageButton: ImageButton

  override fun onCreateView (
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    fragmentView = inflater.inflate(R.layout.table, container, false)
    return fragmentView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeViews()
    setupListeners()
  }

  fun insertRow (shapeCoordinate: ShapeCoordinate) {
    shapeCoordinates.add(shapeCoordinate)
    val shapeRow = createRowView(shapeCoordinate)
    configureRowEvents(shapeRow, shapeCoordinate)
    tableLayout.addView(shapeRow)
  }

  private fun initializeViews () {
    view = requireActivity().findViewById(R.id.mainCanvas)
    tableLayout = fragmentView.findViewById(R.id.tableLayout)
    defineLayout(tableLayout)

    view.defineTable(this)

    loadImageButton = fragmentView.findViewById(R.id.loadImageButton)
    saveImageButton = fragmentView.findViewById(R.id.saveImageButton)
  }

  private fun defineLayout (layout: TableLayout) {
    this.tableLayout = layout
    val header = createTableHeaderRow()
    this.tableLayout.addView(header)
  }

  private fun setupListeners () {
    loadImageButton.setOnClickListener { retrieveData() }
    saveImageButton.setOnClickListener { saveData()}
  }

  private fun saveData() {
    val filePath = context?.filesDir
    val file = File(filePath, "SHAPES")
    file.delete()
    receiveRows().forEach { row ->
      file.appendText("${row.shapeType.trim()}\t${row.startX.trim()}\t${row.startY.trim()}\t${row.endX.trim()}\t${row.endY}\n")
    }
  }

  private fun retrieveData() {
    emptyRows()
    defineLayout(tableLayout)
    val filePath = context?.filesDir
    val file = File(filePath, "SHAPES")
    val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
    val splitInput = inputAsString.split("\n")
    val shapesData = splitInput.filter { splitInput.indexOf(it) != splitInput.lastIndex }
    view.retrieveData(shapesData)
  }

  private fun createTableHeaderRow (): TableRow {
    val tableHeaderRow = TableRow(tableLayout.context)
    val shapeName  = createTextView(getString(R.string.label_name))
    val startX = createTextView(getString(R.string.label_x1))
    val startY = createTextView(getString(R.string.label_y1))
    val endX = createTextView(getString(R.string.label_x2))
    val endY = createTextView(getString(R.string.label_y2))

    tableHeaderRow.addView(shapeName )
    tableHeaderRow.addView(startX)
    tableHeaderRow.addView(startY)
    tableHeaderRow.addView(endX)
    tableHeaderRow.addView(endY)
    return tableHeaderRow
  }

  private fun createRowView (shapeCoordinate: ShapeCoordinate): TableRow {
    val shapeRow = TableRow(tableLayout.context)
    val shapeName = createTextView(shapeCoordinate.shapeType.split(".").last())
    val startX = createTextView(shapeCoordinate.startX)
    val startY = createTextView(shapeCoordinate.startY)
    val endX = createTextView(shapeCoordinate.endX)
    val endY = createTextView(shapeCoordinate.endY)
    shapeRow.addView(shapeName)
    shapeRow.addView(startX)
    shapeRow.addView(startY)
    shapeRow.addView(endX)
    shapeRow.addView(endY)
    return shapeRow
  }

  private fun createTextView (text: String): TextView {
    return TextView(tableLayout.context).apply { this.text = text }
  }

  private fun removeAndShift (index: Int, shapes: MutableList<Shape>) {
    shapes.removeAt(index)
  }

  private fun configureRowEvents (shapeRow: TableRow, shapeCoordinate: ShapeCoordinate) {
    for (i in 0 until shapeRow.childCount) {
      val textView: TextView = shapeRow.getChildAt(i) as TextView
      textView.setTextColor(Color.BLACK)
    }

    shapeRow.setOnLongClickListener {
      removeAndShift(view.receiveShapes().indexOf(shapeCoordinate.shapeReference), view.receiveShapes())
      tableLayout.removeViewInLayout(shapeRow)
      shapeCoordinates.remove(shapeCoordinate)
      tableLayout.invalidate()
      view.invalidate()
      true
    }

    shapeRow.setOnClickListener {
      for (i in 0 until shapeRow.childCount) {
        val textView: TextView = shapeRow.getChildAt(i) as TextView
        if (!shapeCoordinate.shapeReference.receiveHighlightingCondition()) textView.setTextColor(Color.GREEN)
        else textView.setTextColor(Color.BLACK)
      }
      shapeCoordinate.shapeReference.defineHighlightingMode()
      view.invalidate()
    }
  }

  private fun emptyRows () {
    shapeCoordinates.clear()
    tableLayout.removeAllViews()
  }

  private fun receiveRows (): MutableList<ShapeCoordinate> {
    return shapeCoordinates
  }
}