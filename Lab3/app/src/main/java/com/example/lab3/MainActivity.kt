package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity () {
  private lateinit var drawingView: CustomDrawingView
  private lateinit var currentSelectedOption: MenuItem
  private lateinit var mainMenu: Menu

  override fun onCreate (savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    drawingView = CustomDrawingView(this)
    drawingView.setShapePrimitiveEditor(CustomDrawingView.PrimitivesSelection.DOT)
    setContentView(drawingView)
    showSystemUI()
  }

  override fun onCreateOptionsMenu (menu: Menu?): Boolean {
    val mainMenuInflater: MenuInflater = menuInflater
    mainMenuInflater.inflate(R.menu.main_menu, menu)
    mainMenu = menu!!
    currentSelectedOption = mainMenu.findItem(R.id.dotIcon)
    setOptionIcon(currentSelectedOption, R.drawable.dot)
    updateActionBarTitle(currentSelectedOption.title.toString())
    currentSelectedOption.isChecked = true
    return true
  }

  override fun onOptionsItemSelected (item: MenuItem): Boolean {
    defineDisabledIcon(currentSelectedOption)
    currentSelectedOption = mainMenu.findItem(item.itemId)
    val optionTitle = currentSelectedOption.title.toString()

    val itemToCheck = when (item.itemId) {
      R.id.ellipseIcon, R.id.ellipseSelect -> {
        CustomDrawingView.PrimitivesSelection.ELLIPSE to R.drawable.ellipse
      }
      R.id.lineIcon, R.id.lineSelect -> {
        CustomDrawingView.PrimitivesSelection.LINE to R.drawable.line
      }
      R.id.dotIcon, R.id.dotSelect -> {
        CustomDrawingView.PrimitivesSelection.DOT to R.drawable.dot
      }
      R.id.rectangleIcon, R.id.rectangleSelect -> {
        CustomDrawingView.PrimitivesSelection.RECTANGLE to R.drawable.rectangle
      }
      else -> null to 0
    }

    itemToCheck.let { (primitive, icon) ->
      item.isChecked = true
      mainMenu.findItem(R.id.ellipseSelect).isChecked = false
      mainMenu.findItem(R.id.lineSelect).isChecked = false
      mainMenu.findItem(R.id.rectangleSelect).isChecked = false
      mainMenu.findItem(R.id.dotSelect).isChecked = false
      primitive?.let { setCurrentOption(it, optionTitle, icon) }
    }

    updateActionBarTitle (optionTitle)
    currentSelectedOption.isChecked = true

    return super.onOptionsItemSelected(item)
  }

  private fun showSystemUI () {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, drawingView).show(WindowInsetsCompat.Type.systemBars())
  }

  private fun setOptionIcon (item: MenuItem, iconResourceId: Int) {
    item.icon = ContextCompat.getDrawable(this, iconResourceId)
  }

  private fun setCurrentOption (option: CustomDrawingView.PrimitivesSelection, title: String, iconResourceId: Int) {
    drawingView.setShapePrimitiveEditor(option)
    currentSelectedOption.icon = ContextCompat.getDrawable(this, iconResourceId)
    currentSelectedOption.title = title
  }

  private fun defineDisabledIcon (item: MenuItem) {
    when (item.itemId) {
      R.id.ellipseIcon -> setOptionIcon(item, R.drawable.ellipse_disabled)
      R.id.lineIcon -> setOptionIcon(item, R.drawable.line_disabled)
      R.id.dotIcon -> setOptionIcon(item, R.drawable.dot_disabled)
      R.id.rectangleIcon -> setOptionIcon(item, R.drawable.rectangle_disabled)
    }
  }
  private fun updateActionBarTitle (title: String) {
    supportActionBar?.title = title
  }
}