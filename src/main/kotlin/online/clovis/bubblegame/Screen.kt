package online.clovis.bubblegame

import processing.core.PApplet

object Screen : PApplet() {

    var game: Game? = null
        private set

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {
        background(0)

        ellipseMode(CENTER)
        rectMode(CENTER)
        noStroke()

        game = Game(
            setOf(
                Player("Ivan", color(255, 0, 0), 'q'.toInt(), 'd'.toInt())
            )
        )
    }

    override fun draw() {
        game!!.draw()
    }

    override fun keyPressed() {
        game!!.players.forEach {
            it.keys.forEachIndexed { index, pair ->
                if(pair.first == key.toInt() || pair.first == keyCode)
                    it.keys[index] = pair.first to true
            }
        }
    }

    override fun keyReleased() {
        game!!.players.forEach {
            it.keys.forEachIndexed { index, pair ->
                if(pair.first == key.toInt() || pair.first == keyCode)
                    it.keys[index] = pair.first to false
            }
        }
    }

}