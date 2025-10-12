package org.example.lessons.lesson10.homework

fun main() {
    println("=== Блок 1: задачи на работу со словарём ===")

    // 1) Пустой неизменяемый словарь Int->Int
    val m1: Map<Int, Int> = emptyMap()
    println("1) $m1")

    // 2) Словарь Float->Double, инициализированный парами
    val m2: Map<Float, Double> = mapOf(
        1.5f to 2.0,
        3.14f to 6.28,
        2.0f to 4.5
    )
    println("2) $m2")

    // 3) Изменяемый словарь Int->String
    val m3: MutableMap<Int, String> = mutableMapOf()
    println("3) start: $m3")

    // 4) Добавить новые пары
    m3[1] = "one"
    m3[2] = "two"
    m3.putAll(mapOf(3 to "three"))
    println("4) after put: $m3")

    // 5) Извлечь значение по ключу, в т.ч. несуществующему
    val v2 = m3[2]
    val v99 = m3[99] // null, такого ключа нет
    println("5) get(2)=$v2, get(99)=${v99 ?: "null"}")

    // 6) Удалить элемент по ключу
    m3.remove(2)
    println("6) after remove(2): $m3")

    // 7) Map<Double, Int>: вывести key/value или «бесконечность» при делении на 0
    val m7: Map<Double, Int> = mapOf(
        10.0 to 2,
        5.0 to 0,
        7.5 to 3
    )
    print("7) ")
    for ((k, v) in m7) {
        if (v == 0) {
            print("бесконечность ")
        } else {
            val res = k / v
            print("$res ")
        }
    }
    println()

    // 8) Изменить значение для существующего ключа
    val m8 = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    m8[2] = "TWO"
    println("8) $m8")

    // 9) Объединить два словаря в третий через циклы
    val left = mapOf(1 to "a", 2 to "b")
    val right = mapOf(2 to "B2", 3 to "c")
    val merged: MutableMap<Int, String> = mutableMapOf()
    for ((k, v) in left) merged[k] = v
    for ((k, v) in right) merged[k] = v // при совпадении ключей правый перезапишет
    println("9) merged=$merged")

    // 10) Map<String, List<Int>>: добавить несколько элементов
    val m10: MutableMap<String, List<Int>> = mutableMapOf()
    m10["odd"] = listOf(1, 3, 5)
    m10["even"] = listOf(2, 4, 6, 8)
    println("10) $m10")

    // 11) Map<Int, MutableSet<String>>: добавить данные, получить множество и добавить ещё строку
    val m11: MutableMap<Int, MutableSet<String>> = mutableMapOf(
        1 to mutableSetOf("alpha", "beta"),
        2 to mutableSetOf("gamma")
    )
    val setForKey1 = m11.getOrPut(1) { mutableSetOf() }
    setForKey1.add("delta")
    println("11) key=1 -> $setForKey1")

    // 12) Map<Pair<Int,Int>, String>: найти значение, где пара содержит 5 в первом или втором
    val m12: Map<Pair<Int, Int>, String> = mapOf(
        (1 to 2) to "a",
        (5 to 7) to "b",
        (9 to 5) to "c",
        (3 to 4) to "d"
    )
    val foundValues = mutableListOf<String>()
    for ((pair, value) in m12) {
        if (pair.first == 5 || pair.second == 5) {
            foundValues.add(value)
        }
    }
    println("12) значения с пятёркой в паре: $foundValues")

    println("\n=== Блок 2: подбор оптимального типа для словаря ===")
    // Ниже — рекомендуемые типы (просто печатаем как подсказку):
    println("1) Библиотека: Map<String, List<String>>  // автор -> список книг")
    println("2) Растения: Map<String, List<String>>     // тип ('Цветы') -> список названий")
    println("3) Команды: Map<String, List<String>>      // команда -> список игроков")
    println("4) Курс лечения: Map<java.time.LocalDate, List<String>> // дата -> список препаратов")
    println("5) Путешественник: Map<String, Map<String, List<String>>> // страна -> (город -> места)")
}