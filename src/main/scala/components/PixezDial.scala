package org.bpc
package components

import components.{OneDimensionalAnimator, PixezStyle}
import math.*

import java.awt.{Color, Graphics2D, RenderingHints, GradientPaint, BasicStroke}
import javax.swing.Timer
import scala.swing.*
import scala.swing.event.*

// TODO: Add styling options
// TODO: Add publisher/reactive integration
class PixezDial(highlight: Color) extends Component {
    private val animator = new OneDimensionalAnimator(this, .2f)
    val style = new PixezStyle()
    // TODO: Add better way to set the styles
    style.accent = highlight
    // TODO: Optimize this
    override def paint(g: Graphics2D): Unit = {
        val start = System.nanoTime()
        super.paint(g)
        g.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON
        )
        // This turns out to be rather slow for larger shapes,
        // the java.awt.geom.Ellipse2D might be a better choice
        // for the dial itself.
        // Dimension Calculations
        val (width, height) = (this.size.width, this.size.height)
        val center = V2(width / 2f, height / 2f)
        val mindim = Math.min(width, height)
        val diameter = mindim - style.margin * 2
        val topleft = center - (V2(mindim) / 2) + V2(style.margin)
        val bottomleft = topleft + V2(0, diameter)
        // Draw Body
        g.setPaint(new GradientPaint(
          topleft.x,
          topleft.y,
          Color.DARK_GRAY,
          bottomleft.x,
          bottomleft.y,
          Color.BLACK
        ))
        g.fillOval(topleft.x.toInt, topleft.y.toInt, diameter, diameter)
        // Draw Arrow
        val line = center +
            (arrow(-1) * V2((diameter / 2f) - style.weight))
        g.setStroke(new BasicStroke(
          style.weight,
          BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND
        ))
        g.setColor(Color.WHITE)
        g.drawLine(center.x.toInt, center.y.toInt, line.x.toInt, line.y.toInt)
        // Draw Highlight
        g.setColor(highlight)
        g.drawArc(
          topleft.x.toInt,
          topleft.y.toInt,
          diameter,
          diameter,
          90,
          ((animator.value * 360)).toInt
        )
        debugBox(g)
        val end = System.nanoTime()
        println(s"Paint took ${(end - start) / 1000000f}ms")
    }

    var lastY = this.size.height / 2
    listenTo(mouse.clicks, mouse.moves, mouse.wheel)
    reactions += {
        case MouseDragged(_, point, _) =>
            val deltaY = point.y - lastY
            lastY = point.y
            animator.nudge(deltaY.toInt)
        case MouseReleased(_, point, _, _, _) => lastY = this.size.height / 2
        case MouseWheelMoved(_, _, _, rotation) => animator
                .nudge(rotation / 100f)
    }

    private def debugBox(g: Graphics2D): Unit = {
        g.setColor(Color.RED)
        g.setStroke(new BasicStroke(2))
        g.drawRect(0, 0, this.size.width, this.size.height)
    }


    private inline def arrow(direction: Float): V2 = {
        V2.fromAngle(90).invertY.rotate(animator.value * direction * 360)
    }
}
