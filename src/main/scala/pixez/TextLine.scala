package org.bpc
package pixez

import java.awt.{Color, Font, Graphics2D}
import scala.swing.Dimension
import TextLine.{HorizontalAlignment, VerticalAlignment}
import org.bpc.math.clamp

import java.awt.event.{ComponentAdapter, ComponentEvent}
import scala.swing.FlowPanel.Alignment


class TextLine(fontSize: Int) extends PixezComponent {
  var fontColor = Color.WHITE
  var hAlign = HorizontalAlignment.Left
  var vAlign = VerticalAlignment.Top

  override def paint(graphics: Graphics2D): Unit = {
    super.paint(graphics)
    updateFont(graphics)
    graphics.setColor(fontColor)
    val x = getHorizontalOffset(graphics)
    val y = getVerticalOffset(graphics)
    val line = text.split("\n").headOption.getOrElse("")
    graphics.drawString(line, x, y)
  }

  def updateFont(graphics: Graphics2D): Unit = {
    graphics.setFont(new Font("Arial", Font.PLAIN, fontSize))
  }

  private def getHorizontalOffset(graphics: Graphics2D): Int = {
    val stringWidth = graphics.getFontMetrics.stringWidth(text)
    val x = hAlign match {
      case HorizontalAlignment.Left => 0
      case HorizontalAlignment.Center => (size.width - stringWidth) / 2
      case HorizontalAlignment.Right => size.width - stringWidth
    }
    clamp(x, 0, size.width)
  }

  private def getVerticalOffset(graphics: Graphics2D): Int = {
    val height = graphics.getFontMetrics.getHeight()
    val y = vAlign match {
      case VerticalAlignment.Top => height
      case VerticalAlignment.Center => (size.height + height) / 2
      case VerticalAlignment.Bottom => size.height
    }
    clamp(y, 0, size.height)
  }
}

object TextLine {
  enum HorizontalAlignment {
    case Left, Center, Right
  }

  enum VerticalAlignment {
    case Top, Center, Bottom
  }



}