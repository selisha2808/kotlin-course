package org.example.lessons.lesson14.homework

class homework14/* ---------- 1) Событие: вечеринка ---------- */
class Party(
    var location: String,
    var attendees: Int
) {
    fun details() {
        println("Вечеринка в \"$location\", гостей: $attendees")
    }
}

/* ---------- 2) Аспект реальности: эмоция ---------- */
class Emotion(
    var type: String,
    var intensity: Int // 0..100
) {
    fun express() {
        val tone = when (intensity) {
            in 0..20   -> "еле заметна"
            in 21..50  -> "умеренная"
            in 51..80  -> "сильная"
            else       -> "бурная"
        }
        println("Эмоция: $type ($tone, интенсивность=$intensity)")
    }
}

/* ---------- 3) Природное явление: Луна ---------- */
object Moon {
    var isVisible: Boolean = false
    var phase: String = "New Moon"

    fun showPhase() {
        println("Луна: ${if (isVisible) "видна" else "не видна"}, фаза: $phase")
    }
}

/* ---------- 4) Покупка: продукт (дата-класс с кириллическими свойствами) ---------- */
data class Product(
    val название: String,
    val цена: Double,
    val количество: Int
)

/* ---------- 5) Мероприятие: концерт ---------- */
class Concert(
    var группа: String,
    var местоПроведения: String,
    var стоимость: Double,
    var вместимостьЗала: Int
) {
    private var проданныеБилеты: Int = 0

    fun info() {
        println(
            "Концерт: $группа @ $местоПроведения | цена: $стоимость | " +
                    "вместимость: $вместимостьЗала | продано: $проданныеБилеты"
        )
    }

    /** Возвращает true, если билет успешно куплен */
    fun купитьБилет(): Boolean {
        if (проданныеБилеты < вместимостьЗала) {
            проданныеБилеты++
            return true
        }
        return false
    }
}

/* ===================== 6) Стеллаж и полки ===================== */

/** Полка стеллажа */
class Shelf(
    val capacity: Int
) {
    private val items: MutableList<String> = mutableListOf()

    private fun used(): Int = items.sumOf { it.length }
    private fun remaining(): Int = capacity - used()

    /** Добавление предмета; true если поместился */
    fun addItem(name: String): Boolean {
        if (canAccommodate(name)) {
            items.add(name)
            return true
        }
        return false
    }

    /** Удаление предмета по названию; true если нашли и удалили */
    fun removeItem(name: String): Boolean = items.remove(name)

    /** Проверка вместимости для предмета */
    fun canAccommodate(name: String): Boolean =
        name.length <= remaining()

    /** Проверка наличия предмета */
    fun containsItem(name: String): Boolean = name in items

    /** Неизменяемый список предметов */
    fun getItems(): List<String> = items.toList()

    /** Оставшаяся вместимость (для печати) */
    fun remainingCapacity(): Int = remaining()

    override fun toString(): String =
        "Shelf(capacity=$capacity, remaining=${remaining()}, items=$items)"
}

