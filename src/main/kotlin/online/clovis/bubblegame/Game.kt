package online.clovis.bubblegame

class Game(val players: Set<Player>) {

    var ground = Screen.height.toFloat()
        private set

    fun draw(){
        Screen.background(0)
        players.forEach {
            it.move(this)
            it.draw()
        }
    }

}