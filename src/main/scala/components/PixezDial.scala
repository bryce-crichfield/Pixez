package org.bpc.components

import org.bpc.components.{AnimationState, Animator}
import org.bpc.math.*

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, Paint, RadialGradientPaint, RenderingHints}
import javax.swing.Timer
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial(highlight: Color) extends Component {
  val start_arrow = V2(0, -1)
  var arrow = start_arrow
  var direction = PixezDial.Direction.Clockwise
  val lineWeight = 3
  val padding = 5


  var lastY = this.size.height / 2
  private var angle = 360f
  private var targetAngle = 360f
  var lastDrawCall = 0L
  override def paint(g: Graphics2D): Unit = {
    val now = System.nanoTime()
    lastDrawCall = now
    super.paint(g)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    // Draw Body
    // TODO: Enforce a fixed 1:1 aspect ratio
    g.setPaint(new GradientPaint(0, 0, Color.DARK_GRAY, 0, this.size.height, Color.BLACK))
    g.fillOval(
      padding,
      padding,
      this.peer.getWidth() - padding * 2,
      this.peer.getHeight() - padding * 2
    )
    // Draw Arrow

    val center = V2(this.peer.getWidth() / 2f, this.peer.getHeight() / 2f)
    val line = center + (arrow * (center - V2(lineWeight, lineWeight) - V2(padding, padding)))
    val stroke = new BasicStroke(lineWeight, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)
    g.setColor(Color.white)
    g.setStroke(stroke)
    g.drawLine(center.x.toInt, center.y.toInt, line.x.toInt, line.y.toInt)
    // Draw Clipped Highlight
    val strokeArc = new BasicStroke(lineWeight, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL)
    g.setStroke(strokeArc)
    g.setColor(highlight)
    g.drawArc(padding,
      padding,
      this.peer.getWidth() - padding * 2,
      this.peer.getHeight() - padding * 2,
      90, (360-angle).toInt
    )
  }

  def nudge(amount: Int): Unit = {
    targetAngle += amount
    targetAngle = clamp(targetAngle, 0, 360)
  }

  Animator(this) {
    // If we are at the target angle, stop animating
    if (angle == targetAngle) AnimationState.Stop
    else {
      // Otherwise, move towards the target angle
      angle = lerp(angle, targetAngle, .1f)
      angle = clamp(angle, 0, 360)
      arrow = start_arrow.rotate(angle * direction.scalar)
      // If we are close, snap to the target, thus causing only
      // one animation frame to be scheduled
      if (Math.abs(targetAngle - angle) < 1) {
        angle = targetAngle
      }
      AnimationState.Continue
    }
  }



  listenTo(mouse.clicks, mouse.moves, mouse.wheel)
  reactions += {
    case MouseDragged(_, point, _) =>
      val deltaY = point.y - lastY
      lastY = point.y
      nudge(deltaY.toInt)
    case MouseReleased(_, point, _, _, _) =>
      lastY = this.size.height / 2
    case MouseWheelMoved(_, _, _, rotation) =>
      nudge(rotation * 10)
  }

}

object PixezDial {
  enum Direction(val scalar: Int) {
    case Clockwise extends Direction(1)
    case CounterClockwise extends Direction(-1)
  }
}