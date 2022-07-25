package controllers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import models.GameSpriteModel
import views.GameSpriteView

open class GameSpriteController(
    protected val model: GameSpriteModel,
    protected val view: GameSpriteView
) {

    fun overlaps(sprite: GameSpriteController): Boolean {
        return view.overlaps(sprite.view)
    }

    private fun handleFace() {
        if (model.velocity.x < 0 && !view.facingLeft || model.velocity.x > 0 && view.facingLeft)
            view.flip()
    }

    open fun handleInput() {
    }

    private fun handleBoundCollisions() {
        val halfWidth = model.width / 2f
        val halfHeight = model.height / 2f
        val collideWallLeft = model.position.x < halfWidth
        val collideWallRight = model.position.x > Gdx.graphics.width - halfWidth
        if (collideWallLeft || collideWallRight) {
            model.onWallCollision()
        } else if (model.position.y > Gdx.graphics.height - halfHeight) {
            model.onRoofCollision()
        } else if (model.position.y < halfHeight) {
            model.onFloorCollision()
        }
    }

    fun update(dt: Float) {
        handleInput()
        handleBoundCollisions()
        model.applyPositionChange(dt)
        handleFace()
        view.updateAnimationFrame(dt)
    }

    fun render(sb: SpriteBatch) {
        return view.render(sb, model.position)
    }

    fun dispose() {
        return view.dispose()
    }

    fun isLost(): Boolean {
        return model.isLost()
    }

    companion object {
        fun handleSpriteCollision(sprite1: GameSpriteController, sprite2: GameSpriteController) {
            return GameSpriteModel.handleSpriteCollision(sprite1.model, sprite2.model)
        }
    }



}