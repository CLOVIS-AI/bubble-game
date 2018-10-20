package online.clovis.bubblegame

data class Player(
        var name: String,
        var color: Int,
        val moveLeft: Int,
        val moveRight: Int,
        val jump: Int
) {

    val keys: Array<Pair<Int, Boolean>> = arrayOf(
            moveLeft to false,
            moveRight to false,
            jump to false
    )
    var doubleJumpAvailable = true

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

        if(keys[0].second)
            x -= 2f
        if(keys[1].second)
            x += 2f
        if(keys[2].second && doubleJumpAvailable) {
            speed = -5f
            doubleJumpAvailable = false
        }

        y += speed
        if(y + halfWidth >= game.ground){
            y = game.ground - halfWidth
            collide()
        }
    }

    fun collide() {
        if(speed >= 0)
            speed = -5f
        speed -= 0.5f

        doubleJumpAvailable = true
    }

    fun draw(){
        Screen.fill(color)
        Screen.ellipse(x, y, width, width)
    }

}