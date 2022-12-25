package org.bpc
package pixez.paint

import math.V2
import pixez.geometry.{Line, Rectangle}
import pixez.{PixezComponent, paint}

import java.awt.{Color, Graphics2D}
import scala.swing.Dimension

class DialPainter {
  import org.bpc.pixez.geometry.*
  var startAngle = 0
  var rotationDirection = -1
  var knobLightColor = Color.DARK_GRAY
  var knobDarkColor = Color.black
  var needleColor = Color.white
  var accent = Color.green

  private val lineweight = 3
  private var center: V2 = _
  private var diameter: V2 = _
  private var knobBounds: Rectangle = _
  private var needleLine: Line = _
  private var meterArc: Arc = _

  def paintDial(size: Dimension, position: Float, graphics: Graphics2D): Unit = {
    updatePaintParameters(size, position)
    paintKnob(graphics)
    paintLines(graphics)
  }

  private def updatePaintParameters(size: Dimension,position: Float): Unit = {
    center = V2(size).center
    diameter = V2(V2(size).min) - (lineweight * 2)
    knobBounds = Rectangle(center - (V2(size).min / 2) + V2(lineweight), diameter)

    val arrowVector = V2.fromAngle(startAngle).invertY.rotate(position * rotationDirection * 360)
    needleLine = Line(center, center + (arrowVector * ((diameter - (2 * lineweight)) / 2f)))

    val angle = 360 * position
    meterArc = Arc(knobBounds.topleft, diameter, startAngle, angle)
  }

  private def paintKnob(graphics: Graphics2D): Unit = {
    val gradient = knobBounds.leftSide.gradient(knobLightColor, knobDarkColor)
    knobBounds.toOval.fill(gradient, graphics)
    val borderStroke = paint.RoundStroke(lineweight * 1.5f)
    knobBounds.toOval.draw(Color.BLACK, borderStroke, graphics)
  }

  private def paintLines(graphics: Graphics2D): Unit = {
    val stroke = paint.RoundStroke(lineweight)
    needleLine.draw(needleColor, stroke, graphics)
    meterArc.draw(accent, stroke, graphics)
  }
}