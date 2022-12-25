package org.bpc

import net.miginfocom.swing.MigLayout
import org.bpc.pixez.{MigPanel, PixezButton, PixezComponent, PixezDial, PixezHorizontalSlider, TextLine, PixezVerticalSlider, AutoTextLine}
import org.bpc.pixez.event.Value

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

class LabeledDial(name: String) extends MigPanel (
  columnConstraints = "[fill, grow]",
  rowConstraints = "[fill, grow]0[]",
){
  this.opaque = false
  val label = new AutoTextLine(35)
  label.text = s"$name:   "
  label.hAlign = TextLine.HorizontalAlignment.Right
  label.vAlign = TextLine.VerticalAlignment.Center
  def randColor() = new Color((Random.nextInt * 0xFFFFFF).toInt)
  val dial = PixezDial(randColor())

  layout(dial) = "grow, wrap"
  layout(label) = "h :25:"
  label.listenTo(dial)
  label.reactions += {
    case Value(value) =>
      label.text = s"$name: ${FormatAsPercent(value)}%"
      label.repaint()
  }

  this.border = Swing.BeveledBorder(Swing.Raised, Color.black, Color.darkGray)
}

class LabeledSlider extends MigPanel (
  columnConstraints = "[fill, grow]",
  rowConstraints = "[fill, grow]0[]",
){
  this.opaque = false
  val label = new TextLine(16)
  def randColor() = new Color((Random.nextInt * 0xFFFFFF).toInt)
  val slider = PixezHorizontalSlider(randColor())

  layout(slider) = "grow, wrap"
  layout(label) = "h 20!, align center"
  label.listenTo(slider)
  label.reactions += {
    case Value(value) =>
      label.text = f"$value%1.2f"
      label.repaint()
  }

  this.border = Swing.LineBorder(Color.BLACK, 5)
}


class TestPanel extends MigPanel(
  columnConstraints = "[fill, grow]",
  rowConstraints = "[grow, fill]"
) {
  this.opaque = true
  this.background = new Color(0x30, 0x30, 0x30)

  Seq.fill(4)(new LabeledDial("Test")).foreach(dial => layout(dial) = "growx")
  layout(new LabeledDial("Test")) = "growx, wrap"
  Seq.fill(4)(new LabeledDial("Test")).foreach(dial => layout(dial) = "growx")
  layout(new LabeledDial("Test")) = "growx, wrap"
  Seq.fill(4)(new LabeledDial("Test")).foreach(dial => layout(dial) = "growx")

}


object Main extends SwingApplication {
  class Test(panel: Panel) extends MainFrame {
    this.background = new Color(0x30, 0x30, 0x30)
    this.title = "Test"
    this.preferredSize = new Dimension(800, 600)

    contents = panel
  }
  override def startup(args: Array[String]): Unit = {
//    PixezComponent.debug = true

    val frame = new Test(new TestPanel())
    frame.visible = true
    frame.centerOnScreen()
  }

  override def shutdown(): Unit = {
    println("Goodbye, World!")
  }
}
