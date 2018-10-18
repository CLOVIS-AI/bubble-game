package online.clovis.bubblegame

data class Player(
        var name: String,
        var color: Int
) {

    var x = Screen.width.toFloat() / 2
    var y = 0f

    var speed = 0f

    val width = 20f
    val halfWidth = width / 2

    init {
        println("+ Player: $this")
    }

    fun move(game: Game){
        speed += 0.1f

        y += speed
        if(y + halfWidth >= game.ground){
            y = game.ground - halfWidth
            collide()
        }
    }

    fun collide() {
        if(speed >= 0)
            speed = 0f
        speed -= 5f
    }

    fun draw(){
        Screen.fill(color)
        Screen.ellipse(x, y, width, width)
    }

}