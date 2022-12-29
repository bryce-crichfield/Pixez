package org.bpc
package pixez.paint
import java.awt.Color
import scala.swing.{Dimension, Graphics2D, Font}
import pixez.geometry.Rectangle
import pixez.math.V2
class ButtonPainter {
    var lineweight: Int = 5
    var rounding: Int = 10
     var fontsize: Int = 12

    var accent: Color = Color.red
     var buttonLightColor: Color = Color.darkGray
     var buttonDarkColor: Color = Color.black

    private var buttonBounds: Rectangle = _
    def paint(size: Dimension, animationValue: Double, text: String, graphics: Graphics2D): Unit = {
        updatePaintParameters(size, animationValue.toFloat)
        paintButtonBody(graphics)
        updateFont(text, graphics)
        paintButtonText(text, graphics)
    }

    private def updatePaintParameters(size: Dimension, animationValue: Float): Unit = {
        val yOffset = lineweight * animationValue
        val topleft = V2(lineweight, lineweight + yOffset)
        val buttonSize = V2(size.width, size.height) - (lineweight * 2)
        buttonBounds = Rectangle(topleft, buttonSize)
    }

    private def updateFont(text: String, graphics: Graphics2D): Unit = {
        // Optimistically set the font to the fontSize
        graphics.setFont(Font("Arial", Font.Bold, fontsize))
        // Constrain the font width and height until the font is small enough
        // TODO: I don't work completely right, but it's close enough for now
        inline def condition = {
            val width = graphics.getFontMetrics.stringWidth(text) > buttonBounds.size.x
            val height = graphics.getFontMetrics.getHeight() > buttonBounds.size.y
            width && height
        }
        while (condition) {
            graphics.setFont(Font("Arial", Font.Bold, graphics.getFont.getSize - 1))
        }
    }


    private def paintButtonBody(graphics: Graphics2D): Unit = {
        val gradient = buttonBounds.leftSide.gradient(buttonLightColor, buttonDarkColor)
        buttonBounds.fill(gradient, rounding, graphics)
        val borderStroke = pixez.paint.RoundStroke(2)
        buttonBounds.draw(accent, borderStroke, rounding, graphics)
    }

    private def paintButtonText(text: String, graphics: Graphics2D): Unit = {
        val textWidth = graphics.getFontMetrics.stringWidth(text)
        val textHeight = graphics.getFontMetrics.getHeight
        val textPos = V2(buttonBounds.center.x - textWidth / 2, buttonBounds.center.y + textHeight / 2)
        graphics.setColor(accent)
        graphics.drawString(text, textPos.x, textPos.y)
    }

}
