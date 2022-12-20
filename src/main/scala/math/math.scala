package org.bpc.math

def clamp[A: Ordering](x: A, min: A, max: A): A = {
  val ord = summon[Ordering[A]]
  if (ord.compare(x, min) < 0) min
  else if (ord.compare(x, max) > 0) max
  else x
}

def lerp(a: Float, b: Float, t: Float): Float = {
  a + (b - a) * t
}