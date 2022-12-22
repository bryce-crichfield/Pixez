package org.bpc
package components

import org.bpc.math.{clamp, lerp}

import javax.swing.Timer
import scala.math.abs
import scala.swing.{Component, Swing}

trait Animator {
  val component: PixezComponent
  private val timer = new Timer(1000/45, Swing.ActionListener(_ => {
    animate() match
      case Animator.Stop => ()
      case Animator.Continue => component.repaint()
  })).start()

  def animate(): Animator.State
}
object Animator {
  sealed trait State
  case object Continue extends State
  case object Stop extends State
}



