package com.example.lab5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomDrawingView (context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
  companion object {
    private const val BACKGROUND_COLOUR = Color.WHITE
    private const val DRAWING_COLOR = Color.BLACK
    private const val STROKE_WIDTH = 10f
  }

  private var drawingCanvas = Canvas()
  private val shapeList = mutableListOf<Shape>()
  private var currentX = 0f
  private var currentY = 0f

  val drawingSetting = Paint().apply {
    color = DRAWING_COLOR
    strokeWidth = STROKE_WIDTH
    style = Paint.Style.STROKE
    strokeCap = Paint.Cap.ROUND
    strokeJoin = Paint.Join.ROUND
    isAntiAlias = true
  }

  private var actualShapeEditor: MyEditor = MyEditor.getInstance(drawingSetting, shapeList)
  private lateinit var table: MyTable

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    shapeList.forEach { it.draw(canvas) }
  }

  fun setShapePrimitiveEditor(shape: Shape) {
    actualShapeEditor.defineInitialShape(shape)
  }

  fun defineTable (table: MyTable) {
    this.table = table
    actualShapeEditor.defineTable(table)
  }

  fun receiveShapes (): MutableList<Shape> {
    return shapeList
  }

  private fun handleTouchUp() {
    invalidate()
    actualShapeEditor.onTouchUp()
  }

  private fun handleTouchMove() {
    invalidate()
    actualShapeEditor.handleMouseMovement(currentX, currentY)
  }

  private fun handleTouchStart() {
    invalidate()
    actualShapeEditor.onTouchDown(currentX, currentY)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    currentX = event!!.x
    currentY = event.y

    when (event.action) {
      MotionEvent.ACTION_MOVE -> handleTouchMove()
      MotionEvent.ACTION_UP -> handleTouchUp()
      MotionEvent.ACTION_DOWN -> handleTouchStart()
    }
    return true
  }

  fun retrieveData (data: List<String>) {
    shapeList.clear()
    data.forEach { string ->
      val fields = string.split("\t")
      processFields(fields)
    }
  }

  private fun processFields (fields: List<String>) {
    val shape = createShapeFromFields(fields)
    shape.let {
      table.insertRow(it.toShapeCoordinate())
      shapeList.add(it)
      invalidate()
    }
  }

  private fun createShapeFromFields (fields: List<String>): Shape {
    val shapeType = Class.forName(fields[0])
    val constructor = shapeType.getConstructor(Paint::class.java)
    val shape  = constructor.newInstance(drawingSetting) as Shape

    shape.apply {
      defineStartCoordinates(fields[1].toFloat(), fields[2].toFloat())
      defineEndCoordinates(fields[3].toFloat(), fields[4].toFloat())
      defineEraserMode(false)
    }

    return shape
  }

  override fun onSizeChanged(newWidth: Int, newHeight: Int, oldWidth: Int, oldHeight: Int) {
    super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
    drawingCanvas = Canvas()
    drawingCanvas.drawColor(BACKGROUND_COLOUR)
  }
}