package online.clovis.bubblegame

data class Player(
        var name: String,
        var color: Int
) {

    var x: Float = Screen.width / 2f
    var y: Float = 0f

    init {
        println("+ Joueur: $this")
    }

    fun draw(){
        Screen.fill(color)
        Screen.ellipse(x, y, 20f, 20f)
    }

}