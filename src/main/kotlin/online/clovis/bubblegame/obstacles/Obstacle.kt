package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Game
import online.clovis.bubblegame.Player
import online.clovis.bubblegame.Screen
import online.clovis.bubblegame.particles.Particle.Handler.create

abstract class Obstacle(
        protected var x: Float,
        protected var y: Float,
        protected var color: Int,
        protected var sizeX: Float,
        protected var sizeY: Float
) {

    protected var speedX = 0f
    protected var speedY = 0f

    fun move() {
        if(speedX > 0.1) speedX -= 0.1f else if(speedX < -0.1) speedX += 0.1f else speedX = 0f
        if(speedY > 0.1) speedY -= 0.1f else if(speedY < -0.1) speedY += 0.1f else speedY = 0f

        x += speedX
        y += speedY

        update()
    }

    protected open fun update(){}

    fun draw(){
        Screen.fill(color)
        Screen.rect(x, y, sizeX, sizeY)
    }

    private fun isCollidingWith(player: Player): Boolean {
        return player.x + player.halfWidth > this.x - sizeX / 2
            && player.x - player.halfWidth < this.x + sizeX / 2
            && player.y + player.halfWidth > this.y - sizeY / 2
            && player.y - player.halfWidth < this.y + sizeY
    }

    infix fun collideWith(player: Player){
        if (isCollidingWith(player))
            applyColliding(player)
    }

    protected open fun applyColliding(player: Player){
        create(player.x, player.y, color, 1)

        player.collide()
    }

    fun isOnScreen(game: Game): Boolean {
        return x + sizeX/2 >= 0
            && x - sizeX/2 <= Screen.width
            && y - sizeX/2 <= game.ground
    }

    companion object Handler {

        fun create(g: Game): Obstacle {
            val x = Screen.random(Screen.width.toFloat())
            val y = g.ground - Screen.height
            when (Screen.random(0f, 100f).toInt()) {
                in 0..2 -> return HealthRegenerator(x, y)
                in 3..5 -> return Blocker(x, y)
                else -> return Platform(x, y)
            }
        }

    }

}