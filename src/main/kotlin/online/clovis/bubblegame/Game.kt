package online.clovis.bubblegame

import online.clovis.bubblegame.obstacles.Obstacle
import online.clovis.bubblegame.obstacles.Platform

class Game(val players: Set<Player>) {

    var ground: Float = 0f
        private set

    var speed: Float = 0f
        private set

    var speedBoost: Float = 0f
        private set

    var obstacles = ArrayList<Obstacle>()

    fun update(){
        if(players.asSequence().any { it.y < ground - Screen.height })
            speedBoost += 0.025f
        else if(speedBoost > 0)
            speedBoost -= 0.040f
        else if(speedBoost < 0)
            speedBoost = 0f

        speed += 0.001f
        ground -= speed + speedBoost

        if(Screen.random(5f) < 1)
            obstacles.add(Platform(
                    Screen.random(Screen.width.toFloat()),
                    ground - Screen.height
                )
            )

        obstacles.removeIf { !it.isOnScreen(this) }
        obstacles.forEach { it.move() }

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
        obstacles.forEach { it.draw() }
        players.forEach { it.draw() }
        Screen.popMatrix()
    }

}