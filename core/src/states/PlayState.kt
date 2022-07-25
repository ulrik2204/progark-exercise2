package states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import controllers.GameSpriteController
import controllers.ballCreatorRandom
import controllers.birdCreatorRandom
import controllers.helicopterCreator

/**
 * The controler
 */
class PlayState(private val highScore: Float) : State() {

    private val sprites: List<GameSpriteController> = listOf(
        helicopterCreator(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f),
        birdCreatorRandom(), ballCreatorRandom(), ballCreatorRandom()
    )

    private val timerText = BitmapFont()
    private val time = System.currentTimeMillis()
    private var timer = 0f


    private fun handleCollisions() {
        val allSprites: List<GameSpriteController> = sprites
        val appliedCollisions = mutableListOf<GameSpriteController>()
        for (sprite in allSprites) {
            if (appliedCollisions.contains(sprite)) continue
            for (sprite2 in allSprites) {
                if (sprite === sprite2 || appliedCollisions.contains(sprite2)) continue
                if (sprite.overlaps(sprite2)) {
                    GameSpriteController.handleSpriteCollision(sprite, sprite2)
                    appliedCollisions.add(sprite)
                    appliedCollisions.add(sprite2)
                }
            }
        }
    }

    private fun handleLose() {
        val isLost = sprites.any { it.isLost() }
        if (isLost) {
            val highestScore = if (highScore > timer) highScore else timer
            GameStateManager.set(MenuState(highestScore))
        }
    }

    private fun renderText(sb: SpriteBatch) {
        val currentTime = (System.currentTimeMillis() - time) / 1000f
        val currentTimeText = currentTime.toString()
        timerText.draw(sb, "$currentTimeText s", 10f, Gdx.graphics.height.toFloat() - 30f)
        timer = currentTime
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sprites.forEach { it.render(sb) }
        renderText(sb)
        sb.end()

    }

    override fun update(dt: Float) {
        handleCollisions()
        sprites.forEach { it.handleInput() }
        sprites.forEach { it.update(dt) }
        handleLose()
    }

    override fun dispose() {
        sprites.forEach { it.dispose() }
    }


}