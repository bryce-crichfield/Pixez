package org.bpc
package pixez

import pixez.OneDimensionalAnimator
import math.V2

import org.bpc.pixez.event.Value
import org.bpc.pixez.paint.DialPainter

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, RenderingHints}
import javax.swing.Timer
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial extends PixezComponent with Publisher {
    private val animator = new OneDimensionalAnimator(this, .2f)
    val painter = new DialPainter()
    // TODO: REMOVE ME
    
    override def paint(graphics: Graphics2D): Unit = {
      super.paint(graphics)
      // We invert the y-axis to make the dial to conform to AWT's coordinate system
      val needleDirectionVector = 
      painter.paintDial(size, animator.current, graphics)
    }



    var lastY = size.height / 2
    listenTo(mouse.clicks, mouse.moves, mouse.wheel)
    reactions += {
        case MouseDragged(_, point, _) =>
            val deltaY = point.y - lastY
            lastY = point.y
            animator.nudge(deltaY *.25f)
        case MouseReleased(_, point, _, _, _) => lastY = this.size.height / 2
        case MouseWheelMoved(_, _, _, rotation) =>
            animator.nudge(-(rotation / 100f))
            this.publish(Value(animator.target))
    }




}
object PixezDial {
  def apply(accent: Color = Color.GREEN): PixezDial = {
    val dial = new PixezDial
    dial.painter.accent = accent
    dial
  }
}



