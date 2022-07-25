package controllers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import models.BallModel
import models.BirdModel
import models.HelicopterModel
import views.BallView
import views.BirdView
import views.HelicopterView
import kotlin.math.pow
import kotlin.random.Random

fun random(min: Float, max: Float, allowNegative: Boolean = true): Float {
    val negativity = if (allowNegative) (-1f).pow(Random.nextInt(0, 2)) else 1f
    return ((java.util.Random().nextFloat() * (max - min)) + min) * negativity
}

fun helicopterCreator(x: Float, y: Float, difficultyInterval: Int = 10): HelicopterController {
    val heliView = HelicopterView()
    val heliModel = HelicopterModel(x, y, heliView.width, heliView.height, difficultyInterval)
    return HelicopterController(heliModel, heliView)
}

fun ballCreatorRandom(): BallController {
    val ballView = BallView()
    val ballModel = BallModel(
        random(50f, Gdx.graphics.width - 50f),
        random(50f, Gdx.graphics.height - 50f),
        ballView.width, ballView.height,
        startSpeed = Vector2(random(0f, 20f), random(0f, 20f))
    )
    return BallController(ballModel, ballView)

}

fun birdCreatorRandom(): BirdController {
    val birdView = BirdView()
    val birdModel = BirdModel(
        random(50f, Gdx.graphics.width - 50f),
        random(50f, Gdx.graphics.height - 50f),
        birdView.width,
        birdView.height,
        startAcc = Vector2(random(1f, 10f), random(1f, 10f)),
        startSpeed = Vector2(random(0f, 20f), random(0f, 20f))
    )
    return BirdController(birdModel, birdView)
}