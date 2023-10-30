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
import com.example.lab4.shape_primitives.CubeShape
import com.example.lab4.shape_primitives.DotShape
import com.example.lab4.shape_primitives.EllipseShape
import com.example.lab4.shape_primitives.LineShape
import com.example.lab4.shape_primitives.LineWithCirclesShape
import com.example.lab4.shape_primitives.RectangleShape

class MainActivity : AppCompatActivity() {
  private lateinit var drawingView: CustomDrawingView
  private lateinit var currentSelectedOption: MenuItem
  private lateinit var mainMenu: Menu

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    drawingView = com.example.lab4.CustomDrawingView(this)
    drawingView.setShapePrimitiveEditor(DotShape(drawingView.drawingSetting))
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
    when (item.itemId) {
      R.id.ellipseIcon, R.id.ellipseSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.ellipseIcon)
        drawingView.setShapePrimitiveEditor(EllipseShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.ellipse)
      }
      R.id.lineIcon, R.id.lineSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.lineIcon)
        drawingView.setShapePrimitiveEditor(LineShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.line)
      }
      R.id.dotIcon, R.id.dotSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.dotIcon)
        drawingView.setShapePrimitiveEditor(DotShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.dot)
      }
      R.id.rectangleIcon, R.id.rectangleSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.rectangleIcon)
        drawingView.setShapePrimitiveEditor(RectangleShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.rectangle)
      }
      R.id.cubeIcon, R.id.cubeSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.cubeIcon)
        drawingView.setShapePrimitiveEditor(CubeShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.cube_casino)
      }
      R.id.lineswithcirclesIcon, R.id.lineswithcirclesSelect -> {
        currentSelectedOption = mainMenu.findItem(R.id.lineswithcirclesIcon)
        drawingView.setShapePrimitiveEditor(LineWithCirclesShape(drawingView.drawingSetting))
        currentSelectedOption.icon = ContextCompat.getDrawable(this, R.drawable.linewithcircles)
      }
    }
    updateActionBarTitle(currentSelectedOption.title.toString())
    return super.onOptionsItemSelected(item)
  }


  private fun showSystemBars() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, drawingView).show(WindowInsetsCompat.Type.systemBars())
  }

  private fun setPrimitiveIcon(item: MenuItem, iconResourceId: Int) {
    item.icon = ContextCompat.getDrawable(this, iconResourceId)
  }

  private fun defineDisabledIcon(item: MenuItem) {
    when (item.itemId) {
      R.id.ellipseIcon -> setPrimitiveIcon(item, R.drawable.ellipse_disabled)
      R.id.lineIcon -> setPrimitiveIcon(item, R.drawable.line_disabled)
      R.id.dotIcon -> setPrimitiveIcon(item, R.drawable.dot_disabled)
      R.id.rectangleIcon -> setPrimitiveIcon(item, R.drawable.rectangle_disabled)
      R.id.cubeIcon -> setPrimitiveIcon(item, R.drawable.cube_casino_disabled)
      R.id.lineswithcirclesIcon -> setPrimitiveIcon(item, R.drawable.linewithcircle_disabled)
    }
  }

  private fun updateActionBarTitle(title: String) {
    supportActionBar?.title = title
  }
}