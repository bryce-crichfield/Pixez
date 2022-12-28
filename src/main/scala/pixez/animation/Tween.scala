package org.bpc
package pixez.animation

import math.clamp
import scala.concurrent.duration.Duration

case class Tween(start: Double, end: Double, duration: Duration, easing: Easing) {
  private [animation] var current = start
  private var elapsed: Duration = Duration.Zero

  def update(delta: Duration): Unit = {
    elapsed = elapsed.plus(delta)
    if start > end then {
      current = start - easing(end, start, duration, elapsed)
      current = clamp(current, end, start)
    } else {
      current = easing(start, end, duration, elapsed)
      current = clamp(current, start, end)
    }
  }

  def value: Double = current

  def isRunning: Boolean = elapsed <= duration
  
}
