package org.bpc
package pixez

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, RenderingHints}
import scala.swing.{Component, Graphics2D, Orientation, Publisher}

import org.bpc.pixez.animation.*
import org.bpc.pixez.event.Value
import org.bpc.pixez.paint.{HorizontalSliderPainter, SliderPainter, VerticalSliderPainter}

import scala.math.abs
import scala.swing.event.MouseWheelMoved

class PixezSlider (val painter: SliderPainter) extends PixezComponent with Publisher {
    protected val interpolator = new Interpolator(speed = 0.1)
    protected val animator = new Animator(this, interpolator)
    override def paint(graphics: Graphics2D): Unit = {
        super.paint(graphics)
        painter.paintSlider(size, accent, interpolator.value.toFloat, graphics)
    }

    listenTo(mouse.clicks, mouse.wheel)
    reactions += { case MouseWheelMoved(_, _, _, rotation) =>
        interpolator.nudge(-(rotation / 50f))
        this.publish(Value(interpolator.ideal))
    }
}

object PixezSlider {
  def Vertical(accent: Color): PixezSlider = {
      val slider = new PixezSlider(new VerticalSliderPainter())
      slider.accent = accent
      slider
  }

  def Horizontal(accent: Color): PixezSlider = {
      val slider = new PixezSlider(new HorizontalSliderPainter())
      slider.accent = accent
      slider
  }
}

