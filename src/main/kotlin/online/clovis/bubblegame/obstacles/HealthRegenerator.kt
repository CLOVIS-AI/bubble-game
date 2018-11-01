package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Player
import online.clovis.bubblegame.Screen
import online.clovis.bubblegame.particles.Particle

class HealthRegenerator(
        x: Float,
        y: Float
) : Obstacle(
        x, y,
        Screen.color(0, 200, 0),
        50f,
        5f
) {

    override fun applyColliding(player: Player) {
        super.applyColliding(player)

        player.health += 5
        speedY += 5

        Particle.create(x, y, color, 3)
    }
}