/** Стеллаж */
class Rack(
    private val maxShelves: Int
) {
    private val shelves: MutableList<Shelf> = mutableListOf()

    /** Добавление полки; true если добавили (есть место и полки нет в стеллаже) */
    fun addShelf(shelf: Shelf): Boolean {
        if (shelves.size >= maxShelves) return false
        if (shelf in shelves) return false
        shelves.add(shelf)
        return true
    }

    /**
     * Удаление полки по индексу.
     * Возвращает предметы с полки (или пустой список, если полки нет).
     */
    fun removeShelf(index: Int): List<String> {
        if (index !in shelves.indices) return emptyList()
        val removed = shelves.removeAt(index)
        return removed.getItems()
    }

    /** Добавить предмет на первую полку, где он помещается */
    fun addItem(name: String): Boolean {
        for (s in shelves) {
            if (s.addItem(name)) return true
        }
        return false
    }

    /** Удалить один предмет с любой полки */
    fun removeItem(name: String): Boolean {
        for (s in shelves) {
            if (s.removeItem(name)) return true
        }
        return false
    }

    /** Проверка наличия предмета на стеллаже */
    fun containsItem(name: String): Boolean =
        shelves.any { it.containsItem(name) }

    /** Неизменяемый список полок */
    fun getShelves(): List<Shelf> = shelves.toList()

    /** Печать содержимого стеллажа в читаемом виде */
    fun printContents() {
        println("=== Rack: ${shelves.size}/$maxShelves полок ===")
        shelves.forEachIndexed { idx, sh ->
            println(
                "#$idx | capacity=${sh.capacity}, remaining=${sh.remainingCapacity()} | " +
                        "items=${sh.getItems()}"
            )
        }
        if (shelves.isEmpty()) println("(пусто)")
    }

    /**
     * Сложное удаление полки:
     *  - пытаемся перераспределить предметы удаляемой полки по другим полкам,
     *    начиная с самых длинных предметов;
     *  - предмет, который не помещается никуда, пропускаем (сохраняем в «неразмещённые»);
     *  - удаляем полку;
     *  - возвращаем неизменяемый список неразмещённых предметов;
     *  - если индекс невалидный, вернуть пустой список.
     */
    fun advancedRemoveShelf(index: Int): List<String> {
        if (index !in shelves.indices) return emptyList()

        val shelfToRemove = shelves[index]
        // Копируем предметы и сортируем по длине убыв.
        val itemsDesc = shelfToRemove.getItems().sortedByDescending { it.length }.toMutableList()
        val unplaced = mutableListOf<String>()

        // Перебираем предметы и пытаемся разместить на других полках (кроме удаляемой)
        while (itemsDesc.isNotEmpty()) {
            val item = itemsDesc.removeAt(0)
            var placed = false
            for ((i, s) in shelves.withIndex()) {
                if (i == index) continue // не кладём обратно на удаляемую полку
                if (s.addItem(item)) { placed = true; break }
            }
            if (!placed) unplaced.add(item)
        }

        // Удаляем полку (с её остатками)
        shelves.removeAt(index)

        return unplaced.toList()
    }
}

/* ---------- Мини-демо ---------- */
fun main() {
    // Party
    val p = Party("Roof Top", 25); p.details()

    // Emotion
    val e = Emotion("радость", 72); e.express()

    // Moon
    Moon.isVisible = true; Moon.phase = "Full Moon"; Moon.showPhase()

    // Product
    val prod = Product("Чайник", 1999.0, 2)
    println(prod)

    // Concert
    val c = Concert("The Codes", "Green Hall", 35.0, 3)
    c.info()
    println("Купил? " + c.купитьБилет())
    println("Купил? " + c.купитьБилет())
    println("Купил? " + c.купитьБилет())
    println("Купил? " + c.купитьБилет()) // ложь — нет мест
    c.info()

    // Rack / Shelf
    val rack = Rack(maxShelves = 3)
    val s1 = Shelf(capacity = 12)
    val s2 = Shelf(capacity = 10)
    val s3 = Shelf(capacity = 8)

    println("addShelf s1: " + rack.addShelf(s1))
    println("addShelf s2: " + rack.addShelf(s2))
    println("addShelf s3: " + rack.addShelf(s3))
    println("addShelf s3 again (should be false): " + rack.addShelf(s3))

    listOf("молоток", "гвозди", "веревка", "лента", "лейка", "пила").forEach {
        println("addItem \"$it\": " + rack.addItem(it))
    }
    rack.printContents()

    println("-- removeItem(\"лента\"): " + rack.removeItem("лента"))
    rack.printContents()

    println("-- containsItem(\"пила\"): " + rack.containsItem("пила"))

    // Простое удаление полки
    val removedItems = rack.removeShelf(5) // несуществующий индекс
    println("-- removeShelf(5) -> $removedItems (ожидаем пустой)")
    rack.printContents()

    // Сложное удаление полки с перераспределением
    val unplaced = rack.advancedRemoveShelf(0)
    println("-- advancedRemoveShelf(0) -> неразмещённые: $unplaced")
    rack.printContents()
}