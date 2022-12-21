package org.bpc
package components.dial

import components.Animator
import math.{V2, clamp, lerp}

import scala.swing.Component

class PixezDialAnimator(val component: Component) extends Animator {
  var (angle, targetAngle) = (0f, 1f)

  override def animate(): Animator.State = {
    // If we are at the target angle, stop animating
    if (angle == targetAngle) Animator.Stop
    else {
      // Otherwise, move towards the target angle
      angle = lerp(angle, targetAngle, 0.2)
      angle = clamp(angle, 0, 1)
      // If we are close, snap to the target, thus causing only
      // one animation frame to be scheduled
      if (Math.abs(targetAngle - angle) < 0.001) {
        angle = targetAngle
      }
      Animator.Continue
    }
  }

  inline def arrow(direction: Float): V2 = {
    V2.fromAngle(90).invertY.rotate(angle * direction * 360)
  }

  inline def nudge(amount: Float): Unit = {
    targetAngle += amount
    targetAngle = clamp(targetAngle, 0, 1)
  }
}
