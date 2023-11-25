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
    private const val STROKE_WIDTH = 15f
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

  override fun onSizeChanged(newWidth: Int, newHeight: Int, oldWidth: Int, oldHeight: Int) {
    super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
    drawingCanvas = Canvas()
    drawingCanvas.drawColor(BACKGROUND_COLOUR)
  }
}