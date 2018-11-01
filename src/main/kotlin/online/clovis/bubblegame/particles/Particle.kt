package online.clovis.bubblegame.particles

import online.clovis.bubblegame.Game
import online.clovis.bubblegame.Screen

class Particle(
        var x: Float,
        var y: Float,
        val color: Int,
        var speedX: Float,
        var speedY: Float
) {

    fun draw(){
        speedY += 0.01f

        x += speedX
        y += speedY

        Screen.fill(color)
        Screen.ellipse(x, y, 3f, 3f)
    }

    companion object Handler {

        val particles = ArrayList<Particle>()

        fun create(x: Float, y: Float, color: Int, number: Int){
            val strength = number / 5f
            repeat(number) {
                particles.add(Particle(
                        x,
                        y,
                        color,
                        Screen.random(-strength, strength),
                        Screen.random(-strength, strength)
                ))
            }
        }

        fun draw(g: Game){
            particles.removeIf {
                it.y >= g.ground
            }

            particles.forEach {
                it.draw()
            }
        }

    }

}