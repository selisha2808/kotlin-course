package org.example.lessons.lesson15.homework

open class УчебноеЗаведение(
    open val название: String,
    open val город: String
) {
    open fun описание() = println("$название, $город")
}

class Школа(
    override val название: String,
    override val город: String,
    val классов: Int
) : УчебноеЗаведение(название, город)

open class ВУЗ(
    override val название: String,
    override val город: String,
    val аккредитация: String
) : УчебноеЗаведение(название, город)

class Университет(
    название: String,
    город: String,
    аккредитация: String,
    val факультеты: Int
) : ВУЗ(название, город, аккредитация)

class Колледж(
    название: String,
    город: String,
    аккредитация: String,
    val программы: Int
) : ВУЗ(название, город, аккредитация)