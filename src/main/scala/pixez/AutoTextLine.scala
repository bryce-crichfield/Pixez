package org.bpc
package pixez

import java.awt.event.{ComponentAdapter, ComponentEvent}
import java.awt.{Font, Graphics2D}

class AutoTextLine(fontSize: Int = 12) extends TextLine(fontSize) {
  override def updateFont(graphics: Graphics2D): Unit = {
    // Optimistically set the font to the fontSize
    graphics.setFont(new Font("Arial", Font.PLAIN, fontSize))
    // Reduce the font size until the text fits
    constrainFontWidth(graphics)
    constrainFontHeight(graphics)
  }

  private def constrainFontWidth(graphics: Graphics2D): Unit = {
    while (graphics.getFontMetrics.stringWidth(text) > size.width) {
      graphics.setFont(new Font("Arial", Font.PLAIN, graphics.getFont.getSize - 1))
    }
  }

  private def constrainFontHeight(graphics: Graphics2D): Unit = {
    while (graphics.getFontMetrics.getHeight() > size.height) {
      graphics.setFont(new Font("Arial", Font.PLAIN, graphics.getFont.getSize - 1))
    }
  }


  peer.addComponentListener(new ComponentAdapter {
    override def componentResized(e: ComponentEvent): Unit = {
      repaint()
    }
  })
}
