package org.example.lessons.lesson15.homework

open class ГеометрическаяФигура(
    open val цвет: String,
    open val название: String
) {
    open fun info() = println("Фигура: $название, цвет: $цвет")
}

open class Многоугольник(
    override val цвет: String,
    override val название: String,
    val количествоСторон: Int
) : ГеометрическаяФигура(цвет, название)

class Треугольник(
    цвет: String,
    val стороны: Triple<Double, Double, Double>
) : Многоугольник(цвет, "Треугольник", 3)

class Четырехугольник(
    цвет: String,
    val стороны: List<Double> // 4 длины
) : Многоугольник(цвет, "Четырехугольник", 4)

class Круг(
    цвет: String,
    val радиус: Double
) : ГеометрическаяФигура(цвет, "Круг")