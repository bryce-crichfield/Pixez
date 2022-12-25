package org.bpc
package pixez

import java.awt.{BasicStroke, Stroke}

package object paint {
  def RoundStroke(weight: Float): Stroke = {
    new BasicStroke(weight.toInt, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
  }
}
