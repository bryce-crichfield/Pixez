package org.bpc.math

class V2 {
    var (x, y) = (0.0f, 0.0f)

    inline def += (that: V2): V2 = {
        this.x += that.x
        this.y += that.y
        this
    }

    inline def -= (that: V2): V2 = {
        this.x -= that.x
        this.y -= that.y
        this
    }

    inline def *= (that: V2): V2 = {
        this.x *= that.x
        this.y *= that.y
        this
    }

    inline def /= (that: V2): V2 = {
        this.x /= that.x
        this.y /= that.y
        this
    }

    inline def += (scalar: Float): V2 = {
        this.x += scalar
        this.y += scalar
        this
    }

    inline def -= (scalar: Float): V2 = {
        this.x -= scalar
        this.y -= scalar
        this
    }

    inline def *= (scalar: Float): V2 = {
        this.x *= scalar
        this.y *= scalar
        this
    }

    inline def /= (scalar: Float): V2 = {
        this.x /= scalar
        this.y /= scalar
        this
    }

    inline override def clone(): V2 = {
        val out = new V2()
        out.x = this.x
        out.y = this.y
        out
    }

    inline def + (that: V2): V2 = { this.clone() += that }

    inline def - (that: V2): V2 = { this.clone() -= that }

    inline def * (that: V2): V2 = { this.clone() *= that }

    inline def / (that: V2): V2 = { this.clone() /= that }

    inline def + (scalar: Float): V2 = { this.clone() += scalar }

    inline def - (scalar: Float): V2 = { this.clone() -= scalar }

    inline def * (scalar: Float): V2 = { this.clone() *= scalar }

    inline def / (scalar: Float): V2 = { this.clone() /= scalar }

    inline def rotate(degree: Float): V2 = {
        val radians = Math.toRadians(degree)
        val (cos, sin) = (Math.cos(radians), Math.sin(radians))
        val (x1, y1) = (x * cos - y * sin, x * sin + y * cos)
        V2(x1.toFloat, y1.toFloat)
    }
    inline def length: Float = Math.sqrt(x * x + y * y).toFloat
    inline def normalized: V2 = this / length

    inline def invertX: V2 = V2(-x, y)
    inline def invertY: V2 = V2(x, -y)

    override def toString: String = { f"V2($x%1.2f, $y%1.2f)" }
}

object V2 {
    inline def apply(uniform: Float): V2 = {
        val out = new V2()
        out.x = uniform
        out.y = uniform
        out
    }

    inline def apply(x: Float, y: Float): V2 = {
        val out = new V2()
        out.x = x
        out.y = y
        out
    }

    inline def fromAngle(degree: Float): V2 = {
        val radians = Math.toRadians(degree)
        val (cos, sin) = (Math.cos(radians), Math.sin(radians))
        V2(cos.toFloat, sin.toFloat)
    }

}
