package org.bpc
package components

import scala.swing.{Component, Graphics2D}

trait Painter {
  val component: Component
  def paint(g: Graphics2D): Unit
}
