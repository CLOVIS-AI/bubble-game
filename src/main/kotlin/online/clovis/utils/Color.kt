package online.clovis.utils

import online.clovis.bubblegame.Screen

data class Color(val asInt: Int) {

    val alpha:  Int by lazy { asInt shr 24 and 0xFF }
    val red:    Int by lazy { asInt shr 16 and 0xFF }
    val green:  Int by lazy { asInt shr 8  and 0xFF }
    val blue:   Int by lazy { asInt        and 0xFF }

    constructor(red: Int, green: Int, blue: Int): this(Screen.color(red, green, blue, 255))

    constructor(red: Int, green: Int, blue: Int, alpha: Int): this(Screen.color(red, green, blue, alpha))

    operator fun plus(other: Color): Color {
        return Color(
                red + other.red,
                green + other.green,
                blue + other.blue,
                alpha + other.alpha
        )
    }

    operator fun plus(value: Int): Color {
        return Color(
                red + value,
                green + value,
                blue + value,
                alpha
        )
    }

    operator fun minus(other: Color): Color {
        return Color(
                red - other.red,
                green - other.green,
                blue - other.blue,
                alpha - other.alpha
        )
    }

    operator fun minus(value: Int): Color {
        return Color(
                red - value,
                green - value,
                blue - value,
                alpha
        )
    }

    infix fun stepTowards(other: Color): Color {
        return Color(
                red + if(other.red > red) 1 else -1,
                green + if(other.green > green) 1 else -1,
                blue + if(other.blue > blue) 1 else -1,
                alpha + if(other.alpha > alpha) 1 else -1
        )
    }

    operator fun unaryMinus(): Color {
        return Color(
                255 - red,
                255 - green,
                255 - blue
        )
    }

    fun toStringRGB() = "Color(red=$red, green=$green, blue=$blue, alpha=$alpha)"

    companion object {

        fun random(): Color{
            return Color(
                    Screen.random(255f).toInt(),
                    Screen.random(255f).toInt(),
                    Screen.random(255f).toInt()
            )
        }

    }

}