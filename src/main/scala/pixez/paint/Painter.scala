package org.bpc
package pixez.paint

import scala.swing.{Dimension, Graphics2D}

// A painter is responsible for the look and feel
// of its bound component.  It should have all the
// information needed to paint the component except
// the component itself. This allows the look and feel
// to be changed without changing the component.
trait Painter {
  def paint(size: Dimension, animationValue: Double, graphics: Graphics2D): Unit
}
