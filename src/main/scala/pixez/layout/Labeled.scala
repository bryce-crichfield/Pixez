package org.bpc
package pixez.layout

import org.bpc.pixez.AutoTextLine
import java.awt.Color
import scala.swing.*
import pixez.TextLine
import pixez.event.Value

class Labeled(name: String, component: Component) extends MigPanel {
    columnConstraints = "[fill, grow]"
    rowConstraints = "[fill, grow]0[]"
    this.opaque = false
    val label = new AutoTextLine(24)
    label.text = s"$name:   0%"
    label.hAlign = TextLine.HorizontalAlignment.Center
    label.vAlign = TextLine.VerticalAlignment.Center
    layout(component) = "grow, wrap"
    layout(label) = "h :25:"
    label.listenTo(component)
    label.reactions += {
        case Value(value) =>
            label.text = s"$name: ${formatAsPercent(value)}%"
            label.repaint()
    }

//    this.border = Swing.BeveledBorder(Swing.Raised, Color.black, Color.darkGray)

    def formatAsPercent(value: Float): String = {
        val percent = Math.round(value * 100).toInt.toString
        if (percent.length == 1) "  " + percent
        else if (percent.length == 2) " " + percent
        else percent
    }
}
