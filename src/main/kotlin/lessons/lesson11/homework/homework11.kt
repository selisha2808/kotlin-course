package org.example.lessons.lesson11.homework

// ---------- Блок 1: сигнатуры методов ----------

// 1) Не принимает аргументов и не возвращает значения.
fun doNothing(): Unit { /* ничего */ }

// 2) Принимает два Int и возвращает их сумму.
fun sum(a: Int, b: Int): Int = a + b

// 3) Принимает строку и ничего не возвращает.
fun consumeString(s: String): Unit { /* обработка s */ }

// 4) Принимает список Int и возвращает среднее Double.
fun average(nums: List<Int>): Double =
    if (nums.isEmpty()) 0.0 else nums.average()

// 5) Принимает nullable строку и возвращает её длину в виде nullable Int;
//    доступна только в текущем файле.
private fun lengthOrNull(s: String?): Int? = s?.length

// 6) Не принимает аргументов и возвращает nullable Double.
fun maybeDouble(): Double? = null

// 7) Принимает nullable список Int, не возвращает значения;
//    доступна только в текущем файле.
private fun handleNullableList(lst: List<Int>?): Unit { /* ... */ }

// 8) Принимает Int и возвращает nullable String.
fun intToStringOrNull(n: Int): String? = n.toString()

// 9) Не принимает аргументов и возвращает список nullable строк.
fun getNullableStrings(): List<String?> = listOf("hello", null, "kotlin")

// 10) Принимает nullable String и nullable Int и возвращает nullable Boolean.
fun compareLength(s: String?, n: Int?): Boolean? =
    if (s != null && n != null) s.length == n else null



// ---------- Блок 2: рабочий код ----------

// multiplyByTwo: принимает Int и возвращает его * 2
fun multiplyByTwo(x: Int): Int = x * 2

// isEven: true если число чётное
fun isEven(x: Int): Boolean = x % 2 == 0

// printNumbersUntil: печатает 1..n; если n < 1 — просто return
fun printNumbersUntil(n: Int) {
    if (n < 1) return
    for (i in 1..n) println(i)
}

// findFirstNegative: первое отрицательное из списка или null
fun findFirstNegative(items: List<Int>): Int? {
    for (x in items) if (x < 0) return x
    return null
}

// processList: печатает строки; при null в списке — прекращает выполнение
fun processList(items: List<String?>) {
    for (s in items) {
        if (s == null) return
        println(s)
    }
}



// ---------- Мини-демо ----------
fun main() {
    println("sum(2,3) = " + sum(2, 3))
    println("average(listOf(1,2,3)) = " + average(listOf(1,2,3)))
    println("lengthOrNull(null) = " + lengthOrNull(null))
    println("maybeDouble() = " + maybeDouble())
    println("intToStringOrNull(42) = " + intToStringOrNull(42))
    println("getNullableStrings() = " + getNullableStrings())
    println("compareLength(\"abc\", 3) = " + compareLength("abc", 3))

    println("multiplyByTwo(7) = " + multiplyByTwo(7))
    println("isEven(10) = " + isEven(10))

    println("printNumbersUntil(3):")
    printNumbersUntil(3)

    println("findFirstNegative(listOf(5, 0, -2, 7)) = " + findFirstNegative(listOf(5, 0, -2, 7)))

    println("processList(listOf(\"a\", \"b\", null, \"c\")) -> печатает до null:")
    processList(listOf("a", "b", null, "c"))
}