package org.bpc
package pixez.animation
import java.time.Duration
trait Easing {
  def apply(start: Double, end: Double, duration: Duration, elapsed: Duration): Double
}

object Easing {
  val Linear: Easing = (start, end, duration, elapsed) => {
    val t = elapsed.toMillis / duration.toMillis.toDouble
    start + (end - start) * t
  }

  object Quadratic {
    val In: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start + (end - start) * t * t
    }

    val Out: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start - (end - start) * t * (t - 2)
    }

    val InOut: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      if t < 0.5 then start + (end - start) * 2 * t * t
      else start - (end - start) * 2 * t * (t - 2) - 1
    }
  }

  object Cubic {
    val In: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start + (end - start) * t * t * t
    }

    val Out: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start + (end - start) * ((t - 1) * (t - 1) * (t - 1) + 1)
    }

    val InOut: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      if t < 0.5 then start + (end - start) * 4 * t * t * t
      else start + (end - start) * (t * (2 * t - 2) * (2 * t - 2) + 2)
    }
  }

  object Sinusoidal {
    val In: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start - (end - start) * Math.cos(t * (Math.PI / 2))
    }

    val Out: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start + (end - start) * Math.sin(t * (Math.PI / 2))
    }

    val InOut: Easing = (start, end, duration, elapsed) => {
      val t = elapsed.toMillis / duration.toMillis.toDouble
      start - (end - start) / 2 * (Math.cos(Math.PI * t) - 1)
    }
  }
}