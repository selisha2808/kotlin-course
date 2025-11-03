package org.example.lessons.lesson16.homework

private object Ansi {
    const val RESET = "\u001B[0m"
    private fun fg(code: Int) = "\u001B[3${code}m"
    fun color(text: String, code: Int) = fg(code) + text + RESET
}

open class Animal { open fun makeSound() = println("This animal makes no sound.") }
class Dog : Animal() { override fun makeSound() = println(Ansi.color("Bark", 2)) }
class Cat : Animal() { override fun makeSound() = println(Ansi.color("Meow", 5)) }
class Bird : Animal() { override fun makeSound() = println(Ansi.color("Tweet", 6)) }

// Быстрая проверка (вызывать по желанию)
fun demoAnimals() {
    val zoo: List<Animal> = listOf(Animal(), Dog(), Cat(), Bird())
    zoo.forEach { it.makeSound() }
}

// Точка входа для запуска ОТСЮДА
fun main() {
    demoAnimals()
}