package online.clovis.bubblegame

import processing.core.PApplet

object Screen : PApplet() {

    override fun settings() {
        size(500, 500)
        //fullScreen()
    }

    override fun setup() {
        background(0)

        ellipseMode(CENTER)
        rectMode(CENTER)
        noStroke()

        Game.init(
            mutableListOf(
                Player("Ivan", color(255, 0, 0), 'q'.toInt(), 'd'.toInt(), 'z'.toInt()),
                Player("Marina", color(0, 0, 255), '4'.toInt(), '6'.toInt(), '8'.toInt()),
                Player("Invité", color(255, 255, 0), 'j'.toInt(), 'l'.toInt(), 'i'.toInt())
            )
        )
    }

    override fun draw() {
        Game.draw()

        fill(255)
        /*text("FPS: ${frameRate.toInt()}\n" +
             "Obstacles: ${game!!.obstacles.size}",
                10f, 10f)*/
    }

    override fun keyPressed() {
        Game.players.forEach {
            it.keys.forEachIndexed { index, pair ->
                if(pair.first == key.toInt())
                    it.keys[index] = pair.first to true
            }
        }
    }

    override fun keyReleased() {
        Game.players.forEach {
            it.keys.forEachIndexed { index, pair ->
                if(pair.first == key.toInt())
                    it.keys[index] = pair.first to false
            }
        }
    }

}