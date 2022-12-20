package org.bpc
package components

sealed trait AnimationState
object AnimationState {
  case object Continue extends AnimationState
  case object Stop extends AnimationState
}
