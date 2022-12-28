package org.bpc
package pixez.animation
import javax.swing.Timer
import scala.swing.Component
// This is what bridges our component and our
// tweens, triggering repaints as needed.
class Animator(component: Component, motion: Motion) {
  private val chrono = new Chronometer()
  private var oneTimePaint = false
  
  new Timer(33, _ => {
    val delta = chrono.update()
    motion.update(delta)
    if motion.isRunning then
      component.repaint()
      oneTimePaint = true
    else if oneTimePaint then
      component.repaint()
      oneTimePaint = false
  }).start()

}

