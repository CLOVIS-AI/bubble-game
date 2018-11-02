package online.clovis.bubblegame

import online.clovis.utils.Color
import processing.core.PApplet

fun main(args: Array<String>){

    val c = Color(100, 100, 100)
    assert(Color(155, 155, 155) == -c)

    val main = Screen
    PApplet.runSketch(arrayOf("clovis.online.bubblegame.Screen"), main)
}
