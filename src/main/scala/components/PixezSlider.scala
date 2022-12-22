package org.bpc
package components

import java.awt.{BasicStroke, Color, GradientPaint, RenderingHints}
import scala.swing.{Component, Graphics2D, Orientation, Publisher}
import math.{V2, clamp, lerp}

import org.bpc.components.event.Value

import scala.math.abs
import scala.swing.event.MouseWheelMoved

trait PixezSlider extends PixezComponent with Publisher {
    protected val animator = new OneDimensionalAnimator(this, 0.35f)

    var knobRadius = 15
    var highlightPercentage = .25
    this.lineweight = 15
    this.margin = 15
  
    listenTo(mouse.clicks, mouse.wheel)
    reactions += { case MouseWheelMoved(_, _, _, rotation) =>
        animator.nudge(-(rotation / 50f))
        this.publish(Value(animator.target))
    }
}

