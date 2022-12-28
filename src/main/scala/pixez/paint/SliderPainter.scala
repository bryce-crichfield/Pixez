package org.bpc
package pixez.paint
import pixez.geometry.{Line, Rectangle}
import pixez.math.V2
import pixez.{PixezComponent, paint}

import java.awt.{Color, Graphics2D}
import scala.swing.Dimension
trait SliderPainter {
  protected var lineweight: Int = _
  protected var knobRadius: Float = _
  protected val highlightPercentage = 0.25f
  protected var trackLine: Line = _
  protected var handleCenter: V2 = _
  protected var handleBounds: Rectangle = _

  def paintSlider(size: Dimension, accent: Color, sliderValue: Float, graphics: Graphics2D): Unit = {
    updatePaintParameters(size, sliderValue)
    paintTrack(accent, graphics)
    paintHandle(graphics)
  }

  protected def updatePaintParameters(size: Dimension, sliderValue: Float): Unit

  protected def paintTrack(accent: Color, graphics: Graphics2D): Unit = {
    val backgroundStroke = paint.RoundStroke(lineweight)
    trackLine.draw(Color.BLACK, backgroundStroke, graphics)
    val accentStroke = paint.RoundStroke(lineweight * highlightPercentage)
    trackLine.set(p2 = handleCenter).draw(accent, accentStroke, graphics)
  }

  protected def paintHandle(graphics: Graphics2D): Unit = {
    val gradient = handleBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
    handleBounds.toOval.fill(gradient, graphics)
    val borderStroke = paint.RoundStroke(3)
    handleBounds.toOval.draw(Color.BLACK, borderStroke, graphics)
  }
}

class HorizontalSliderPainter extends SliderPainter {
  protected def updatePaintParameters(size: Dimension, sliderValue: Float): Unit = {
    // The line-weight will be no greater than 10% of the
    // smaller dimension of the component or 15 pixels
    lineweight = {
      val mindim = Math.min(size.width, size.height) * .1f
      Math.min(mindim, 15f).toInt
    }
    // The knobRadius will 10% larger than the line-weight
    knobRadius = lineweight + (lineweight * .1f)
    // Push the track over by a line-weight and a half (due to the rounded line caps)
    trackLine = {
      val start = V2(lineweight * 1.5f, size.height / 2)
      val end = V2(size.width - (lineweight * 1.5f),size.height / 2)
      Line(start, end)
    }
    // Determine the bounds of the handle along the trackLine
    val centerX = ((trackLine.lengthX - (2 * lineweight)) * sliderValue) + (2 * lineweight)
    handleCenter = V2(centerX, size.height / 2)
    handleBounds = Rectangle(handleCenter - V2(knobRadius), V2(knobRadius * 2))
  }
}

class VerticalSliderPainter extends SliderPainter {
  protected def updatePaintParameters(size: Dimension, sliderValue: Float): Unit = {
    // The line-weight will be no greater than 10% of the
    // smaller dimension of the component or 15 pixels
    lineweight = {
      val mindim = Math.min(size.width, size.height) * .1f
      Math.min(mindim, 15f).toInt
    }
    // The knobRadius will 10% larger than the line-weight
    knobRadius = lineweight + (lineweight * .1f)
    // Push the track over by a line-weight and a half (due to the rounded line caps)
    trackLine = {
      val start = V2(size.width / 2, size.height - (lineweight * 1.5f))
      val end = V2(size.width / 2, lineweight * 1.5f)
      Line(start, end)
    }
    // Determine the bounds of the handle along the trackLine
    val centerY = ((trackLine.lengthY - (2 * lineweight)) * sliderValue) + (2 * lineweight)
    handleCenter = V2(size.width / 2, centerY)
    handleBounds = Rectangle(handleCenter - V2(knobRadius), V2(knobRadius * 2))
  }
}
