package org.bpc
package pixez.animation
import scala.concurrent.duration.Duration
class Playlist() extends Motion {

  import scala.collection.mutable.ListBuffer

  private val tweens = ListBuffer.empty[Tween]

  def +=(tween: Tween): Unit = {
    tweens += tween
  }

  def update(delta: Duration): Unit = {
    if (tweens.nonEmpty) {
      tweens.head.update(delta)
      if (!tweens.head.isRunning) {
        tweens.remove(0)
      }
    }
  }

  def isRunning: Boolean = tweens.nonEmpty

  def value: Double = {
    // TODO; this is not correct
    if (tweens.nonEmpty) {
      tweens.head.value
    } else {
      0
    }
  }

  def clear(): Unit = {
    tweens.clear()
  }
}
