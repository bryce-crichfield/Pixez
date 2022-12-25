package org.bpc
package pixez.geometry

import math.V2

import java.awt.{Stroke, Graphics2D, Paint}

case class Oval(topleft: V2, size: V2) {
  inline def toRectangle: Rectangle = {
    Rectangle(topleft, size)
  }

  def fill(paint: Paint, graphics: Graphics2D): Unit = {
    graphics.setPaint(paint)
    graphics.fillOval(this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt)
  }

  inline def draw(paint: Paint, stroke: Stroke, graphics: Graphics2D): Unit = {
    graphics.setStroke(stroke)
    graphics.setPaint(paint)
    graphics.drawOval(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt)
  }
}
