package org.bpc
package pixez.layout

import net.miginfocom.swing.MigLayout

import javax.swing.JPanel
import scala.swing.{Component, LayoutContainer, Panel}

class MigPanel()
  extends Panel with LayoutContainer {
  override type Constraints = String
  private var _layoutConstraints = ""
  private var _columnConstraints = ""
  private var _rowConstraints = ""
  override lazy val peer: JPanel = new JPanel(new MigLayout()) with SuperMixin

  def layoutConstraints: String = _layoutConstraints
  def layoutConstraints_=(constraints: String): Unit = {
    _layoutConstraints = constraints
    peer.setLayout(new MigLayout(layoutConstraints, columnConstraints, rowConstraints))
  }

  def columnConstraints: String = _columnConstraints
  def columnConstraints_=(constraints: String): Unit = {
    _columnConstraints = constraints
    peer.setLayout(new MigLayout(layoutConstraints, columnConstraints, rowConstraints))
  }

  def rowConstraints: String = _rowConstraints
  def rowConstraints_=(constraints: String): Unit = {
    _rowConstraints = constraints
    peer.setLayout(new MigLayout(layoutConstraints, columnConstraints, rowConstraints))
  }

  private def layoutManager: MigLayout = peer.getLayout.asInstanceOf[MigLayout]
  protected def constraintsFor(c: Component): Constraints = {
    // TODO: Provide backwards conversion from string to MigPanel.Constraint,
    // or just store the constraint enums locally
    // as it is, this is an error
    layoutManager.getConstraintMap.get(c.peer).asInstanceOf[String]
  }

  protected def areValid(c: Constraints): (Boolean, String) = (true, "")

  protected def add(comp: Component, constraints: Constraints): Unit = {
    peer.add(comp.peer, constraints)
  }
  
  def remove(comp: Component): Unit = peer.remove(comp.peer)
}


