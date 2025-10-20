package org.example.lessons.lesson13.homework

/* ====================  ФУНКЦИИ ПО ЗАДАЧАМ  ==================== */

// 1) Среднее время тестов (Map<name, seconds>): используем values
fun avgExecTime(times: Map<String, Int>): Double = times.values.average()

// 2) Список всех тестовых методов (ключи): keys
fun testMethodNames(meta: Map<String, String>): List<String> = meta.keys.toList()

// 3) Добавить в изменяемый словарь новый тест и результат: put
fun addTestResult(m: MutableMap<String, String>, name: String, result: String) { m.put(name, result) }

// 4) Посчитать количество успешных тестов: count (на entries)
fun countPassed(results: Map<String, String>): Int =
    results.entries.count { it.value.equals("passed", ignoreCase = true) }

// 5) Удалить баг, который исправлен: remove
fun removeFixedBug(bugs: MutableMap<String, String>, bugName: String) { bugs.remove(bugName) }

// 6) Сообщение по странице и статусу: entries (итерация)
fun pageStatusesMsg(statuses: Map<String, String>): List<String> =
    statuses.entries.map { (url, status) -> "Page $url -> $status" }

// 7) Сервисы, время ответа > threshold: filterValues
fun slowServices(svcTimes: Map<String, Long>, thresholdMs: Long): Map<String, Long> =
    svcTimes.filterValues { it > thresholdMs }

// 8) Найти статус endpoint или “не протестирован”: getOrDefault
fun endpointStatus(api: Map<String, String>, endpoint: String): String =
    api.getOrDefault(endpoint, "not_tested")

// 9) Получить "browserType" (не null): getValue
fun browserType(cfg: Map<String, String>): String = cfg.getValue("browserType")

// 10) Копия неизменяемого словаря версий + новая версия: toMutableMap (как ключевой метод)
fun addVersionCopy(versions: Map<String, String>, k: String, v: String): Map<String, String> =
    versions.toMutableMap().apply { this[k] = v }.toMap()

// 11) Настройки для модели или дефолт: getOrElse
fun deviceSettingsOrDefault(settings: Map<String, String>, model: String, default: () -> String): String =
    settings.getOrElse(model, default)

// 12) Проверить наличие кода ошибки: containsKey
fun hasErrorCode(errors: Map<Int, String>, code: Int): Boolean = errors.containsKey(code)

// 13) Отфильтровать сценарии по версии в ключе: filterKeys
fun scenariosByVersion(scenarios: Map<String, String>, version: String): Map<String, String> =
    scenarios.filterKeys { it.contains(version) }

// 14) Есть ли модули с неудачным тестированием: any
fun hasFailedModule(mods: Map<String, String>): Boolean =
    mods.any { it.value.equals("failed", ignoreCase = true) }

// 15) Добавить в изменяемый словарь настройки из другого: forEach (по второму словарю)
fun mergeIntoMutable(target: MutableMap<String, String>, src: Map<String, String>) {
    src.forEach { (k, v) -> target[k] = v }
}

// 16) Объединить два неизменяемых словаря багов: plus
fun mergeImmutableBugs(a: Map<String, String>, b: Map<String, String>): Map<String, String> = a + b

// 17) Очистить изменяемый словарь: clear
fun clearTemp(temp: MutableMap<String, Any?>) { temp.clear() }

// 18) Исключить skipped из отчёта: filterNot
fun excludeSkipped(report: Map<String, String>): Map<String, String> =
    report.filterNot { it.value.equals("skipped", ignoreCase = true) }

// 19) Копия конфигов без нескольких ключей: minus
fun copyWithout(config: Map<String, String>, keys: List<String>): Map<String, String> = config - keys

// 20) Отчёт "Test ID: результат": map
fun reportLines(results: Map<String, String>): List<String> =
    results.map { (id, res) -> "Test $id: $res" }

// 21) Преобразовать изменяемый словарь результатов в неизменяемый: toMap
fun freezeResults(mutable: MutableMap<String, String>): Map<String, String> = mutable.toMap()

// 22) Заменить числовой ID на строковый: mapKeys
fun stringifiedIds(times: Map<Int, Long>): Map<String, Long> = times.mapKeys { (k, _) -> k.toString() }

// 23) Увеличить оценки производительности на 10%: mapValues
fun bumpPerf(perf: Map<String, Double>): Map<String, Double> = perf.mapValues { it.value * 1.1 }

// 24) Пуст ли словарь ошибок компиляции: isEmpty
fun isCompileErrorsEmpty(errors: Map<String, String>): Boolean = errors.isEmpty()

// 25) Нагрузочные результаты не пусты: isNotEmpty
fun isLoadResultsNotEmpty(load: Map<String, String>): Boolean = load.isNotEmpty()

