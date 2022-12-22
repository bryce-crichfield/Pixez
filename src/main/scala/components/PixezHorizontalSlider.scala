package org.bpc
package components

import java.awt.{Color, Graphics2D, BasicStroke, GradientPaint}
import org.bpc.math.{clamp, V2}
import PixezGeometry.*

class PixezHorizontalSlider extends PixezSlider {
  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    val channelLine = {
      val start = V2(margin + (lineweight / 2), size.height/2)
      val end = V2(size.width - margin - (lineweight / 2), size.height/2)
      Line(start, end)
    }
    var centerX  = ((channelLine.lengthX - (2 * margin))  * (animator.current)) + (2 * margin)
    val knobCenter = V2(centerX, size.height/2)
    val knobBounds = Rectangle(knobCenter - V2(knobRadius), V2(knobRadius * 2))
    channelLine.draw(Color.BLACK, defaultStroke(), graphics)
    channelLine.copy(p2 = knobCenter).draw(accent, defaultStroke((lineweight * highlightPercentage).toInt), graphics)
    val gradient = knobBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
    knobBounds.toOval.fill(gradient, graphics)
    knobBounds.toOval.draw(Color.BLACK, defaultStroke(3), graphics)
  }
}
object PixezHorizontalSlider {
  def apply(accent: Color): PixezHorizontalSlider = {
    val slider = new PixezHorizontalSlider
    slider.accent = accent
    slider
  }
}
