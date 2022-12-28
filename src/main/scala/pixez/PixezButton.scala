package org.bpc
package pixez

import org.bpc.math.{clamp, lerp, V2}

import java.awt.{BasicStroke, Color, RenderingHints, GradientPaint}
import scala.math.abs
import scala.swing.*
import scala.swing.event.*
import pixez.animation.*
class PixezButton() extends PixezComponent {
    private val playlist = new Playlist()
    private val animator = new animation.Animator(this, playlist)
    override def paint(graphics: Graphics2D): Unit = {
        super.paint(graphics)
        import org.bpc.pixez.geometry.*
        val margin = 5
        val down: Double = playlist.value * margin
        val topleft = V2(margin, margin + down.toFloat)
        val buttonSize = V2(size) - (margin * 2)

        val buttonBounds = Rectangle(topleft, buttonSize)
        val gradient = buttonBounds.leftSide.gradient(Color.DARK_GRAY, Color.BLACK)
        buttonBounds.fill(gradient, 10, graphics)
        var color = if (playlist.isRunning) accent else Color.BLACK
        val borderStroke = org.bpc.pixez.paint.RoundStroke(2)
        buttonBounds.draw(color, borderStroke,10,  graphics)
        color = if (playlist.isRunning) accent else Color.WHITE
        graphics.setFont(Font("SansSerif", Font.Bold, fontsize)  )
        val textWidth = graphics.getFontMetrics().stringWidth(text)
        val textHeight = graphics.getFontMetrics().getHeight()
        val textPos = V2(buttonBounds.center.x - textWidth / 2, buttonBounds.center.y + textHeight / 2)
        graphics.setColor(color)
        graphics.drawString(text, textPos.x, textPos.y)
    }
    
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>
        println("clicked")
        import scala.concurrent.duration.Duration
        playlist.clear()
        playlist += Tween(0, 1, durationMillis(100), Easing.Quadratic.In)
        playlist += Tween(1, 0, durationMillis(100), Easing.Quadratic.Out)
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

