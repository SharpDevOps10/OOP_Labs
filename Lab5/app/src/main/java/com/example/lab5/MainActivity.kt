package com.example.lab5

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentTransaction
import com.example.lab5.shape_primitives.CubeShape
import com.example.lab5.shape_primitives.DotShape
import com.example.lab5.shape_primitives.EllipseShape
import com.example.lab5.shape_primitives.LineShape
import com.example.lab5.shape_primitives.LineWithCirclesShape
import com.example.lab5.shape_primitives.RectangleShape

class MainActivity : AppCompatActivity() {
  private lateinit var drawingView: CustomDrawingView
  private lateinit var currentSelectedOption: MenuItem
  private lateinit var frameLayout: FrameLayout
  private lateinit var table: MyTable
  private lateinit var mainMenu: Menu

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    drawingView = findViewById(R.id.mainCanvas)
    drawingView.setShapePrimitiveEditor(LineShape(drawingView.drawingSetting))

    frameLayout = findViewById(R.id.tableContainer)

    table = MyTable()
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.add(R.id.tableContainer, table)
    ft.commit()
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
      R.id.tableIcon, R.id.tableSelect -> {
        if (frameLayout.visibility == View.GONE) frameLayout.visibility = View.VISIBLE
        else frameLayout.visibility = View.GONE
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