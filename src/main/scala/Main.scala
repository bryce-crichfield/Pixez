package org.bpc

import org.bpc.components.{PixezDial, PixezButton}

import java.awt.{Color, Graphics, RenderingHints}
import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager

class MainFrame extends Frame {
  contents = new BoxPanel(Orientation.NoOrientation) {
    this.background = Color.DARK_GRAY
    this.preferredSize = (new Dimension(200, 50))
    contents += new PixezDial(Color.BLUE)
    contents += Swing.HStrut(25)
    contents += new PixezButton()
  }

}

object Main extends SwingApplication {
  override def startup(args: Array[String]): Unit = {
    val frame = new MainFrame()
    frame.visible = true
    frame.centerOnScreen()
  }

  override def shutdown(): Unit = {
    println("Goodbye, World!")
  }
}
