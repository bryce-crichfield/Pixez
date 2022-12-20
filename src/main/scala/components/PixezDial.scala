package org.bpc.components

import org.bpc.math.*

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, Paint, RadialGradientPaint, RenderingHints}
import javax.swing.Timer
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial(highlight: Color) extends Component {
  private val animator = new PixezDialAnimator(this)
  private val painter = new PixezDialPainter(this, animator)

  override def paint(g: Graphics2D): Unit = {
    super.paint(g)
    painter.paint(g)
  }

  var lastY = this.size.height / 2
  listenTo(mouse.clicks, mouse.moves, mouse.wheel)
  reactions += {
    case MouseDragged(_, point, _) =>
      val deltaY = point.y - lastY
      lastY = point.y
      animator.nudge(deltaY.toInt)
    case MouseReleased(_, point, _, _, _) =>
      lastY = this.size.height / 2
    case MouseWheelMoved(_, _, _, rotation) =>
      animator.nudge(rotation / 100f)
  }

}

