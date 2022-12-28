package org.bpc
package pixez.animation
import pixez.math.*

import scala.concurrent.duration.Duration
import scala.math.*
class Interpolator(speed: Double) extends Motion {
  private var (current, target) = (0f, 0f)

  def update(delta: Duration): Unit = {
    current = lerp(current, target, speed.toFloat)
    current = clamp(current, 0, 1)
    // I want this but I can't have this??? :(
    val percentDifference = abs(current - target) / target
    if (abs(current - target) < 0.001) {
      current = target
    }
  }

  def isRunning: Boolean = {
    current != target
  }
  def ideal: Float = target
  def value: Double = current.toDouble

  def nudge(amount: Float): Unit = {
    target += amount
    target = clamp(target, 0, 1)
  }

}
