//package org.bpc
//package components
//
//import org.bpc.math.{clamp, lerp, V2}
//
//import java.awt.{BasicStroke, Color, RenderingHints, GradientPaint}
//import scala.math.abs
//import scala.swing.*
//import scala.swing.event.*
//
//class PixezToggleButton(highlight: Color) extends Component {
//    private var selected = false
//    val style = new PixezStyle()
//    style.accent = highlight.darker()
//    // TODO: requires a different easing function to remove weird artifacts
//    val animator = new OneDimensionalAnimator(this, .45f)
//
//    override def paint(g: Graphics2D): Unit = {
//        super.paint(g)
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
//        val topleft = V2(style.margin, style.margin + (animator.value * (style.margin / 4)))
//        val bottomright = V2(size.width - style.margin, size.height - style.margin + (animator.value * (style.margin / 4)))
//        g.setPaint(new GradientPaint(
//            topleft.x, topleft.y, Color.DARK_GRAY,
//            topleft.x, bottomright.y , Color.BLACK
//        ))
//        g.fillRoundRect(
//            topleft.x.toInt,
//            topleft.y.toInt,
//            (bottomright.x - topleft.x).toInt,
//            (bottomright.y - topleft.y).toInt, 10, 10)
//        val color = if (animator.value > .1) style.accent else Color.BLACK
//        g.setColor(color)
//        g.setStroke(new BasicStroke(style.weight))
//        g.drawRoundRect(
//            topleft.x.toInt,
//            topleft.y.toInt,
//            (bottomright.x - topleft.x).toInt,
//            (bottomright.y - topleft.y).toInt, 10, 10)
//        debugBox(g)
//    }
//    private def debugBox(g: Graphics2D): Unit = {
//        g.setColor(Color.RED)
//        g.setStroke(new BasicStroke(2))
//        g.drawRect(0, 0, this.size.width, this.size.height)
//    }
//
//    listenTo(mouse.clicks)
//    reactions += {
//        case e: MouseClicked =>
//            selected = !selected
//            if (selected) animator.nudge(1) else animator.nudge(-1)
//    }
//}
//
