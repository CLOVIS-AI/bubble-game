package online.clovis.bubblegame

data class Player(
        var name: String,
        var color: Int
) {

    var x = Screen.width.toFloat() / 2
        private set
    var y = 0f
        private set

    var speed = 0f
        private set

    val width = 20f
    val halfWidth = width / 2

    init {
        println("+ Joueur: $this")
    }

    fun draw(){
        Screen.fill(color)
        Screen.ellipse(x, y, 20f, 20f)
    }

}