package org.bpc

import org.bpc.components.dial.PixezDial
import org.bpc.components.PixezSlider
import java.awt.{Color, Graphics, RenderingHints}
import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager

class MainFrame extends Frame {
    contents = new BoxPanel(Orientation.NoOrientation) {
        this.background = Color.DARK_GRAY
        this.preferredSize = (new Dimension(800, 200))
        contents += new BoxPanel(Orientation.Vertical) {
            this.opaque = false
            contents += new BoxPanel(Orientation.NoOrientation) {
                this.opaque = false
                this.preferredSize = (new Dimension(50, 100))
                contents += new PixezDial(Color.BLUE)

            }
            contents += new PixezDial(Color.RED)
            contents += new PixezDial(new Color(0xa0, 0x20, 0xf0))
        }
        contents += Swing.HStrut(25)
        contents += new BoxPanel(Orientation.Vertical) {
            this.opaque = false
            contents += new PixezSlider(Color.RED)
            contents += new PixezSlider(Color.GREEN)
            contents += new PixezSlider(Color.ORANGE)
        }
        contents += Swing.HStrut(25)
        contents += new PixezSlider(Color.CYAN, Orientation.Vertical)
        contents += Swing.HStrut(25)
        contents += new PixezSlider(Color.MAGENTA, Orientation.Vertical)
        contents += Swing.HStrut(25)
        contents += new PixezSlider(Color.YELLOW, Orientation.Vertical)
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
