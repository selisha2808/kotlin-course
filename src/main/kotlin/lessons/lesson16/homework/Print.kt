package org.example.lessons.lesson16.homework

private object AnsiP {
    const val RESET = "\u001B[0m"
    private fun fg(code: Int) = "\u001B[3${code}m"
    private fun bg(code: Int) = "\u001B[4${code}m"
    fun style(text: String, fg: Int? = null, bg: Int? = null): String =
        (fg?.let { fg(it) } ?: "") + (bg?.let { bg(it) } ?: "") + text + RESET
}

abstract class Printer { abstract fun print(text: String) }

class LaserPrinter : Printer() {
    override fun print(text: String) {
        text.split(Regex("\\s+"))
            .filter { it.isNotEmpty() }
            .forEach { w -> println(AnsiP.style(w, fg = 0, bg = 7)) } // black on white
    }
}

class InkjetPrinter : Printer() {
    private val palette: List<Pair<Int, Int>> =
        listOf(7 to 1, 0 to 3, 7 to 4, 0 to 6, 7 to 5)
    override fun print(text: String) {
        val words = text.split(Regex("\\s+")).filter { it.isNotEmpty() }
        for ((i, w) in words.withIndex()) {
            val (fg, bg) = palette[i % palette.size]
            println(AnsiP.style(w, fg = fg, bg = bg))
        }
    }
}

fun demoPrinters() {
    val laser = LaserPrinter()
    val ink = InkjetPrinter()
    val text = "Kotlin is fun and powerful for JVM and beyond"
    println("-- Laser --"); laser.print(text)
    println("-- Inkjet --"); ink.print(text)
}

// Точка входа этого файла
fun main() {
    demoPrinters()
}