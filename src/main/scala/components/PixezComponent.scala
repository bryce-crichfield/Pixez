package org.bpc
package components

import java.awt.{Color, RenderingHints, BasicStroke}
import scala.swing.*

trait PixezComponent extends Component with Publisher {
  var primary = Color.darkGray
  var secondary = Color.gray
  var accent = Color.green
  var margin = 8
  var lineweight = 3
  var text = ""
  var fontsize = 12
  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    if (PixezComponent.debug) {
      graphics.setStroke(new BasicStroke(1))
      graphics.setColor(Color.RED)
      graphics.drawRect(0, 0, this.size.width - 1 , this.size.height - 1)
    }
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
  }
  inline def defaultStroke(weight: Int = lineweight): BasicStroke = 
    new BasicStroke(weight, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)

}
object PixezComponent {
  var debug: Boolean = false

}
