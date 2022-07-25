package models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import kotlin.math.pow

open class GameSpriteModel(
    x: Float,
    y: Float,
    val width: Float,
    val height: Float,
    startAcc: Vector2 = Vector2(10f, -9.8f),
    startSpeed: Vector2 = Vector2(0f, 0f),
    val mass: Float = 1f
) {
    // List of the textrues the helicopter sprite can take
    private var lastCollided: GameSpriteModel? = null


    // Determining what state it is in

    // Physics paramters
    var position = Vector2(x, y)
    var velocity = Vector2(startSpeed)
    var acc = Vector2(startAcc.x, startAcc.y)


    fun applyPositionChange(dt: Float) {
        val adjustment = velocity.add(acc.x * dt, acc.y * dt)
        position.add(adjustment)
//        if (velocity.x < 0 && !facingLeft) {
//            facingLeft = !facingLeft
//            sprite.flip(true, false)
//        } else if (velocity.x > 0 && facingLeft) {
//            facingLeft = !facingLeft
//            sprite.flip(true, false)
//        }
    }

    open fun onWallCollision() {
        val halfWidth = width / 2f
        val collideWallLeft = position.x < halfWidth
        acc.scl(-1f, 1f)
        velocity.scl(-1f, 1f)
        position.x = if (collideWallLeft) halfWidth else Gdx.graphics.width - halfWidth
        lastCollided = null
    }

    open fun onRoofCollision() {
        velocity.scl(1f, -1f)
        position.y = Gdx.graphics.height - height/2f
        lastCollided = null
    }

    open fun onFloorCollision() {
        velocity.scl(1f, -1f)
        position.y = height/2f
        lastCollided = null
    }

    open fun onInput() {

    }

    open fun isLost(): Boolean {
        return false
    }

    companion object {
        fun handleSpriteCollision(sprite1: GameSpriteModel, sprite2: GameSpriteModel) {
            if (sprite1.lastCollided === sprite2 && sprite2.lastCollided === sprite1) return
            val m1 = sprite1.mass
            val m2 = sprite2.mass
            val v1 = sprite1.velocity.cpy()
            val x1 = sprite1.position.cpy()
            val v2 = sprite2.velocity.cpy()
            val x2 = sprite2.position.cpy()
            val v1n = v1.cpy().sub(
                x1.cpy().sub(x2).scl(
                    ((2 * m2 / (m1 + m2)) *
                            ((v1.cpy().sub(v2))).dot(x1.cpy().sub(x2))) / (x1.cpy().sub(x2).len()
                        .pow(2))
                )
            )
            val v2n = v2.cpy().sub(
                x2.cpy().sub(x1).scl(
                    ((2 * m1 / (m1 + m2)) *
                            ((v2.cpy().sub(v1))).dot(x2.cpy().sub(x1))) / (x2.cpy().sub(x1).len()
                        .pow(2))
                )
            )
            sprite1.velocity = v1n
            sprite2.velocity = v2n
            sprite1.lastCollided = sprite2
            sprite2.lastCollided = sprite1
        }
    }
}