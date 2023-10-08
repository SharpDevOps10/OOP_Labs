package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
class MainActivity : AppCompatActivity() {

  private lateinit var drawingView: CustomDrawingView
  private var currentPrimitive: CustomDrawingView.PrimitivesSelection = CustomDrawingView.PrimitivesSelection.DOT
  private lateinit var mainMenu: Menu
  private var menuItemMap: MutableMap<Int, CustomDrawingView.PrimitivesSelection> = mutableMapOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    drawingView = CustomDrawingView(this)
    drawingView.setShapePrimitiveEditor(currentPrimitive)
    setContentView(drawingView)
    showSystemBars()
  }

  private fun setCurrentPrimitive (primitive: CustomDrawingView.PrimitivesSelection) {
    currentPrimitive = primitive
    drawingView.setShapePrimitiveEditor(currentPrimitive)
    updateMenuCheckState(currentPrimitive)
  }

  override fun onCreateOptionsMenu (menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    mainMenu = menu!!

    menuItemMap[R.id.ellipseSelect] = CustomDrawingView.PrimitivesSelection.ELLIPSE
    menuItemMap[R.id.lineSelect] = CustomDrawingView.PrimitivesSelection.LINE
    menuItemMap[R.id.dotSelect] = CustomDrawingView.PrimitivesSelection.DOT
    menuItemMap[R.id.rectSelect] = CustomDrawingView.PrimitivesSelection.RECTANGLE
    updateMenuCheckState(currentPrimitive)
    return true
  }

  override fun onOptionsItemSelected (item: MenuItem): Boolean {
    val selectedPrimitive = menuItemMap[item.itemId]
    if (selectedPrimitive != null) setCurrentPrimitive(selectedPrimitive)

    return super.onOptionsItemSelected(item)
  }

  private fun showSystemBars () {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, drawingView).show(WindowInsetsCompat.Type.systemBars())
  }

  private fun updateMenuCheckState (selectedOption: CustomDrawingView.PrimitivesSelection) {
    menuItemMap.values.forEach { mainMenu.findItem(getMenuItemId(it))?.isChecked = false }
    mainMenu.findItem(getMenuItemId(selectedOption))?.isChecked = true
  }

  private fun getMenuItemId (option: CustomDrawingView.PrimitivesSelection): Int {
    return when (option) {
      CustomDrawingView.PrimitivesSelection.ELLIPSE -> R.id.ellipseSelect
      CustomDrawingView.PrimitivesSelection.LINE -> R.id.lineSelect
      CustomDrawingView.PrimitivesSelection.DOT -> R.id.dotSelect
      CustomDrawingView.PrimitivesSelection.RECTANGLE -> R.id.rectSelect
    }
  }
}