package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Player
import online.clovis.bubblegame.Screen

class Platform(
        x: Float,
        y: Float
) : Obstacle(
        x, y,
        Screen.color(125, 125, 125),
        50f,
        5f
) {

    override fun applyColliding(player: Player) {
        super.applyColliding(player)

        speedY += 0.5f
    }
}