//package org.bpc
//package components
//
//import scala.swing.*
//import scala.swing.event.*
//import math.*
//
//import java.awt.{Color, RenderingHints, BasicStroke}
//import scala.math.abs
//
//class PixezSwitch extends Component {
//  private var (current, target) = (0f, 1f)
//  override def paint(g: Graphics2D): Unit = {
//    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
//    g.setColor(Color.DARK_GRAY)
//    g.fillRect(0, 0, size.width, size.height)
//    g.setColor(Color.LIGHT_GRAY)
//    g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND))
//    g.drawLine(0, size.height / 2, size.width, size.height / 2)
//    g.setColor(Color.WHITE)
//    g.fillOval(
//      (size.width * current).toInt,
//      (size.height / 2).toInt,
//      10, 10
//    )
//  }
//  Animator(this) {
//    if (current == target) AnimationState.Stop
//    else {
//      current = lerp(current, target, 0.1)
//      if (abs(current - target) < 0.01) {
//        current = target
//      }
//      AnimationState.Continue
//    }
//  }
//}
