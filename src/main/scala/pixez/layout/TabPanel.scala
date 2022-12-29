package org.bpc
package pixez.layout
import org.bpc.pixez.{AutoTextLine, TextLine}
import scala.swing.*
import java.awt.Color
import scala.swing.event.*
class TabPanel extends MigPanel {
    columnConstraints = "0[fill, grow]0"
    rowConstraints = "[]0[grow, fill]"
    import scala.collection.mutable.{ListBuffer, HashMap}

    private val tabs = ListBuffer.empty[AutoTextLine]
    private val components = HashMap.empty[String, Component]

    def addTab(name: String, component: Component): Unit = {
        val tab = new AutoTextLine(20)
        tab.hAlign = TextLine.HorizontalAlignment.Center
        tab.vAlign = TextLine.VerticalAlignment.Center
        tab.text = name
        tab.border = Swing.MatteBorder(0, 0, 1, 0, Color.white)
        tabs += tab
        components += name -> component
        tab.listenTo(tab.mouse.clicks)
        tab.reactions += {
            case _: MouseClicked => {
                select(name)
            }
        }
        select(name)
    }

    private def select(name: String): Unit = {
        this.peer.removeAll()
        tabs.zipWithIndex.foreach { case (tab, index) =>
            val style = if (index == tabs.length - 1) "wrap" else ""
            layout(tab) = "h 10%, " + style
            if (tab.text == name) tab.border = Swing.MatteBorder(0, 0, 1, 0, Color.red)
            else tab.border = Swing.MatteBorder(0, 0, 1, 0, Color.white)
        }
        this.add(components(name), "span, spany, growy,  wrap")
        revalidate()
        repaint()
    }

}
