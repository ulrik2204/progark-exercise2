package states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*
// The singleton
object GameStateManager {
    private val states: Stack<State> = Stack()

    fun push(state: State) {
        states.push(state)
    }

    fun set(state: State) {
        states.pop()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(sb: SpriteBatch) {
        states.peek().render(sb)
    }



}