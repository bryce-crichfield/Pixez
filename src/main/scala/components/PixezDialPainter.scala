package org.bpc
package components

import org.bpc.math.V2

import java.awt.{BasicStroke, Color, GradientPaint, RenderingHints}
import scala.swing.{Component, Graphics2D}

class PixezDialPainter(val component: Component, animator: PixezDialAnimator) extends Painter {
  val lineWeight = 2
  val padding = 5
  val highlight = Color.RED
  override def paint(g: Graphics2D): Unit = {
    val (width, height) = (component.size.width, component.size.height)
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    // Draw Body
    // TODO: Enforce a fixed 1:1 aspect ratio
    g.setPaint(new GradientPaint(0, 0, Color.DARK_GRAY, 0, height, Color.BLACK))
    g.fillOval(
      padding,
      padding,
      width - padding * 2,
      height - padding * 2
    )
    // Draw Arrow

    val center = V2(width / 2f, height / 2f)
    val line = center + (animator.arrow(-1) * (center - V2(lineWeight, lineWeight) - V2(padding, padding)))
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
      width - padding * 2,
      height - padding * 2,
      90, (360 - animator.angle).toInt
    )
  }
}
