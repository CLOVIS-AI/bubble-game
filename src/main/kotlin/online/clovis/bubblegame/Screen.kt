package online.clovis.bubblegame

import processing.core.PApplet

object Screen : PApplet() {

    var game: Game? = null

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {
        background(0)

        ellipseMode(CENTER)
        rectMode(CENTER)

        game = Game(
            setOf(
                Player("Ivan", color(255, 0, 0))
            )
        )
    }

    override fun draw() {
        game!!.draw()
    }

}