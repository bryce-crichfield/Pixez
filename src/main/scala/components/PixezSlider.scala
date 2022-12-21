package org.bpc
package components

import java.awt.{BasicStroke, Color, GradientPaint, RenderingHints}
import scala.swing.{Component, Graphics2D, Orientation}
import math.{V2, clamp, lerp}

import scala.math.abs
import scala.swing.event.MouseWheelMoved

class PixezSlider(color: Color, val orientation: Orientation.Value = Orientation.Horizontal) extends Component {
    private val style = new PixezStyle()
    private val animator = new OneDimensionalAnimator(this, 0.35f)
    private var handleDiameter = 20
    style.weight = 8
    style.accent = color

    override def paint(g: Graphics2D): Unit = {
        super.paint(g)
        g.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON
        )
        // TODO: painting required a lot of tweaking, might
        // be good to do a refactor
        orientation match
            case Orientation.Vertical => paintVertical(g)
            case _                    => paintHorizontal(g)
        debugBox(g)
    }

    private def paintVertical(g: Graphics2D): Unit = {
        val start = V2(size.width / 2, style.margin)
        val end = V2(size.width / 2, size.height - style.margin)
        val center = V2(size.width / 2, size.height * (1 - animator.value))
        val handleTopLeft = {
            val radius = handleDiameter / 2f
            val min = style.margin - (style.weight / 2f)
            val max = size.height - style.margin - handleDiameter +
                (style.weight / 2f)
            val x = center.x - radius
            val y = clamp(center.y - radius, min, max)
            V2(x, y)
        }
        // Draw the Channel
        g.setColor(Color.BLACK)
        g.setStroke(new BasicStroke(
          style.weight,
          BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND
        ))
        g.drawLine(
          ((size.width / 2f) + (style.weight / 16f)).toInt,
          style.margin,
          ((size.width / 2f) + (style.weight / 16f)).toInt,
          size.height - style.margin
        )

        g.setColor(style.accent)
        g.setStroke(new BasicStroke(
          style.weight / 2,
          BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND
        ))
        // NOTE: causes bug when slider hits top
        if (handleTopLeft.y.toInt >= style.margin) {
            g.drawLine(
              size.width / 2 + (style.weight / 16).toInt,
              size.height - style.margin,
              size.width / 2 + (style.weight / 16).toInt,
              handleTopLeft.y.toInt + style.weight
            )
        }

        g.setPaint(new GradientPaint(
          handleTopLeft.x,
          handleTopLeft.y,
          Color.DARK_GRAY,
          handleTopLeft.x + handleDiameter,
          handleTopLeft.y,
          Color.BLACK
        ))
        g.fillOval(
          handleTopLeft.x.toInt,
          handleTopLeft.y.toInt,
          handleDiameter,
          handleDiameter
        )

    }
    private def paintHorizontal(g: Graphics2D): Unit = {
        val start = V2(style.margin, size.height / 2)
        val end = V2(size.width - style.margin, size.height / 2)
        // Draw the Slider Handle
        val center = V2(size.width * animator.value, size.height / 2)
        val handleTopLeft = {
            val radius = handleDiameter / 2f
            val min = style.margin - (style.weight / 2f)
            val max = size.width - style.margin - handleDiameter +
                (style.weight / 2f)
            val x = clamp(center.x - radius, min, max)
            val y = center.y - radius
            V2(x, y)
        }
        g.setColor(Color.BLACK)
        g.setStroke(new BasicStroke(
          style.weight,
          BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND
        ))
        g.drawLine(
          style.margin,
          ((size.height / 2) + (style.weight / 16f)).toInt,
          size.width - style.margin,
          ((size.height / 2) + (style.weight / 16f)).toInt
        )

        g.setColor(style.accent)
        g.setStroke(new BasicStroke(
          style.weight / 2,
          BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND
        ))
        if (handleTopLeft.x.toInt >= style.margin) {
            g.drawLine(
              style.margin,
              size.height / 2,
              handleTopLeft.x.toInt,
              size.height / 2
            )
        }

        g.setPaint(new GradientPaint(
          handleTopLeft.x,
          handleTopLeft.y,
          Color.DARK_GRAY,
          handleTopLeft.x,
          handleTopLeft.y + handleDiameter,
          Color.BLACK
        ))
        g.fillOval(
          handleTopLeft.x.toInt,
          handleTopLeft.y.toInt,
          handleDiameter,
          handleDiameter
        )

    }

    private def debugBox(g: Graphics2D): Unit = {
        g.setColor(Color.RED)
        g.setStroke(new BasicStroke(2))
        g.drawRect(0, 0, this.size.width, this.size.height)
    }

    listenTo(mouse.clicks, mouse.wheel)
    reactions += { case MouseWheelMoved(_, _, _, rotation) =>
        animator.nudge(rotation / 100f)
    }

}

