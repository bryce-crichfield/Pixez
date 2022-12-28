package org.bpc
package pixez.animation
import org.bpc.pixez.PixezComponent

import javax.swing.Timer
import scala.swing.Component
// This is what bridges our component and our
// tweens, triggering repaints as needed.
trait Animated { 
    this: PixezComponent=>
    private val chrono = new Chronometer()
    private var oneTimePaint = false
    private var timer: Option[Timer] = None
    
    def startAnimator(motion: Motion): Unit = {
        timer.foreach(_.stop())
        timer = Some(new Timer(33, _ => {
            val delta = chrono.update()
            motion.update(delta)
            if motion.isRunning then
                repaint()
                oneTimePaint = true
            else if oneTimePaint then
                repaint()
                oneTimePaint = false
        }))
        timer.foreach(_.start())
    }
    
    def stopAnimator(): Unit = {
        timer.foreach(_.stop())
        timer = None
    }
}