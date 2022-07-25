package views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

open class GameSpriteView(
    internalPaths: List<String>,
    var facingLeft: Boolean = true
) {

    private var animationState = 0
    private var frameDisplayTime = 0f
    private val FRAME_CHANGE_INTERVAL = 0.1f
    protected val pictures = internalPaths.map { Texture(it) }
    protected var sprite = Sprite(pictures[animationState])
    val width = sprite.width
    val height = sprite.height

    fun render(sb: SpriteBatch, position: Vector2) {
        sprite.setCenter(position.x, position.y)
        sprite.draw(sb)
    }

    fun overlaps(other: GameSpriteView): Boolean {
        val thisRect = this.sprite.boundingRectangle
        val otherRect = other.sprite.boundingRectangle
        return thisRect.overlaps(otherRect)
    }

    fun flip() {
        facingLeft = !facingLeft
        sprite.flip(true, false)
    }


    open fun updateAnimationFrame(dt: Float) {
        frameDisplayTime += dt
        if (frameDisplayTime >= FRAME_CHANGE_INTERVAL) {
            frameDisplayTime = 0f
            animationState = (animationState + 1) % pictures.size
            sprite = Sprite(pictures[animationState])
            if (!facingLeft) sprite.flip(true, false)
        }
    }

    fun dispose() {
        pictures.forEach() { it.dispose() }

    }

}