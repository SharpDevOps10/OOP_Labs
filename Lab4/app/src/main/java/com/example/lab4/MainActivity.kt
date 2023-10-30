package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.lab4.editor_primitives.DotEditor
import com.example.lab4.editor_primitives.EllipseEditor
import com.example.lab4.editor_primitives.LineEditor
import com.example.lab4.editor_primitives.RectangleEditor


class MainActivity : AppCompatActivity() {
  private lateinit var drawingView: CustomDrawingView
  private lateinit var currentSelectedOption: MenuItem
  private lateinit var mainMenu: Menu

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    drawingView = CustomDrawingView(this)
    val initialEditor = DotEditor(drawingView.drawingSetting, drawingView.shapeList)
    drawingView.setShapePrimitiveEditor(initialEditor)
    setContentView(drawingView)
    showSystemBars()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val mainMenuInflater: MenuInflater = menuInflater
    mainMenuInflater.inflate(R.menu.main_menu, menu)
    mainMenu = menu!!
    currentSelectedOption = mainMenu.findItem(R.id.dotIcon)
    setPrimitiveIcon(currentSelectedOption, R.drawable.dot)
    updateActionBarTitle(currentSelectedOption.title.toString())
    currentSelectedOption.isChecked = true
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    defineDisabledIcon(currentSelectedOption)
    currentSelectedOption = mainMenu.findItem(item.itemId)
    val optionTitle = currentSelectedOption.title.toString()

    val primitiveEditor: ShapeEditor? = when (item.itemId) {
      R.id.ellipseIcon, R.id.ellipseSelect -> EllipseEditor(
        drawingView.drawingSetting,
        drawingView.shapeList
      )

      R.id.lineIcon, R.id.lineSelect -> LineEditor(
        drawingView.drawingSetting,
        drawingView.shapeList
      )

      R.id.dotIcon, R.id.dotSelect -> DotEditor(drawingView.drawingSetting, drawingView.shapeList)
      R.id.rectangleIcon, R.id.rectangleSelect -> RectangleEditor(
        drawingView.drawingSetting,
        drawingView.shapeList
      )

      else -> null
    }

    primitiveEditor?.let { editor ->
      item.isChecked = true
      mainMenu.findItem(R.id.ellipseSelect).isChecked = false
      mainMenu.findItem(R.id.lineSelect).isChecked = false
      mainMenu.findItem(R.id.rectangleSelect).isChecked = false
      mainMenu.findItem(R.id.dotSelect).isChecked = false

      val iconResourceId = when (editor) {
        is EllipseEditor -> R.drawable.ellipse
        is LineEditor -> R.drawable.line
        is DotEditor -> R.drawable.dot
        is RectangleEditor -> R.drawable.rectangle
        else -> 0
      }

      setCurrentPrimitive(editor, optionTitle, iconResourceId)
    }

    updateActionBarTitle(optionTitle)
    currentSelectedOption.isChecked = true

    return super.onOptionsItemSelected(item)
  }


  private fun showSystemBars() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, drawingView).show(WindowInsetsCompat.Type.systemBars())
  }

  private fun setPrimitiveIcon(item: MenuItem, iconResourceId: Int) {
    item.icon = ContextCompat.getDrawable(this, iconResourceId)
  }

  private fun setCurrentPrimitive(primitive: ShapeEditor, title: String, iconResourceId: Int) {
    drawingView.setShapePrimitiveEditor(primitive)
    currentSelectedOption.icon = ContextCompat.getDrawable(this, iconResourceId)
    currentSelectedOption.title = title
  }

  private fun defineDisabledIcon(item: MenuItem) {
    when (item.itemId) {
      R.id.ellipseIcon -> setPrimitiveIcon(item, R.drawable.ellipse_disabled)
      R.id.lineIcon -> setPrimitiveIcon(item, R.drawable.line_disabled)
      R.id.dotIcon -> setPrimitiveIcon(item, R.drawable.dot_disabled)
      R.id.rectangleIcon -> setPrimitiveIcon(item, R.drawable.rectangle_disabled)
    }
  }

  private fun updateActionBarTitle(title: String) {
    supportActionBar?.title = title
  }
}