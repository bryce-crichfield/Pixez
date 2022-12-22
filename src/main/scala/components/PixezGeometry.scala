package org.bpc
package components

import org.bpc.math.V2

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, Stroke, Paint}
import scala.swing.Dimension
object PixezGeometry {

  case class Line(p1: V2, p2: V2) {
    
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

}


