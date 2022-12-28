package org.bpc
package pixez

package object animation {
  def durationMillis(millis: Long): scala.concurrent.duration.Duration = {
    scala.concurrent.duration.Duration.fromNanos(millis * 1000000)
  }
}
