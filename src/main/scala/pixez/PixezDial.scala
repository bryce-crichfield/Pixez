package org.bpc
package pixez

import org.bpc.pixez.event.Value
import org.bpc.pixez.paint.DialPainter
import org.bpc.pixez.animation.*
import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, RenderingHints}
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial extends PixezComponent with Publisher {
    private val interpolator = new Interpolator(speed = 0.1)
    private val animator = new animation.Animator(this, interpolator)
    val painter = new DialPainter()
    // TODO: REMOVE ME
    
    override def paint(graphics: Graphics2D): Unit = {
      super.paint(graphics)
      painter.paint(size, interpolator.value, graphics)
    }



    var lastY = size.height / 2
    listenTo(mouse.clicks, mouse.moves, mouse.wheel)
    reactions += {
//        case MouseDragged(_, point, _) =>
//            val deltaY = point.y - lastY
//            lastY = point.y
//            animator.nudge(deltaY *.25f)
//        case MouseReleased(_, point, _, _, _) => lastY = this.size.height / 2
        case MouseWheelMoved(_, _, _, rotation) =>
//            animator.nudge(-(rotation / 100f))
          import java.time.Duration
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



