package org.bpc
package components

import org.bpc.math.{clamp, lerp, V2}

import java.awt.{BasicStroke, Color, RenderingHints, GradientPaint}
import scala.math.abs
import scala.swing.*
import scala.swing.event.*

class PixezButton() extends PixezComponent {
    private val animator = new OneDimensionalAnimator(this, .95f)
    override def paint(graphics: Graphics2D): Unit = {
        super.paint(graphics)
        import PixezGeometry.*
        val topleft = V2(margin, margin + (animator.current * (margin / 2)))
        val buttonSize = V2(size) - (margin * 2)

        val buttonBounds = Rectangle(topleft, buttonSize)
        val gradient = buttonBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
        buttonBounds.fill(gradient, 10, graphics)
        var color = if (animator.current > .1) accent else Color.BLACK
        buttonBounds.draw(color, defaultStroke(),10,  graphics)
        color = if (animator.current > .1) accent else Color.WHITE
        graphics.setFont(Font("SansSerif", Font.Bold, fontsize)  )
        val textWidth = graphics.getFontMetrics().stringWidth(text)
        val textHeight = graphics.getFontMetrics().getHeight()
        val textPos = V2(buttonBounds.center.x - textWidth / 2, buttonBounds.center.y + textHeight / 2)
        graphics.setColor(color)
        graphics.drawString(text, textPos.x, textPos.y)
    }
    
    listenTo(mouse.clicks)
    reactions += {
        case _: MousePressed => animator.nudge(1)
        case _: MouseReleased => animator.nudge(-1)
    }
}

object PixezButton {
  def apply(accent: Color, text: String, fontsize: Int = 15): PixezButton = {
    val button = new PixezButton()
    button.accent = accent
    button.text = text
    button.fontsize = fontsize
    button
  }
}

