package org.bpc

import net.miginfocom.swing.MigLayout
import org.bpc.pixez.{AutoTextLine, PixezButton, PixezComponent, PixezDial, PixezSlider, TextLine}
import org.bpc.pixez.event.Value
import org.bpc.pixez.layout.{Labeled, MigPanel, TabPanel}

import java.awt.{Color, Graphics, RenderingHints}
import scala.swing.*
import scala.swing.event.*
import javax.swing.{JPanel, UIManager}
import scala.util.Random


class TestTabPanel extends TabPanel {

    this.opaque = true
    this.background = new Color(0x30, 0x30, 0x30)
    val panel1 = new OscillatorPanel()
    val panel2 = new OscillatorPanel()
    val panel3 = new OscillatorPanel()
    this.addTab("Osc 1", panel1)
    this.addTab("Osc 2", panel2)
    this.addTab("Osc 3", panel3)
}

class OscillatorPanel() extends MigPanel {
    columnConstraints = "[25%, fill][25%, fill][50%, fill]"
    rowConstraints = "[grow, fill]"
    this.opaque = true
    this.background = new Color(0x30, 0x30, 0x30)

    layout(PixezButton(Color.orange, "sin")) = "cell 0 0"
    layout(PixezButton(Color.orange, "saw")) = "cell 0 1"
    layout(PixezButton(Color.orange, "sqr")) = "cell 0 2"

    layout(PixezButton(Color.orange, "tri")) = "cell 1 0"
    layout(PixezButton(Color.orange, "pls")) = "cell 1 1"
    layout(PixezButton(Color.orange, "rnd")) = "cell 1 2"

    layout(new Labeled("amp", PixezDial(Color.RED))) = "cell 2 0"
    layout(new Labeled("phs", PixezDial(Color.RED))) = "cell 2 1"
    layout(new Labeled("pan", PixezDial(Color.RED))) = "cell 2 2"

}

object Main extends SwingApplication {
  class Test(panel: Panel) extends MainFrame {
      this.preferredSize = new Dimension(800, 600)
    this.background = new Color(0x30, 0x30, 0x30)
    this.title = "Test"

    contents = panel
  }
  override def startup(args: Array[String]): Unit = {
//    PixezComponent.debugBox = true
    PixezComponent.trackRepaint = true
    val frame = new Test(new TestTabPanel())
    frame.visible = true
    frame.centerOnScreen()
  }

  override def shutdown(): Unit = {
    println("Goodbye, World!")
  }
}
