package org.bpc
package pixez.animation

import scala.concurrent.duration.Duration

trait Motion {
  def update(delta: Duration): Unit
  def isRunning: Boolean 
  def value: Double
}
