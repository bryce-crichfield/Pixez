package org.bpc
package pixez

import pixez.animation.*
import pixez.event.Value
import pixez.paint.DialPainter

import java.awt.{Color, Graphics2D, *}
import scala.swing.*
import scala.swing.event.*

class PixezDial extends PixezComponent with Publisher with Animated {
    private val interpolator = new Interpolator(speed = 0.25)
    this.startAnimator(interpolator)
    val painter = new DialPainter()
    
    override def paint(graphics: Graphics2D): Unit = {
      super.paint(graphics)
      painter.paint(size, interpolator.value, graphics)
    }
  
    var lastY = size.height / 2
    listenTo(mouse.clicks, mouse.moves, mouse.wheel)
    reactions += {
        case MouseWheelMoved(_, _, _, rotation) =>
          interpolator.nudge(-(rotation/100f))
          this.publish(Value(interpolator.value.toFloat))
    }
}
object PixezDial {
  def apply(accent: Color = Color.GREEN): PixezDial = {
    val dial = new PixezDial
    dial.painter.accent = accent
    dial
  }
}



