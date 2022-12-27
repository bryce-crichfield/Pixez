package org.bpc

import net.miginfocom.swing.MigLayout
import org.bpc.pixez.{AutoTextLine, PixezButton, PixezComponent, PixezDial, PixezHorizontalSlider, PixezVerticalSlider, TextLine}
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
  Seq.fill(4)(PixezDial()).foreach(dial => layout(dial) = "growx")
  layout(PixezDial()) =  "growx, wrap"
  Seq.fill(4)(new PixezButton()).foreach(dial => layout(dial) = "growx")

}


//object Main extends SwingApplication {
//  class Test(panel: Panel) extends MainFrame {
//    this.background = new Color(0x30, 0x30, 0x30)
//    this.title = "Test"
//    this.preferredSize = new Dimension(400, 400)
//
//    contents = panel
//  }
//  override def startup(args: Array[String]): Unit = {
//    PixezComponent.debug = true
//    val frame = new Test(new TestPanel())
//    frame.visible = true
//    frame.centerOnScreen()
//  }
//
//  override def shutdown(): Unit = {
//    println("Goodbye, World!")
//  }
//}


object TweenTest extends App {

  import org.bpc.pixez.animation.*
  import java.time.Duration
  val t1 = Tween(0, 1, Duration.ofMillis(1000), Easing.Quadratic.Out)
  val t2 = Tween(1, 0, Duration.ofMillis(1000), Easing.Linear)

  val path = Path(t1, t2)

  var last = Duration.ofNanos(System.nanoTime())
  while (path.isRunning) {
    val now = Duration.ofNanos(System.nanoTime())
    val delta = now.minus(last)
    last = now
    path.update(delta)
    println(path.value)
    Thread.sleep(100)
  }
}