package org.bpc
package pixez

import pixez.animation.*
import pixez.event.Value
import pixez.paint.{HorizontalSliderPainter, SliderPainter, VerticalSliderPainter}

import java.awt.*
import scala.math.abs
import scala.swing.event.MouseWheelMoved
import scala.swing.{Component, Graphics2D, Orientation, Publisher}

class PixezSlider (val painter: SliderPainter) extends PixezComponent with Publisher with Animated{
    protected val interpolator = new Interpolator(speed = 0.1)
    this.startAnimator(interpolator)
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

