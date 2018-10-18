package online.clovis.bubblegame

class Game(val players: Set<Player>) {

    fun draw(){
        Screen.background(0)
        players.forEach { it.draw() }
    }

}