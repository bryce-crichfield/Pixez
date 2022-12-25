package org.bpc
package pixez

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D}
import org.bpc.math.{V2, clamp}
import org.bpc.pixez.paint.HorizontalSliderPainter

import scala.swing.Component

class PixezHorizontalSlider extends PixezSlider {
  private val painter = new HorizontalSliderPainter()
  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    painter.paintSlider(size, accent, animator.current, graphics)
  }

}
object PixezHorizontalSlider {
  def apply(accent: Color): PixezHorizontalSlider = {
    val slider = new PixezHorizontalSlider
//    slider.accent = accent
    slider
  }
}
import org.bpc.pixez.geometry.{Line, Rectangle}
