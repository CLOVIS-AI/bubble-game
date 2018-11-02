package online.clovis.bubblegame.obstacles

import online.clovis.bubblegame.Game
import online.clovis.bubblegame.Player
import online.clovis.utils.Color

class Platform(
        x: Float,
        y: Float
) : Obstacle(
        x, y,
        Color(125, 125, 125),
        50f,
        5f
) {

    override fun update() {
        color = Game.oppositeColor
    }

    override fun applyColliding(player: Player) {
        super.applyColliding(player)

        speedY += 0.5f
    }
}