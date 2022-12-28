package org.bpc
package pixez.geometry

import pixez.math.V2

import java.awt.*

case class Line(p1: V2, p2: V2) {
  inline def set(p1: V2 = p1, p2: V2 = p2): Line = Line(p1, p2)
  inline def lengthX: Float = {
    V2(p1.x, p2.x).length
  }
  
  inline def lengthY: Float = {
    V2(p1.y, p2.y).length
  }
  inline def gradient(start: Color, end: Color): GradientPaint = {
    new GradientPaint(p1.x, p1.y, start, p2.x, p2.y, end)
  }

  inline def draw(paint: Paint, stroke: Stroke, graphics: Graphics2D): Unit = {
    graphics.setStroke(stroke)
    graphics.setPaint(paint)
    graphics.drawLine(
      this.p1.x.toInt,
      this.p1.y.toInt,
      this.p2.x.toInt,
      this.p2.y.toInt
    )
  }
}
