package online.clovis.bubblegame

data class Player(
        var name: String,
        var color: Int
) {

    var x: Int = Screen.width / 2
    var y: Int = 0

    init {
        println("+ Joueur: $this")
    }

}