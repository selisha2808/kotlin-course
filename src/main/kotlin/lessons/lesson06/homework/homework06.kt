package org.example.lessons.lesson06.homework

fun main() {
    // Примеры вызовов для проверки
    printSeason(3)
    dogToHumanAge(5)
    recommendTransport(7.0)
    calculateBonusPoints(1350.0)
    detectFileType("report.docx")
    convertTemperature(100.0, "C")
    clothingAdvice(-35)
    println(getMovieCategory(12))
}

// ===== Задание 1: Определение сезона =====
fun printSeason(month: Int) {
    if (month !in 1..12) {
        println("Ошибка: номер месяца должен быть от 1 до 12")
        return
    }
    val season = when (month) {
        12, 1, 2 -> "Зима"
        3, 4, 5 -> "Весна"
        6, 7, 8 -> "Лето"
        9, 10, 11 -> "Осень"
        else -> "Неизвестный сезон"
    }
    println("Сезон: $season")
}

// ===== Задание 2: Расчет возраста питомца =====
fun dogToHumanAge(dogAge: Int) {
    if (dogAge <= 0) {
        println("Ошибка: возраст собаки должен быть положительным")
        return
    }
    val humanAge = if (dogAge <= 2) {
        dogAge * 10.5
    } else {
        2 * 10.5 + (dogAge - 2) * 4
    }
    println("Возраст собаки $dogAge лет = $humanAge человеческих лет")
}

// ===== Задание 3: Определение способа перемещения =====
fun recommendTransport(distanceKm: Double) {
    if (distanceKm <= 0) {
        println("Ошибка: расстояние должно быть положительным")
        return
    }
    val transport = when {
        distanceKm <= 1 -> "пешком"
        distanceKm <= 5 -> "велосипед"
        else -> "автотранспорт"
    }
    println("Рекомендуемый способ передвижения: $transport")
}

// ===== Задание 4: Расчет бонусных баллов =====
fun calculateBonusPoints(purchaseAmount: Double) {
    if (purchaseAmount <= 0) {
        println("Ошибка: сумма покупки должна быть положительной")
        return
    }
    val pointsPer100 = if (purchaseAmount <= 1000) 2 else 3
    val points = (purchaseAmount / 100).toInt() * pointsPer100
    println("Сумма покупки $purchaseAmount руб. = $points бонусных баллов")
}

// ===== Задание 5: Определение типа документа =====
fun detectFileType(fileName: String) {
    val extension = fileName.substringAfterLast('.', "").lowercase()
    val fileType = when (extension) {
        "txt", "doc", "docx" -> "Текстовый документ"
        "jpg", "jpeg", "png", "gif" -> "Изображение"
        "xls", "xlsx", "csv" -> "Таблица"
        else -> "Неизвестный тип"
    }
    println("Файл $fileName: $fileType")
}

// ===== Задание 6: Конвертация температуры =====
fun convertTemperature(value: Double, unit: String) {
    when (unit.uppercase()) {
        "C" -> {
            val f = value * 9 / 5 + 32
            print("$f"); println("F")
        }
        "F" -> {
            val c = (value - 32) * 5 / 9
            print("$c"); println("C")
        }
        else -> println("Ошибка: допустимые единицы измерения C или F")
    }
}

// ===== Задание 7: Подбор одежды по погоде =====
fun clothingAdvice(temperature: Int) {
    val advice = when {
        temperature < -30 || temperature > 35 -> "Рекомендация: не выходить из дома"
        temperature < 10 -> "Куртка и шапка"
        temperature in 10..18 -> "Ветровка"
        else -> "Футболка и шорты"
    }
    println("Температура $temperature° → $advice")
}

// ===== Задание 8: Выбор фильма по возрасту =====
fun getMovieCategory(age: Int): String {
    if (age < 0) return "Ошибка: возраст не может быть отрицательным"
    return when (age) {
        in 0..9 -> "Детские фильмы"
        in 10..18 -> "Подростковые фильмы"
        else -> "Фильмы 18+"
    }
}