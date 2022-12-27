package org.bpc
package pixez.animation
import java.time.Duration
class Path private() {

  import scala.collection.mutable.ListBuffer

  private val tweens = ListBuffer.empty[Tween]
  private var end = 0d

  def +=(tween: Tween): Unit = {
    if tweens.isEmpty then {
      end = tween.end
    }
    tweens += tween
  }

  def update(delta: Duration): Unit = {
    if (tweens.nonEmpty) {
      tweens.head.update(delta)
      if (!tweens.head.isRunning) {
        tweens.remove(0)
        tweens.headOption.foreach(tween => end = tween.end)
      }
    }
  }

  def isRunning: Boolean = tweens.nonEmpty

  def value: Double = {
    if (tweens.nonEmpty) tweens.head.value
    else end
  }
}

object Path {
  def apply(tweens: Tween*): Path = {
    val path = new Path
    tweens.foreach(path += _)
    path
  }
}