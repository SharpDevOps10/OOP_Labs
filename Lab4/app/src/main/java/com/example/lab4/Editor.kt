package com.example.lab4

abstract class Editor {
  abstract fun handleMouseMovement (x: Float, y: Float)
  abstract fun onTouchUp ()
  abstract fun onTouchDown (x: Float, y: Float)

}