package org.bpc
package pixez

import org.bpc.pixez.geometry.{Line, Rectangle}

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D}
import org.bpc.math.{V2, clamp}

class PixezVerticalSlider extends PixezSlider {
  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    val lineweight = {
      val mindim = Math.min(size.width, size.height) * .1f
      Math.min(mindim, 15f).toInt
    }
    val highlightPercentage = .25

    val knobRadius = lineweight + (lineweight * .1f)
    val margin = lineweight
    val channelLine = {
      val start = V2(size.width / 2, margin + (lineweight / 2))
      val end = V2(size.width / 2, size.height - margin - (lineweight / 2))
      Line(start, end)
    }
    var centerY = ((channelLine.lengthY - (2 * margin)) * (1 - animator.current)) + (2 * margin)
    val knobCenter = V2(size.width / 2, centerY)
    val knobBounds = Rectangle(knobCenter - V2(knobRadius, knobRadius), V2(knobRadius * 2))
    channelLine.draw(Color.BLACK, defaultStroke(lineweight), graphics)
    channelLine.copy(p1 = knobCenter).draw(accent, defaultStroke((lineweight * highlightPercentage).toInt), graphics)
    val gradient = knobBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
    knobBounds.toOval.fill(gradient, graphics)
    knobBounds.toOval.draw(Color.BLACK, defaultStroke(3), graphics)

  }

}
object PixezVerticalSlider {
  def apply(accent: Color): PixezVerticalSlider = {
    val slider = new PixezVerticalSlider
    slider.accent = accent
    slider
  }
}
