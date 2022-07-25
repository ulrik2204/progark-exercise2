package models

import com.badlogic.gdx.math.Vector2

class BallModel(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    startAcc: Vector2 = Vector2(-1f, -9.8f),
    startSpeed: Vector2 = Vector2(0f, 0f)

) : GameSpriteModel(
    x,
    y,
    width,
    height,
    startAcc,
    startSpeed
) {
}