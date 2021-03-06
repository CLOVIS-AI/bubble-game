package online.clovis.bubblegame

import online.clovis.bubblegame.particles.Particle
import online.clovis.utils.Color

data class Player(
        var name: String,
        var color: Color,
        val moveLeft: Int,
        val moveRight: Int,
        val jump: Int
) {

    val keys: Array<Pair<Int, Boolean>> = arrayOf(
            moveLeft to false,
            moveRight to false,
            jump to false
    )
    var doubleJumpAvailable = true

    var x = Screen.width.toFloat() / 2
    var y = 0f

    var speed = 0f

    val width = 20f
    val halfWidth = width / 2

    var health = 100
        set(value) {field = value; displayHealth = 60}
    var displayHealth = 60
    val isAlive: Boolean
        get() = health >= 0

    init {
        println("+ Player: $this")
    }

    fun move(game: Game){
        speed += 0.1f

        if(keys[0].second)
            x -= 2f
        if(keys[1].second)
            x += 2f
        if(keys[2].second && doubleJumpAvailable) {
            speed = -7f
            doubleJumpAvailable = false
        }

        if(x < 0)
            x = Screen.width.toFloat()
        else if(x > Screen.width.toFloat())
            x = 0f

        y += speed
        if(y + halfWidth >= game.ground){
            y = game.ground - halfWidth
            health--

            if(health <= 0)
                Particle.create(x, y, color, 100)

            collide()
        }
    }

    fun collide() {
        if(speed >= 0)
            speed = -5f - Game.speed / 10
        speed -= 0.5f + Game.speed / 20

        doubleJumpAvailable = true

        Particle.create(x, y, color, 5)
    }

    fun draw(g: Game){
        Screen.fill(color)
        val top = g.ground - Screen.height
        if(y >= top - width) {
            Screen.ellipse(x, y, width, width)
        } else {
            Screen.triangle(x, top+5, x-7, top+14, x+7, top+14)
        }

        if(displayHealth >= 0){
            displayHealth--

            val c = Color(
                    color.red,
                    color.green,
                    color.blue,
                    displayHealth*255/60
            )
            Screen.fill(c)
            Screen.rect(x, y-width/2-10, health.toFloat()/2, 5f)
        }
    }

}