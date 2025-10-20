package org.example.lessons.lesson12.homework
import java.util.Locale

/* ===================== БЛОК 1. Приведение коллекции к значению (все методы уникальны) ===================== */

fun sizeMoreThan5(xs: Collection<*>): Boolean = xs.size > 5                 // size
fun isEmptyCol(xs: Collection<*>): Boolean = xs.isEmpty()                    // isEmpty
fun isNotEmptyCol(xs: Collection<*>): Boolean = xs.isNotEmpty()              // isNotEmpty
fun <T> getOrMake(xs: List<T>, index: Int, default: () -> T): T =            // getOrElse
    xs.getOrElse(index) { default() }
fun <T> joinIt(xs: Collection<T>, sep: String = ","): String =               // joinToString
    xs.joinToString(sep)
fun sumAll(xs: Collection<Int>): Int = xs.sum()                              // sum
fun avgAll(xs: Collection<Int>): Double = xs.average()                       // average
fun maxNum(xs: Collection<Int>): Int? = xs.maxOrNull()                       // maxOrNull
fun minNum(xs: Collection<Int>): Int? = xs.minOrNull()                       // minOrNull
fun firstOrNullNum(xs: List<Int>): Int? = xs.firstOrNull()                   // firstOrNull
fun <T> hasElement(xs: Collection<T>, v: T): Boolean = xs.contains(v)        // contains

/* ===================== БЛОК 2. Обработка коллекций (каждая задача — свой метод, без повторов) ===================== */

// 1) Отфильтровать 18..30
fun filterRange18to30(xs: List<Int>) = xs.filter { it in 18..30 }                         // filter

// 2) Числа, НЕ делящиеся одновременно на 2 и 3
fun notDivisibleBy2And3Together(xs: List<Int>) = xs.filterNot { it % 2 == 0 && it % 3 == 0 } // filterNot

// 3) Удалить null из текстовой коллекции
fun dropNulls(xs: List<String?>): List<String> = xs.filterNotNull()                       // filterNotNull

// 4) В длины слов
fun toWordLengths(xs: List<String>) = xs.map { it.length }                                // map

// 5) В мапу: ключ — перевёрнутое слово, значение — длина
fun toReversedKeyMap(xs: List<String>) =                                                  // associate
    xs.associate { it.reversed() to it.length }

// 6) Отсортировать по алфавиту
fun sortAlpha(xs: List<String>) = xs.sorted()                                             // sorted

// 7) Первые 3
fun first3(xs: List<Int>) = xs.take(3)                                                    // take

// 8) Распечатать квадраты
fun printSquares(xs: List<Int>) = xs.forEach { println(it * it) }                         // forEach

// 9) Группировать по первой букве
fun groupByFirstLetter(xs: List<String>) = xs.groupBy { it.first() }                      // groupBy

// 10) Очистить от дублей
fun removeDuplicates(xs: List<Int>) = xs.distinct()                                       // distinct

// 11) Сортировка по убыванию
fun sortDesc(xs: List<Int>) = xs.sortedDescending()                                       // sortedDescending

// 12) Последние 3
fun last3(xs: List<Int>) = xs.takeLast(3)                                                 // takeLast

/* ===================== Задача 24. Характеристика числовой коллекции ===================== */

fun characterize(nums: List<Int>): String = when {
    nums.isEmpty() -> "Пусто"
    nums.size < 5 -> "Короткая"
    nums.firstOrNull() == 0 -> "Стартовая"
    nums.sum() > 10_000 -> "Массивная"
    nums.isNotEmpty() && nums.average() == 10.0 -> "Сбалансированная"
    nums.joinToString("").length == 20 -> "Клейкая"
    nums.maxOrNull()?.let { it < -10 } == true -> "Отрицательная"
    nums.minOrNull()?.let { it > 1000 } == true -> "Положительная"
    nums.contains(3) && nums.contains(14) -> "Пи***тая"
    else -> "Уникальная"
}

/* ===================== Задача 25. Анализ учебных оценок ===================== */
// Фильтруем >=60, сортируем по возрастанию, берём первые 3 (цепочкой)
fun analyzeGrades(grades: List<Int>): List<Int> =
    grades.filter { it >= 60 }.sorted().take(3)

/* ===================== Задача 26. Каталог по первой букве ===================== */
// Привести к нижнему регистру и сгруппировать по первой букве (groupBy)
fun catalogByFirstLetter(items: List<String>): Map<Char, List<String>> =
    items.map { it.lowercase() }.groupBy { it.first() }

/* ===================== Задание 27. Средняя длина слов (формат с 2 знаками) ===================== */
fun averageWordLengthText(items: List<String>): String {
    val avg = items.map { it.length }.average()
    return String.format(Locale("ru", "RU"), "%.2f", avg)
}

/* ===================== Задание 28. Категоризация чисел ===================== */
// Уникальные -> по убыванию -> группировка по четности в ключи "четные"/"нечетные"
fun categorizeNumbers(numbers: List<Int>): Map<String, List<Int>> =
    numbers.distinct()
        .sortedDescending()
        .groupBy { if (it % 2 == 0) "четные" else "нечетные" }

