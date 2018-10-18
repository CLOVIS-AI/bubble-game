package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Player
import online.clovis.bubblegame.Screen

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

    protected abstract fun applyColliding(player: Player)

    fun isOnScreen(): Boolean {
        return x + sizeX/2 >= 0
            && x - sizeX/2 <= Screen.width
            && y - sizeX/2 <= Screen.height
    }

}