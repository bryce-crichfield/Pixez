package org.bpc
package components

import scala.swing.*
import scala.swing.event.*
import java.awt.Color

class PixezButton extends Component {
  val padding = 5
  
  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    g.setColor(Color.black)
    g.fillRect(0, 0, size.width, size.height)
    g.setColor(Color.white)
    g.drawString("Button", padding, size.height - padding)
  }
}
