package models

import com.badlogic.gdx.math.Vector2

class HelicopterModel(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    private val difficultyInterval: Int = 10
) : GameSpriteModel(
    x,
    y,
    width,
    height,
    startAcc = Vector2(-1f, -9.8f)
) {
    private var clickCount = 0
    override fun onInput() {
        clickCount++
        velocity.y = 3f
        if (clickCount > difficultyInterval) {
            clickCount = 0
            acc.add(0f, -3f)
        }
    }

    /**
     * Method to check if the helicopter is in a loosing position
     */
    override fun isLost(): Boolean {
        return position.y <= height / 2f
    }
}