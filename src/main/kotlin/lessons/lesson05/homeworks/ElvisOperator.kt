package org.example.lessons.lesson05.homeworks

fun main() {
    // ===== Задача 1 =====
    // Интенсивность звука = начальная интенсивность * коэффициент затухания (или 0.5, если null)
    val initialIntensity: Double = 100.0
    val attenuation: Double? = null   // может быть null
    val resultIntensity = initialIntensity * (attenuation ?: 0.5)
    println("Задача 1: Интенсивность звука после затухания = $resultIntensity")

    // ===== Задача 2 =====
    // Полная стоимость доставки = стоимость + страховка (0.5% от стоимости)
    val cargoCost: Double? = null
    val realCost = cargoCost ?: 50.0
    val deliveryPrice = realCost + realCost * 0.005
    println("Задача 2: Полная стоимость доставки = $deliveryPrice")

    // ===== Задача 3 =====
    // Проверка показаний атмосферного давления
    val pressure: Int? = null
    val pressureValue = pressure ?: error("Задача 3: Ошибка! Нет показаний давления")
    println("Задача 3: Атмосферное давление = $pressureValue мм рт. ст.")
}