/* ===================== Задание 29. Поиск первого подходящего элемента ===================== */
// ages могут содержать null; ищем первый > threshold, иначе null
fun findFirstGreaterThan(ages: List<Int?>, threshold: Int): Int? =
    ages.filterNotNull().firstOrNull { it > threshold }

/* ===================== ДЕМО ===================== */
fun main() {
    // Блок 1 демонстрация
    println("sizeMoreThan5: " + sizeMoreThan5(listOf(1,2,3,4,5,6)))
    println("isEmptyCol: " + isEmptyCol(emptyList<Int>()))
    println("isNotEmptyCol: " + isNotEmptyCol(listOf(1)))
    println("getOrMake: " + getOrMake(listOf(10,20), 5) { 999 })
    println("joinIt: " + joinIt(listOf("a","b","c"), "-"))
    println("sumAll: " + sumAll(listOf(1,2,3)))
    println("avgAll: " + avgAll(listOf(5,5,10)))
    println("maxNum: " + maxNum(listOf(1,9,3)))
    println("minNum: " + minNum(listOf(1,9,3)))
    println("firstOrNullNum: " + firstOrNullNum(listOf()))
    println("hasElement: " + hasElement(listOf(1,2,3), 2))

    // Блок 2 демонстрация
    println("filterRange18to30: " + filterRange18to30(listOf(12,18,19,30,31)))
    println("notDivisibleBy2And3Together: " + notDivisibleBy2And3Together(listOf(6,12,7,8,9,10,18)))
    println("dropNulls: " + dropNulls(listOf("a", null, "b")))
    println("toWordLengths: " + toWordLengths(listOf("kot","lin")))
    println("toReversedKeyMap: " + toReversedKeyMap(listOf("kotlin","java")))
    println("sortAlpha: " + sortAlpha(listOf("banana","apple","cherry")))
    println("first3: " + first3(listOf(1,2,3,4,5)))
    println("printSquares:")
    printSquares(listOf(2,3,4))
    println("groupByFirstLetter: " + groupByFirstLetter(listOf("apple","art","bee","boat")))
    println("removeDuplicates: " + removeDuplicates(listOf(1,1,2,2,3,3)))
    println("sortDesc: " + sortDesc(listOf(3,1,4,2)))
    println("last3: " + last3(listOf(1,2,3,4,5)))

    // Задача 24 — тесты веток
    println("24) " + characterize(emptyList()))                       // Пусто
    println("24) " + characterize(listOf(1,2,3,4)))                    // Короткая
    println("24) " + characterize(listOf(0, 9, 9)))                    // Стартовая
    println("24) " + characterize(listOf(10_001)))                     // Массивная
    println("24) " + characterize(List(10) { 10 }))                    // Сбалансированная
    println("24) " + characterize(listOf(9,1,0,1,9)))                  // Клейкая (например "91019" длина 5 — подберите свой набор до 20)
    println("24) " + characterize(listOf(-11, -12, -13)))              // Отрицательная
    println("24) " + characterize(listOf(1001, 2000)))                 // Положительная
    println("24) " + characterize(listOf(3, 14, 1)))                   // Пи***тая
    println("24) " + characterize(listOf(42, 7, 8)))                   // Уникальная

    // Задача 25
    val grades = listOf(85, 58, 90, 74, 88, 67, 95, 92, 50, 42, 12)
    println("25) " + analyzeGrades(grades)) // [67, 74, 85]

    // Задача 26
    val list = listOf(
        "Стол", "табурет", "ваза", "Кружка", "Зеркало", "ковер", "Шкаф", "часы",
        "Люстра", "подушка", "Картина", "столик", "Вазон", "шторы", "Пуф", "книга",
        "Фоторамка", "светильник", "Коврик", "вешалка", "Подставка", "телевизор",
        "Комод", "полка", "Абажур", "диван", "Кресло", "занавеска", "Бра",
        "пепельница", "Глобус", "статуэтка", "Поднос", "фигурка", "Ключница",
        "плед", "Тумба", "игрушка", "Настенные часы", "подсвечник",
        "Журнальный столик", "сувенир", "Корзина для белья", "посуда",
        "Настольная лампа", "торшер", "Этажерка"
    )
    val catalog = catalogByFirstLetter(list)
    println("26) разделы: " + catalog.keys.sorted())

    // Задание 27
    println("27) средняя длина: " + averageWordLengthText(list.map { it.lowercase() }))

    // Задание 28
    val numbers = listOf(1, 3, 5, 7, 3, 1, 8, 9, 9, 7)
    println("28) " + categorizeNumbers(numbers))

    // Задание 29
    val ages = listOf(22, 18, 30, 45, 17, null, 60)
    println("29) " + findFirstGreaterThan(ages, 18)) // ожидаемо 22 или 30 (первое > 18)
}