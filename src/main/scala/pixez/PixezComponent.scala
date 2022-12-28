package org.bpc
package pixez

import java.awt.{BasicStroke, Color, RenderingHints}
import scala.swing.*

trait PixezComponent extends Component with Publisher {
  var primary = Color.darkGray
  var secondary = Color.gray
  var accent = Color.green
  var text = ""
  var fontsize = 12
  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    if (PixezComponent.debugBox) {
      graphics.setStroke(new BasicStroke(1))
      graphics.setColor(Color.RED)
      graphics.drawRect(0, 0, this.size.width - 1 , this.size.height - 1)
    }
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
  }

  override def repaint(): Unit = {
    super.repaint()
    PixezComponent.repaints += 1
  }

  inline def defaultStroke(weight: Int): BasicStroke =
    new BasicStroke(weight, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

}
object PixezComponent {
  private var repaints = 0

  var debugBox: Boolean = false
  var trackRepaint: Boolean = false

  new javax.swing.Timer(1000, _ => {
    if (trackRepaint) {
      println(s"Repaints Per Second: $repaints")
      repaints = 0
    }
  }).start()
}

