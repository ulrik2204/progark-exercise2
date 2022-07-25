package com.progark.exercise2

import states.GameStateManager
import states.MenuState
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class MyGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()
        GameStateManager.push(MenuState(0f))
    }

    override fun render() {
        ScreenUtils.clear(1f, 1f, 1f, 1f)
        GameStateManager.update(Gdx.graphics.deltaTime)
        GameStateManager.render(batch)

    }

    override fun dispose() {
        batch.dispose()
    }


}