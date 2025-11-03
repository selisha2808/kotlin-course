package org.example.lessons.lesson16.homework

enum class Level { INFO, WARNING, ERROR, DEBUG }

// file-private ANSI для логгера
private object AnsiL {
    const val RESET = "\u001B[0m"
    private fun fg(code: Int) = "\u001B[3${code}m"
    private fun bg(code: Int) = "\u001B[4${code}m"
    fun style(text: String, fg: Int? = null, bg: Int? = null): String =
        (fg?.let { fg(it) } ?: "") + (bg?.let { bg(it) } ?: "") + text + RESET
}

class Logger {
    fun log(message: String) = log(Level.INFO, message)
    fun log(level: Level, message: String) {
        val out = when (level) {
            Level.INFO    -> message
            Level.DEBUG   -> AnsiL.style(message, fg = 6)
            Level.WARNING -> AnsiL.style(message, fg = 3)
            Level.ERROR   -> AnsiL.style(message, fg = 7, bg = 1)
        }
        println("[${level.name}] $out")
    }
    fun log(messages: List<String>) = messages.forEach { log(it) }
    fun log(ex: Exception) = log(Level.ERROR, ex.message ?: ex.toString())
}

fun demoLogger() {
    val log = Logger()
    log.log("System started")
    log.log(Level.WARNING, "Low disk space")
    log.log(Level.DEBUG, "Query took 12ms")
    log.log(Exception("Connection refused"))
    log.log(listOf("User logged in", "User clicked btn:save"))
}

// Точка входа этого файла
fun main() {
    demoLogger()
}