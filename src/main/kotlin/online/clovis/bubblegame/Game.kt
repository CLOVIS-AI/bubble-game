package online.clovis.bubblegame

import online.clovis.bubblegame.obstacles.Obstacle
import online.clovis.bubblegame.obstacles.Platform

class Game(val players: Set<Player>) {

    var ground = Screen.height.toFloat()
        private set

    private var obstacles = ArrayList<Obstacle>()

    fun update(){
        if(Screen.random(10f) < 1)
            obstacles.add(Platform(
                    Screen.random(Screen.width.toFloat()),
                    50f
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
        obstacles.forEach { it.draw() }
        players.forEach { it.draw() }
    }

}