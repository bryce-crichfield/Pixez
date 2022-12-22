package org.bpc

import org.bpc.components.{PixezButton, PixezComponent, PixezDial, PixezHorizontalSlider, PixezVerticalSlider}
import org.bpc.components.event.Value
import java.awt.{Color, Graphics, RenderingHints}
import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager

def SimpleLabel(publisher: Publisher): Label = {
  val label = new Label("0.0")
  label.preferredSize = (new Dimension(100, 20))
  label.background = new Color(0x30, 0x30, 0x30)
  label.foreground = Color.WHITE
  label.opaque = true
  label.font = Font("SansSerif", Font.Bold, 18)
  publisher.reactions += {
    case Value(value) => label.text = f"${value}%1.2f"
  }
  label
}


class MainFrame extends Frame {
  contents = new BoxPanel(Orientation.NoOrientation) {
    this.background = new Color(0x30, 0x30, 0x30)
    this.preferredSize = (new Dimension(1600, 900))
    contents += new BoxPanel(Orientation.Vertical) {
      this.opaque = false
      contents ++= {
        val dial = PixezDial(Color.BLUE)
        Seq(dial, SimpleLabel(dial))
      }
      contents ++= {
        val dial = PixezDial(Color.RED)
        Seq(dial, SimpleLabel(dial))
      }
      contents ++= {
        val dial = PixezDial(Color.MAGENTA)
        Seq(dial, SimpleLabel(dial))
      }
    }
    contents += Swing.HStrut(25)
    contents += new BoxPanel(Orientation.Vertical) {
      this.opaque = false
      contents ++= {
        val slider = PixezHorizontalSlider(Color.ORANGE)
        Seq(slider, SimpleLabel(slider))
      }
      contents ++= {
        val slider = PixezHorizontalSlider(Color.GREEN)
        Seq(slider, SimpleLabel(slider))
      }
      contents ++= {
        val slider = PixezHorizontalSlider(Color.CYAN)
        Seq(slider, SimpleLabel(slider))
      }
    }
    contents += Swing.HStrut(25)
    contents += new BoxPanel(Orientation.Vertical) {
      this.opaque = false
      contents ++= {
        val slider = PixezVerticalSlider(Color.PINK)
        Seq(slider, SimpleLabel(slider))
      }

    }
    contents += Swing.HStrut(25)
    contents += new BoxPanel(Orientation.Vertical) {
      this.opaque = false
      contents ++= {
        val slider = PixezVerticalSlider(Color.BLUE)
        Seq(slider, SimpleLabel(slider))
      }

    }
    contents += Swing.HStrut(25)
    contents += new BoxPanel(Orientation.Vertical) {
      this.preferredSize = new Dimension(100, 100)
      this.opaque = false
      contents += PixezButton(Color.GREEN, "Green", 15)
      contents += PixezButton(Color.RED, "Red", 15)
      contents += PixezButton(Color.MAGENTA, "Magenta", 15)
    }
    contents += new BoxPanel(Orientation.Vertical) {
      this.preferredSize = new Dimension(100, 100)
      this.opaque = false
      contents += PixezButton(Color.YELLOW, "Yellow", 15)
      contents += PixezButton(Color.CYAN, "Cyan", 15)
      contents += PixezButton(Color.ORANGE, "Orange", 15)
    }

  }

}

object Main extends SwingApplication {
  override def startup(args: Array[String]): Unit = {
//    PixezComponent.debug = true

    val frame = new MainFrame()
    frame.visible = true
    frame.centerOnScreen()
  }

  override def shutdown(): Unit = {
    println("Goodbye, World!")
  }
}
