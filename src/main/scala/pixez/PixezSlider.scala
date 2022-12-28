package org.bpc
package pixez

import java.awt.{BasicStroke, Color, GradientPaint, RenderingHints}
import scala.swing.{Component, Graphics2D, Orientation, Publisher}
import math.{V2, clamp, lerp}
import org.bpc.pixez.animation.*
import org.bpc.pixez.event.Value

import scala.math.abs
import scala.swing.event.MouseWheelMoved

class PixezSlider extends PixezComponent with Publisher {
    protected val interpolator = new Interpolator(speed = 0.1)
    protected val animator = new Animator(this, interpolator)
  
    listenTo(mouse.clicks, mouse.wheel)
    reactions += { case MouseWheelMoved(_, _, _, rotation) =>
        interpolator.nudge(-(rotation / 50f))
        this.publish(Value(interpolator.ideal))
    }
}

