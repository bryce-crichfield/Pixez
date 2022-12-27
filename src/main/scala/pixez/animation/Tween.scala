package org.bpc
package pixez.animation

import math.clamp
import java.time.Duration
case class Tween(start: Double, end: Double, duration: Duration, easing: Easing) {
  private var current = start
  private var elapsed: Duration = Duration.ZERO

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
  def isRunning: Boolean = elapsed.compareTo(duration) <= 0
}
