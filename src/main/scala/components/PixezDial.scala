package org.bpc
package components

import components.OneDimensionalAnimator
import math.V2

import org.bpc.components.event.Value

import java.awt.{BasicStroke, Color, GradientPaint, Graphics2D, RenderingHints}
import javax.swing.Timer
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial extends PixezComponent with Publisher {
    private val animator = new OneDimensionalAnimator(this, .2f)
    // TODO: Optimize this
    override def paint(graphics: Graphics2D): Unit = {
      super.paint(graphics)
      import PixezGeometry.*;
      val center = V2(size).center
      val diameter = V2(V2(size).min) - (margin * 2)
      // Draw body as an oval with a linear gradient following the side of the dial
      val knobBounds = Rectangle(center - (V2(size).min / 2) + V2(margin), diameter)
      val gradient = knobBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
      val needleLine = Line(center, center + (arrow(-1) * ((diameter - (2*lineweight))/2f)) )
      val meterArc = Arc(knobBounds.topleft, diameter, 90, animator.current * 360)
      // Make the appropriate graphics calls
      knobBounds.toOval.fill(gradient, graphics)
      knobBounds.toOval.draw(Color.BLACK, defaultStroke(5), graphics)
      needleLine.draw(Color.WHITE, defaultStroke(), graphics)
      meterArc.draw(accent, defaultStroke(), graphics)
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



    private inline def arrow(direction: Float): V2 = {
        V2.fromAngle(90).invertY.rotate(animator.current * direction * 360)
    }
}
object PixezDial {
  def apply(accent: Color): PixezDial = {
    val dial = new PixezDial
    dial.accent = accent
    dial
  }
}
