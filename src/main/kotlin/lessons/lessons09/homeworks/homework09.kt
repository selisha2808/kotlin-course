package org.example.lessons.lessons09.homeworks

// ---------- ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ ----------

// Поиск строки-подстроки в массиве строк через цикл (contains)
fun findBySubstring(arr: Array<String>, needle: String): String? {
    for (s in arr) {
        if (s.contains(needle)) return s
    }
    return null
}

// Проверка наличия строки в множестве через цикл
fun setContainsByLoop(set: Set<String>, target: String): Boolean {
    for (s in set) {
        if (s == target) return true
    }
    return false
}

// Конвертация множества строк в изменяемый список через цикл
fun setToMutableListByLoop(set: Set<String>): MutableList<String> {
    val result = mutableListOf<String>()
    for (s in set) result.add(s)
    return result
}

// ---------- MAIN DEMO ----------
fun main() {
    println("=== Работа с массивами Array ===")

    // 1) Массив из 5 целых, значения 1..5
    val arr1 = arrayOf(1, 2, 3, 4, 5)
    println("1) " + arr1.joinToString())

    // 2) Пустой массив строк размером 10 элементов
    val arr2 = arrayOfNulls<String>(10) // элементы = null
    println("2) size=${arr2.size}, contents=" + arr2.joinToString())

    // 3) Массив из 5 Double: значение = удвоенный индекс
    val arr3 = DoubleArray(5) { i -> (i * 2).toDouble() }
    println("3) " + arr3.joinToString())

    // 4) Массив из 5 Int: элемент = индекс * 3 (через цикл)
    val arr4 = IntArray(5)
    for (i in arr4.indices) {
        arr4[i] = i * 3
    }
    println("4) " + arr4.joinToString())

    // 5) Массив из 3 nullable строк: один null и две строки
    val arr5 = arrayOf<String?>(null, "hello", "world")
    println("5) " + arr5.joinToString())

    // 6) Скопировать массив целых чисел в новый массив в цикле
    val source = intArrayOf(10, 20, 30, 40)
    val copy = IntArray(source.size)
    for (i in source.indices) {
        copy[i] = source[i]
    }
    println("6) source=" + source.joinToString() + " | copy=" + copy.joinToString())

    // 7) Разность двух массивов одинаковой длины -> третий массив, распечатать
    val a = intArrayOf(5, 9, 2, 7)
    val b = intArrayOf(1, 3, 8, 4)
    val c = IntArray(a.size)
    for (i in a.indices) c[i] = a[i] - b[i]
    println("7) a=" + a.joinToString() + ", b=" + b.joinToString() + " -> a-b=" + c.joinToString())

    // 8) Найти индекс элемента со значением 5 через while (иначе -1)
    val arr8 = intArrayOf(2, 4, 6, 8)
    var idx = 0
    var foundIndex = -1
    while (idx < arr8.size) {
        if (arr8[idx] == 5) { foundIndex = idx; break }
        idx++
    }
    println("8) В arr8=" + arr8.joinToString() + " индекс значения 5 = $foundIndex")

    // 9) Вывести каждый элемент и написать чётное/нечётное
    val arr9 = intArrayOf(1, 2, 3, 4, 5, 6)
    print("9) ")
    for (x in arr9) {
        val parity = if (x % 2 == 0) "чётное" else "нечётное"
        print("$x($parity) ")
    }
    println()

    // 10) Функция поиска подстроки в массиве строк (contains)
    val arr10 = arrayOf("alpha", "beta", "kotlin", "world")
    val needle = "lin"
    val found = findBySubstring(arr10, needle)
    println("10) Ищем \"$needle\" в " + arr10.joinToString() + " -> найдено: ${found ?: "ничего"}")

    println("\n=== Работа со списками List ===")

    // 1) Пустой неизменяемый список целых чисел
    val list1: List<Int> = emptyList()
    println("1) $list1")

    // 2) Неизменяемый список строк из трёх элементов
    val list2 = listOf("Hello", "World", "Kotlin")
    println("2) $list2")

    // 3) Изменяемый список Int со значениями 1..5
    val mlist3 = mutableListOf(1,2,3,4,5)
    println("3) $mlist3")

    // 4) Добавить 6,7,8 в изменяемый список
    mlist3.add(6); mlist3.add(7); mlist3.add(8)
    println("4) $mlist3")

    // 5) Удалить элемент "World" из изменяемого списка строк
    val mlist5 = mutableListOf("Hello", "World", "Kotlin")
    mlist5.remove("World")
    println("5) $mlist5")

    // 6) Вывести элементы списка целых через цикл
    val list6 = listOf(10, 20, 30)
    print("6) ")
    for (x in list6) print("$x ")
    println()

    // 7) Получить второй элемент списка строк по индексу
    val list7 = listOf("a", "b", "c")
    val second = list7[1]
    println("7) второй элемент: $second")

    // 8) Изменить элемент с индексом 2 в изменяемом списке
    val mlist8 = mutableListOf(100, 200, 300, 400)
    mlist8[2] = 999
    println("8) $mlist8")

    // 9) Объединить два списка строк в новый через циклы
    val listA = listOf("red", "green")
    val listB = listOf("blue", "yellow")
    val merged = mutableListOf<String>()
    for (s in listA) merged.add(s)
    for (s in listB) merged.add(s)
    println("9) merged=$merged")

    // 10) Найти мин/макс в списке целых через цикл
    val list10 = listOf(5, -2, 17, 0, 9)
    var min = list10[0]
    var max = list10[0]
    for (x in list10) {
        if (x < min) min = x
        if (x > max) max = x
    }
    println("10) min=$min, max=$max")

    // 11) Новый список только из чётных чисел (через цикл)
    val list11 = listOf(1,2,3,4,5,6,7,8)
    val evens = mutableListOf<Int>()
    for (x in list11) if (x % 2 == 0) evens.add(x)
    println("11) чётные: $evens")

    println("\n=== Работа с множествами Set ===")

    // 1) Пустое неизменяемое множество Int
    val set1: Set<Int> = emptySet()
    println("1) $set1")

    // 2) Неизменяемое множество Int из трёх элементов
    val set2 = setOf(1, 2, 3)
    println("2) $set2")

    // 3) Изменяемое множество строк с начальными значениями
    val mset3 = mutableSetOf("Kotlin", "Java", "Scala")
    println("3) $mset3")

    // 4) Добавить "Swift", "Go" в изменяемое множество строк
    mset3.add("Swift"); mset3.add("Go")
    println("4) $mset3")

    // 5) Удалить 2 из изменяемого множества Int
    val mset5 = mutableSetOf(1, 2, 3, 4)
    mset5.remove(2)
    println("5) $mset5")

    // 6) Вывести элементы множества Int через цикл
    val set6 = setOf(10, 20, 30)
    print("6) ")
    for (x in set6) print("$x ")
    println()

    // 7) Функция проверки наличия строки в множестве (через цикл)
    val sset = setOf("alpha", "beta", "gamma")
    println("7) contains 'beta'? " + setContainsByLoop(sset, "beta"))
    println("   contains 'delta'? " + setContainsByLoop(sset, "delta"))

    // 8) Конвертация множества строк в изменяемый список через цикл
    val set8 = setOf("x", "y", "z")
    val mlistFromSet = setToMutableListByLoop(set8)
    println("8) $mlistFromSet (type=${mlistFromSet::class.simpleName})")
}