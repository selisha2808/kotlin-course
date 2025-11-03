package org.example.lessons.lesson16.homework

abstract class Shape { open fun area(): Double = 0.0 }

class Circle(private val radius: Double) : Shape() {
    override fun area(): Double = Math.PI * radius * radius
}
class Square(private val side: Double) : Shape() {
    override fun area(): Double = side * side
}
class Triangle(
    private val a: Double,
    private val b: Double,
    private val angleDegrees: Double
) : Shape() {
    override fun area(): Double {
        val r = Math.toRadians(angleDegrees)
        return 0.5 * a * b * kotlin.math.sin(r)
    }
}

fun demoShapes() {
    val shapes: List<Shape> = listOf(
        Circle(3.0),
        Square(5.0),
        Triangle(6.0, 4.0, 30.0)
    )
    shapes.forEach { println("area = ${"%.3f".format(it.area())}") }
}

// Точка входа этого файла
fun main() {
    demoShapes()
}