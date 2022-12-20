package org.bpc.math

class V2 {
  var (x, y) = (0.0, 0.0f)


  def +=(that: V2): V2 = {
    this.x += that.x
    this.y += that.y
    this
  }

  def -=(that: V2): V2 = {
    this.x -= that.x
    this.y -= that.y
    this
  }

  def *=(that: V2): V2 = {
    this.x *= that.x
    this.y *= that.y
    this
  }

  override def clone(): V2 = {
    val out = new V2()
    out.x = this.x
    out.y = this.y
    out
  }

  def +(that: V2): V2 = {
    this.clone() += that
  }

  def -(that: V2): V2 = {
    this.clone() -= that
  }

  def *(that: V2): V2 = {
    this.clone() *= that
  }

  def rotate(degree: Float): V2 = {
    val radians = Math.toRadians(degree)
    val (cos, sin) = (Math.cos(radians), Math.sin(radians))
    val (x1, y1) = (x * cos - y * sin, x * sin + y * cos)
    V2(x1.toFloat, y1.toFloat)
  }
}

object V2 {
  def apply(x: Float, y: Float): V2 = {
    val out = new V2()
    out.x = x
    out.y = y
    out
  }
}