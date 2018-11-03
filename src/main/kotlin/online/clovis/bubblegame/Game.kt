package online.clovis.bubblegame

import online.clovis.bubblegame.obstacles.Obstacle
import online.clovis.bubblegame.particles.Particle
import online.clovis.utils.Color

object Game{

    var ground: Float = 0f
        private set

    var speed: Float = 0f
        private set

    var speedBoost: Float = 0f
        private set

    var obstacles = ArrayList<Obstacle>()

    lateinit var players: MutableList<Player>

    var backgroundColor = Color(0, 0, 0)
    val oppositeColor
        get() = -backgroundColor
    var nextColor = backgroundColor
    var nextColorChange = 1

    fun init(players: MutableList<Player>){
        this.players = players
    }

    fun update(){
        if(players.any { it.y < ground - Screen.height })
            speedBoost += 0.025f
        else if(speedBoost > 0)
            speedBoost -= 0.050f
        else if(speedBoost < 0)
            speedBoost = 0f

        speed += 0.001f
        val currentSpeed = speed + speedBoost
        ground -= currentSpeed

        val chance = Screen.width / 400f
        if(Screen.random(chance) < (currentSpeed/10) + 1)
            obstacles.add(Obstacle.create(this))

        obstacles.removeIf { !it.isOnScreen(this) }
        obstacles.forEach { it.move() }

        players.removeIf { !it.isAlive }
        players.forEach { player ->
            player.move(this)
            obstacles.forEach { it collideWith player }
        }
    }

    fun draw(){
        update()

        if(speed >= nextColorChange * 1) {
            nextColor = Color.random()
            nextColorChange++
            println("The background color will now be $nextColor")
        }

        backgroundColor = backgroundColor stepTowards nextColor
        val color = backgroundColor + (speedBoost * 20).toInt()

        Screen.pushMatrix()
        Screen.background(color.asInt)
        Screen.translate(1f, Screen.height - ground)
        Particle.draw()

        obstacles.forEach { it.draw() }
        players.forEach { it.draw(this) }

        Screen.popMatrix()
    }

}