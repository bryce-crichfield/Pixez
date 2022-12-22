package org.bpc
package components

import scala.math.abs
import scala.swing.Component
import org.bpc.components.Animator
import org.bpc.math.{clamp, lerp}

class OneDimensionalAnimator(val component: PixezComponent, speed: Float)
    extends Animator {
    var (current, target) = (0f, 0f)



    override def animate(): Animator.State = {
        if (target == current) return Animator.Stop
        current = lerp(current, target, .1)
//        current = lerp(current, target, speed)
        current = clamp(current, 0, 1)
        if (abs(current - target) < 0.001f) { current = target }
        Animator.Continue
    }

    inline def nudge(amount: Float): Unit = {
        target += amount
        target = clamp(target, 0, 1)
    }

}
