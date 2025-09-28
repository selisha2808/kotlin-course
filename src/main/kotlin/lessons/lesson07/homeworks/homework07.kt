package org.example.lessons.lesson07.homeworks

fun main() {
    println("=== Прямой диапазон ===")
    directRange_1to5()
    even_1to10()

    println("\n=== Обратный диапазон ===")
    reverse_5to1()
    reverse_10to1_step2()

    println("\n=== С шагом (step) ===")
    step_1to9_step2()
    everyThird_1to20()

    println("\n=== Использование until ===")
    until_withSize(size = 12)

    println("\n=== Цикл while ===")
    while_squares_1to5()
    while_decrease_10to5()

    println("\n=== Цикл do..while ===")
    doWhile_5to1()
    doWhile_from5_until10()

    println("\n=== Прерывание и пропуск итерации ===")
    println("-- break в for --")
    break_in_for_1to10_stopAt6()

    println("-- бесконечный while с break --")
    infiniteWhile_breakAt10()

    println("-- continue в for (пропуск чётных) --")
    continue_in_for_skipEven()

    println("-- while: пропуск кратных 3 --")
    while_1to10_skipMultiplesOf3()
}

/* ==================== Прямой диапазон ==================== */

// 1) for: вывод чисел от 1 до 5
fun directRange_1to5() {
    for (i in 1..5) {
        print("$i ")
    }
    println()
}

// 2) for: вывод чётных чисел от 1 до 10
fun even_1to10() {
    for (i in 2..10 step 2) {
        print("$i ")
    }
    println()
}

/* ==================== Обратный диапазон ==================== */

// 3) for: вывод чисел от 5 до 1
fun reverse_5to1() {
    for (i in 5 downTo 1) {
        print("$i ")
    }
    println()
}

// 4) for: вывод чисел от 10 до 1, уменьшая на 2
fun reverse_10to1_step2() {
    for (i in 10 downTo 1 step 2) {
        print("$i ")
    }
    println()
}

/* ==================== С шагом (step) ==================== */

// 5) for с шагом 2: от 1 до 9
fun step_1to9_step2() {
    for (i in 1..9 step 2) {
        print("$i ")
    }
    println()
}

// 6) for: каждое третье число в диапазоне 1..20
fun everyThird_1to20() {
    for (i in 1..20 step 3) {
        print("$i ")
    }
    println()
}

/* ==================== Использование until ==================== */

// 7) size, for с шагом 2: от 3 до size (не включая size)
fun until_withSize(size: Int) {
    // Требование: выводить числа 3,5,7,... < size
    for (i in 3 until size step 2) {
        print("$i ")
    }
    println()
}

/* ==================== Цикл while ==================== */

// 8) while: квадраты чисел от 1 до 5
fun while_squares_1to5() {
    var i = 1
    while (i <= 5) {
        print("${i * i} ")
        i++
    }
    println()
}

// 9) while: уменьшать число от 10 до 5, затем вывести результат
fun while_decrease_10to5() {
    var x = 10
    while (x > 5) {
        x-- // уменьшаем до тех пор, пока станет 5
    }
    println("Результат после уменьшения: $x")
}

/* ==================== Цикл do..while ==================== */

// 10) do..while: вывести числа от 5 до 1
fun doWhile_5to1() {
    var i = 5
    do {
        print("$i ")
        i--
    } while (i >= 1)
    println()
}

// 11) do..while: повторять пока счётчик < 10, начиная с 5
fun doWhile_from5_until10() {
    var counter = 5
    do {
        print("$counter ")
        counter++
    } while (counter < 10)
    println()
}

/* ==================== Прерывание и пропуск итерации ==================== */

// 12) for 1..10 и break при достижении 6
fun break_in_for_1to10_stopAt6() {
    for (i in 1..10) {
        if (i == 6) break
        print("$i ")
    }
    println()
}

// 13) бесконечный while, печатает с 1, прерывается при достижении 10
fun infiniteWhile_breakAt10() {
    var i = 1
    while (true) {
        print("$i ")
        if (i == 10) break
        i++
    }
    println()
}

// 14) for 1..10: continue, чтобы пропустить чётные
fun continue_in_for_skipEven() {
    for (i in 1..10) {
        if (i % 2 == 0) continue
        print("$i ")
    }
    println()
}

// 15) while: вывести 1..10, пропуская кратные 3
fun while_1to10_skipMultiplesOf3() {
    var i = 1
    while (i <= 10) {
        if (i % 3 == 0) {
            i++
            continue
        }
        print("$i ")
        i++
    }
    println()
}