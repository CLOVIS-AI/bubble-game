package online.clovis.bubblegame.particles

import online.clovis.bubblegame.Game
import online.clovis.bubblegame.Screen
import online.clovis.utils.Color

class Particle(
        var x: Float,
        var y: Float,
        val color: Color,
        var speedX: Float,
        var speedY: Float,
        val size: Float
) {

    fun draw(){
        speedY += 0.01f

        x += speedX
        y += speedY

        if(x < 0 || x > Screen.width.toFloat())
            speedX = -speedX

        Screen.fill(color)
        Screen.ellipse(x, y, size, size)
    }

    companion object Handler {

        val particles = ArrayList<Particle>()

        fun create(x: Float, y: Float, color: Color, number: Int){
            val strength = number / 5f
            repeat(number) {
                particles.add(Particle(
                        x,
                        y,
                        color,
                        Screen.random(-strength, strength),
                        Screen.random(-strength, strength),
                        Screen.random(2f, 5f)
                ))
            }
        }

        fun draw(){
            particles.removeIf {
                it.y >= Game.ground
            }

            particles.forEach {
                it.draw()
            }
        }

    }

}