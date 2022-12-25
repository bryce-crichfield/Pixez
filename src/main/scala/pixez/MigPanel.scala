package org.bpc
package pixez

import net.miginfocom.swing.MigLayout

import javax.swing.JPanel
import scala.swing.{Component, LayoutContainer, Panel}

class MigPanel(layoutConstraints: String = "",
               columnConstraints: String = "",
               rowConstraints: String = "")
  extends Panel with LayoutContainer {
  override type Constraints = String

  override lazy val peer: JPanel = new JPanel(new MigLayout(layoutConstraints, columnConstraints, rowConstraints)) with SuperMixin

  private def layoutManager: MigLayout = peer.getLayout.asInstanceOf[MigLayout]
  protected def constraintsFor(c: Component): Constraints = {
    layoutManager.getConstraintMap.get(c.peer).asInstanceOf[String]
  }

  protected def areValid(c: Constraints): (Boolean, String) = (true, "")

  protected def add(comp: Component, c: Constraints): Unit = {
    peer.add(comp.peer, c)
  }
}
