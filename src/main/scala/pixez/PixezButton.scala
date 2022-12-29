package org.bpc
package pixez

import pixez.animation.*
import pixez.math.V2
import pixez.paint.ButtonPainter
import java.awt.{BasicStroke, Color, GradientPaint, RenderingHints}
import scala.math.abs
import scala.swing.*
import scala.swing.event.*

class PixezButton() extends PixezComponent with Animated {
    private val playlist = new Playlist()
    this.startAnimator(playlist)
    val painter = new ButtonPainter()
    override def paint(graphics: Graphics2D): Unit = {
        super.paint(graphics)
        painter.accent = if (playlist.isRunning) this.accent else Color.white
        painter.paint(size, playlist.value, text, graphics)
    }
    
    listenTo(mouse.clicks)
    reactions += {
      case _: MouseClicked =>
        import scala.concurrent.duration.Duration
        playlist.clear()
        playlist += Tween(0, 1, durationMillis(175), Easing.Linear)
        playlist += Tween(1, 0, durationMillis(175), Easing.Linear)
        publish(ButtonClicked(null))
    }
}

object PixezButton {
  def apply(accent: Color, text: String, fontsize: Int = 15): PixezButton = {
    val button = new PixezButton()
    button.accent = accent
    button.text = text
    button.painter.fontsize = fontsize
    button
  }
}

