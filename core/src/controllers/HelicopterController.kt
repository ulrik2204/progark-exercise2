package controllers

import com.badlogic.gdx.Gdx
import models.HelicopterModel
import views.HelicopterView

class HelicopterController(model: HelicopterModel, view: HelicopterView) :
    GameSpriteController(model, view) {

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            model.onInput()
        }
    }
}