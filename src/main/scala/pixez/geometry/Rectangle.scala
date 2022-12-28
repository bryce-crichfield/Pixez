package org.bpc
package pixez.geometry

import org.bpc.pixez.math.V2
import java.awt.{Graphics2D, Paint, Stroke}

case class Rectangle(topleft: V2, size: V2) {
  inline def bottomleft: V2 = {
    topleft + V2(0, size.y)
  }

  inline def topright: V2 = {
    topleft + V2(size.x, 0)
  }

  inline def bottomright: V2 = {
    topleft + size
  }

  inline def leftSide: Line = {
    Line(topleft, topleft + V2(0, size.y))
  }

  inline def rightSide: Line = {
    Line(topleft + V2(size.x, 0), topleft + size)
  }
  
  inline def center: V2 = {
    topleft + size / 2
  }

  inline def toOval: Oval = {
    Oval(topleft, size)
  }

  inline def toArc(start: Float, extent: Float): Arc = {
    Arc(topleft, size, start, extent)
  }

  inline def fill(paint: Paint, graphics: Graphics2D): Unit = {
    graphics.setPaint(paint)
    graphics.fillRect(this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt)
  }

  inline def fill(paint: Paint, rounding: Int, graphics: Graphics2D): Unit = {
    graphics.setPaint(paint)
    graphics.fillRoundRect(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt,
      rounding, rounding
    )
  }

  inline def draw(paint: Paint, stroke: Stroke, graphics: Graphics2D): Unit = {
    graphics.setStroke(stroke)
    graphics.setPaint(paint)
    graphics.drawRect(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt)
  }

  inline def draw(paint: Paint, stroke: Stroke, rounding: Int, graphics: Graphics2D): Unit = {
    graphics.setStroke(stroke)
    graphics.setPaint(paint)
    graphics.drawRoundRect(
      this.topleft.x.toInt,
      this.topleft.y.toInt,
      this.size.x.toInt,
      this.size.y.toInt,
      rounding, rounding
    )
  }
}
