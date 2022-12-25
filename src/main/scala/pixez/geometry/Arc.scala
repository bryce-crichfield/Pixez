package org.bpc
package pixez.geometry

import math.V2

import java.awt.{Stroke, Graphics2D, Paint}
import scala.swing.Dimension

case class Arc(topleft: V2, size: V2, start: Float, extent: Float) {
  inline def toRectangle: Rectangle = {
    Rectangle(topleft, size)
  }

  inline def fill(arc: Arc, paint: Paint, graphics: Graphics2D): Unit = {
    graphics.fillArc(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt,
      this.start.toInt,
      this.extent.toInt
    )
  }
  inline def draw(paint: Paint, stroke: Stroke, graphics: Graphics2D): Unit = {
    graphics.setStroke(stroke)
    graphics.setPaint(paint)
    graphics.drawArc(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt,
      this.start.toInt,
      this.extent.toInt
    )
  }
}




