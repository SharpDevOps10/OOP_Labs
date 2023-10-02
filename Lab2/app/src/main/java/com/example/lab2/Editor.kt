package com.example.lab2

abstract class Editor {
  abstract fun handleMouseMovement (x: Float, y: Float)
  abstract fun handleLeftMouseButtonRelease ()
  abstract fun handleLeftMouseButtonPress (x: Float, y: Float)

}