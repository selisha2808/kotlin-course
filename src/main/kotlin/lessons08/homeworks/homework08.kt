package org.example.lessons08.homeworks

// 1) Преобразование строк
fun transformPhrase(inputRaw: String): String {
    var s = inputRaw.trim()

    // Правило: если фраза состоит из одного слова
    val words = s.split(Regex("\\s+")).filter { it.isNotEmpty() }
    if (words.size == 1) {
        return "Иногда, $s, но не всегда"
    }

    // Правило: если начинается с "Я не уверен"
    if (s.startsWith("Я не уверен", ignoreCase = true)) {
        s += ", но моя интуиция говорит об обратном"
    }

    // Правило: содержит "невозможно"
    s = Regex("невозможно", RegexOption.IGNORE_CASE)
        .replace(s, "совершенно точно возможно, просто требует времени")

    // Правило: содержит "катастрофа"
    s = Regex("катастрофа", RegexOption.IGNORE_CASE)
        .replace(s, "интересное событие")

    // Правило: заканчивается на "без проблем"
    s = Regex("\\s*без проблем\\s*$", RegexOption.IGNORE_CASE)
        .replace(s) { " с парой интересных вызовов на пути" }

    return s
}

// 2) Извлечение даты и времени из строки лога
fun printDateTimeFromLog(logLine: String) {
    // Формат: "<что-то> -> YYYY-MM-DD HH:MM:SS"
    val arrow = logLine.indexOf("->")
    val right = if (arrow >= 0) logLine.substring(arrow + 2).trim() else logLine
    val parts = right.split(Regex("\\s+"))
    val date = parts.getOrNull(0).orEmpty()
    val time = parts.getOrNull(1).orEmpty()
    println(date)
    println(time)
}

// 3) Маскирование номера карты (сохраняем пробелы/разделители)
fun maskCardNumber(card: String): String {
    // Оставить видимыми только последние 4 цифры
    val digits = card.count { it.isDigit() }
    var keepFrom = digits - 4
    val sb = StringBuilder(card.length)
    for (ch in card) {
        if (ch.isDigit()) {
            if (keepFrom > 0) {
                sb.append('*')
                keepFrom--
            } else {
                sb.append(ch)
            }
        } else {
            sb.append(ch)
        }
    }
    return sb.toString()
}

// 4) Форматирование email через replace()
fun prettyEmail(email: String): String =
    email.replace("@", " [at] ")
        .replace(".", " [dot] ")

// 5) Извлечение имени файла из пути (поддержка / и \)
fun fileName(path: String): String =
    path.trim().split(Regex("[/\\\\]+")).lastOrNull().orEmpty()

// 6) Аббревиатура из фразы
fun makeAcronym(phrase: String): String {
    val parts = phrase.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
    var acc = ""
    for (w in parts) {
        acc += w.first().uppercase()
    }
    return acc
}

fun main() {
    // 1) Примеры
    val phrases = listOf(
        "Это невозможно выполнить за один день",
        "Я не уверен в успехе этого проекта",
        "Произошла катастрофа на сервере",
        "Этот код работает без проблем",
        "Удача"
    )
    println("=== 1) Преобразование строк ===")
    phrases.forEach { println("• $it -> ${transformPhrase(it)}") }

    println("\n=== 2) Дата/время из лога ===")
    printDateTimeFromLog("Пользователь вошел в систему -> 2021-12-01 09:48:23")

    println("\n=== 3) Маскирование карты ===")
    println(maskCardNumber("4539 1488 0343 6467"))

    println("\n=== 4) Форматирование email ===")
    println(prettyEmail("username@example.com"))

    println("\n=== 5) Имя файла из пути ===")
    println(fileName("C:/Пользователи/Документы/report.txt"))
    println(fileName("D:/good.themes/dracula.theme"))

    println("\n=== 6) Аббревиатура ===")
    println(makeAcronym("Котлин лучший язык программирования"))
}