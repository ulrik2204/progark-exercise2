package controllers

import models.BallModel
import views.BallView

class BallController(model: BallModel, view: BallView): GameSpriteController(model, view) {
}