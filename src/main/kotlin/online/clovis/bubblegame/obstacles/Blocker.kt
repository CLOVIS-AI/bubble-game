package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Player
import online.clovis.bubblegame.Screen
import online.clovis.bubblegame.particles.Particle

class Blocker(
        x: Float,
        y: Float
) : Obstacle(
        x, y,
        Screen.color(200, 0, 0),
        40f,
        5f
) {

    override fun applyColliding(player: Player) {
        if(player.speed > 0)
            super.applyColliding(player)
        else {
            player.speed = 5f

            Particle.create(x, y, color, 10)
        }
    }
}