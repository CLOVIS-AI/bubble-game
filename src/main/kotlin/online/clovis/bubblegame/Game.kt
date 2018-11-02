package online.clovis.bubblegame

import online.clovis.bubblegame.obstacles.Obstacle
import online.clovis.bubblegame.particles.Particle

object Game{

    var ground: Float = 0f
        private set

    var speed: Float = 0f
        private set

    var speedBoost: Float = 0f
        private set

    var obstacles = ArrayList<Obstacle>()

    lateinit var players: MutableList<Player>

    fun init(players: MutableList<Player>){
        this.players = players
    }

    fun update(){
        if(players.asSequence().any { it.y < ground - Screen.height })
            speedBoost += 0.025f
        else if(speedBoost > 0)
            speedBoost -= 0.050f
        else if(speedBoost < 0)
            speedBoost = 0f

        speed += 0.001f
        val currentSpeed = speed + speedBoost
        ground -= currentSpeed

        if(Screen.random(5f) < (currentSpeed/10) + 1)
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

        Screen.pushMatrix()
        Screen.background(0)
        Screen.translate(1f, Screen.height - ground)
        Particle.draw()

        obstacles.forEach { it.draw() }
        players.forEach { it.draw(this) }

        Screen.popMatrix()
    }

}