// 26) Все тесты прошли успешно: all
fun allPassed(results: Map<String, String>): Boolean = results.all { it.value.equals("passed", true) }

// 27) Есть хотя бы один с ошибкой: containsValue
fun hasAnyFailed(results: Map<String, String>): Boolean = results.containsValue("failed")

// 28) Оставить только “optional” и НЕ прошли: filter
fun optionalNotPassed(svcs: Map<String, String>): Map<String, String> =
    svcs.filter { (name, status) -> name.contains("optional", true) && !status.equals("passed", true) }


/* ====================  ДЕМО ==================== */

fun main() {
    // 1
    println("1) avgExecTime = " + avgExecTime(mapOf("t1" to 2, "t2" to 3, "t3" to 5)))

    // 2
    println("2) methods = " + testMethodNames(mapOf("testLogin" to "meta", "testPay" to "meta")))

    // 3
    val resultsMutable = mutableMapOf("testA" to "passed")
    addTestResult(resultsMutable, "testB", "failed")
    println("3) $resultsMutable")

    // 4
    println("4) passed count = " + countPassed(mapOf("A" to "passed", "B" to "failed", "C" to "passed")))

    // 5
    val bugs = mutableMapOf("BUG-1" to "fixed", "BUG-2" to "open")
    removeFixedBug(bugs, "BUG-1")
    println("5) $bugs")

    // 6
    println("6) " + pageStatusesMsg(mapOf("https://a" to "200", "https://b" to "404")).joinToString())

    // 7
    println("7) slow = " + slowServices(mapOf("svc1" to 1200L, "svc2" to 800L, "svc3" to 1500L), 1000))

    // 8
    println("8) status = " + endpointStatus(mapOf("/login" to "OK"), "/pay"))

    // 9
    println("9) browserType = " + browserType(mapOf("browserType" to "Chrome", "grid" to "on")))

    // 10
    println("10) versions+ = " + addVersionCopy(mapOf("1.0" to "stable"), "1.1", "beta"))

    // 11
    println("11) device cfg = " +
            deviceSettingsOrDefault(mapOf("Pixel" to "A;B"), "iPhone") { "DEFAULT" })

    // 12
    println("12) has code 404? " + hasErrorCode(mapOf(404 to "NF", 500 to "ISE"), 404))

    // 13
    println("13) by version v2 = " + scenariosByVersion(mapOf("Test1_v1" to "Passed", "Test2_v2" to "Failed"), "v2"))

    // 14
    println("14) failed module? " + hasFailedModule(mapOf("Auth" to "Passed", "Billing" to "Failed")))

    // 15
    val env = mutableMapOf("grid" to "off")
    mergeIntoMutable(env, mapOf("threads" to "4", "region" to "eu"))
    println("15) env = $env")

    // 16
    println("16) merged bugs = " + mergeImmutableBugs(mapOf("B1" to "open"), mapOf("B2" to "fixed")))

    // 17
    val temp = mutableMapOf<String, Any?>("runId" to 123, "ts" to 999L)
    clearTemp(temp)
    println("17) cleared = $temp")

    // 18
    println("18) exclude skipped = " + excludeSkipped(mapOf("A" to "passed", "B" to "skipped", "C" to "failed")))

    // 19
    println("19) copyWithout = " + copyWithout(mapOf("browser" to "Chrome", "grid" to "on", "region" to "eu"),
        listOf("grid", "region")))

    // 20
    println("20) report lines = " + reportLines(mapOf("T1" to "Passed", "T2" to "Failed")).joinToString(" | "))

    // 21
    val lastRun = mutableMapOf("A" to "passed", "B" to "failed")
    val archived: Map<String, String> = freezeResults(lastRun)
    println("21) archived is immutable size=" + archived.size)

    // 22
    println("22) stringifiedIds = " + stringifiedIds(mapOf(1 to 100L, 2 to 200L)))

    // 23
    println("23) bumpPerf = " + bumpPerf(mapOf("1.0" to 1.5, "1.1" to 2.0)))

    // 24
    println("24) compile errors empty? " + isCompileErrorsEmpty(emptyMap()))

    // 25
    println("25) load results not empty? " + isLoadResultsNotEmpty(mapOf("r1" to "ok")))

    // 26
    println("26) all passed? " + allPassed(mapOf("A" to "passed", "B" to "passed")))

    // 27
    println("27) any failed? " + hasAnyFailed(mapOf("A" to "passed", "B" to "failed")))

    // 28
    println("28) optional not passed = " +
            optionalNotPassed(mapOf("optional_auth" to "failed", "core" to "passed", "optional_billing" to "skipped")))
}