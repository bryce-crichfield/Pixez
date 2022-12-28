package org.bpc

import net.miginfocom.swing.MigLayout
import org.bpc.pixez.{AutoTextLine, PixezButton, PixezComponent, PixezDial, PixezSlider, TextLine}
import org.bpc.pixez.event.Value
import org.bpc.pixez.layout.MigPanel

import java.awt.{Color, Graphics, RenderingHints}
import scala.swing.*
import scala.swing.event.*
import javax.swing.{JPanel, UIManager}
import scala.util.Random
def FormatAsPercent(value: Float): String = {
  val percent = Math.round(value * 100).toInt.toString
  if (percent.length == 1) "  " + percent
  else if (percent.length == 2) " " + percent
  else percent
}

class LabeledComponent(name: String, component: PixezComponent) extends MigPanel {
  columnConstraints = "[fill, grow]"
  rowConstraints = "[fill, grow]0[]"
  this.opaque = false
  val label = new AutoTextLine(35)
  label.text = s"$name:   0%"
  label.hAlign = TextLine.HorizontalAlignment.Center
  label.vAlign = TextLine.VerticalAlignment.Center
  layout(component) = "grow, wrap"
  layout(label) = "h :25:"
  label.listenTo(component)
  label.reactions += {
    case Value(value) =>
      label.text = s"$name: ${FormatAsPercent(value)}%"
      label.repaint()
  }

  this.border = Swing.BeveledBorder(Swing.Raised, Color.black, Color.darkGray)
}


class TestPanel extends MigPanel {
  columnConstraints = "[fill, grow]"
  rowConstraints = "[grow, fill]"
  this.opaque = true
  this.background = new Color(0x30, 0x30, 0x30)

  Seq.fill(4)(LabeledComponent("text", PixezDial())).foreach(dial => layout(dial) = "growx")
  layout(PixezDial()) = "growx, wrap"
  Seq.fill(4)(PixezSlider.Vertical(Color.RED)).foreach(dial => layout(dial) = "growx")
  layout(PixezDial()) =  "growx, wrap"
  Seq.fill(4)(PixezButton(Color.RED, "Text")).foreach(dial => layout(dial) = "growx")

}


object Main extends SwingApplication {
  class Test(panel: Panel) extends MainFrame {
    this.background = new Color(0x30, 0x30, 0x30)
    this.title = "Test"
    this.preferredSize = new Dimension(400, 400)

    contents = panel
  }
  override def startup(args: Array[String]): Unit = {
    PixezComponent.debugBox = true
    PixezComponent.trackRepaint = true
    val frame = new Test(new TestPanel())
    frame.visible = true
    frame.centerOnScreen()
  }

  override def shutdown(): Unit = {
    println("Goodbye, World!")
  }
}
