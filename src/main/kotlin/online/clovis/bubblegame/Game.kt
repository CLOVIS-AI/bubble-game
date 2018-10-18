package online.clovis.bubblegame

import online.clovis.bubblegame.obstacles.Obstacle
import online.clovis.bubblegame.obstacles.Platform

class Game(val players: Set<Player>) {

    var ground = Screen.height.toFloat()
        private set

    private var obstacles = ArrayList<Obstacle>()

    var speed = 0f
        private set

    fun update(){
        speed += 0.001f
        ground -= speed

        if(Screen.random(10f) < 1)
            obstacles.add(Platform(
                    Screen.random(Screen.width.toFloat()),
                    ground - Screen.height
                )
            )

        obstacles.removeIf { !it.isOnScreen() }
        obstacles.forEach { it.move() }

        players.forEach { player ->
            player.move(this)
            obstacles.forEach { it collideWith player }
        }
    }

    fun draw(){
        update()

        Screen.background(0)
        Screen.translate(1f, Screen.height - ground)
        obstacles.forEach { it.draw() }
        players.forEach { it.draw() }
    }

}