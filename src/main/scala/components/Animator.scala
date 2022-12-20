package org.bpc
package components

import components.AnimationState.{Continue, Stop}

import javax.swing.Timer
import scala.swing.{Component, Swing}

class Animator private (owner: Component, animate: () => AnimationState) {
  private val timer = new Timer(1000/45, Swing.ActionListener(_ => {
    animate() match
      case Stop => ()
      case Continue => owner.repaint()
  })).start()
}
object Animator {
  def apply(owner: Component)(animate:  => AnimationState): Animator = {
    new Animator(owner, () => animate)
  }
}
