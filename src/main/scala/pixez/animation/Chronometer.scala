package org.bpc
package pixez.animation
import scala.concurrent.duration.Duration
// Keeps delta time between frames
class Chronometer {
  private var last = Duration.fromNanos(System.nanoTime())
  
  def update(): Duration = {
    val now = Duration.fromNanos(System.nanoTime())
    val delta = now.minus(last)
    last = now
    delta  
  }
}
