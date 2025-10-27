package org.example.lessons.lesson15.homework

abstract class Materials {
    protected val materials = mutableListOf<String>()     // было private
    open fun addMaterial(material: String) {              // пометили open, чтобы переопределять
        materials.add(material)
    }
    fun extractMaterials(): List<String> {
        val extracted = materials.toList()
        materials.clear()
        return extracted
    }
    fun printContainer() {
        materials.forEachIndexed { index, layer ->
            println("[$index]: $layer")
        }
    }
}

/** 1) Класс: вставляет строку на дно (под индекс 0) */
class BottomInsertMaterials : Materials() {
    override fun addMaterial(material: String) {
        materials.add(0, material)
    }
}

/** 2) Класс: принимает список строк и вставляет их «вперемежку» в начало: 0,2,4,... */
class AlternatingHeadInserter : Materials() {
    fun addMaterialsInterleavedAtHead(list: List<String>) {
        // хотим после операции: новые на 0,2,4..., старые — на 1,3,5...
        // Шаг: собираем новый список требуемого вида.
        val old = materials.toList()
        materials.clear()
        val maxLen = maxOf(list.size, old.size)
        for (i in 0 until maxLen) {
            if (i < list.size) materials.add(list[i]) // позиции 0,2,4...
            if (i < old.size)  materials.add(old[i])  // позиции 1,3,5...
        }
    }
}

/** 3) Класс: при добавлении — сортирует весь список по алфавиту */
class SortedMaterials : Materials() {
    override fun addMaterial(material: String) {
        materials.add(material)
        materials.sort()
    }
}

/** 4) Класс: принимает Map<k,v> — ключи в начало (в обратном порядке), значения в конец */
class KeyFrontValueBackMaterials : Materials() {
    fun addPairs(pairs: Map<String, String>) {
        // Ключи должны оказаться в начале в обратном порядке добавления,
        // затем текущие материалы, затем значения (в порядковом обходе).
        val existing = materials.toList()
        val keys = pairs.keys.toList()
        val values = pairs.values.toList()

        materials.clear()
        // Ключи в обратном порядке
        for (k in keys.asReversed()) materials.add(k)
        // Старые материалы
        materials.addAll(existing)
        // Значения в конец (в исходном порядке)
        materials.addAll(values)
    }
}


/* ===================== ДЕМО ===================== */
fun main() {
    // Геометрия
    val f: ГеометрическаяФигура = Круг("красный", 5.0)
    f.info()
    val t = Треугольник("синий", Triple(3.0, 4.0, 5.0))
    t.info()

    // Учебные заведения
    val uni = Университет("КНУ", "Киев", "IV", факультеты = 15)
    uni.описание()

    // Мебель
    val шкаф = КнижныйШкаф("Дерево", "IKEA", секций = 2, полок = 6)
    шкаф.паспорт()

    // Контейнеры материалов
    println("\n--- BottomInsertMaterials ---")
    val btm = BottomInsertMaterials()
    btm.addMaterial("steel")
    btm.addMaterial("wood")
    btm.addMaterial("glass") // окажется сверху, но «дно» — индекс 0
    btm.printContainer()     // [0]: glass, [1]: wood, [2]: steel

    println("\n--- AlternatingHeadInserter ---")
    val alt = AlternatingHeadInserter()
    // заранее добавим что-то обычным способом
    alt.addMaterial("A")
    alt.addMaterial("B")
    alt.addMaterialsInterleavedAtHead(listOf("x", "y", "z"))
    alt.printContainer()     // ожидаемо: x, A, y, B, z

    println("\n--- SortedMaterials ---")
    val sorted = SortedMaterials()
    listOf("pear", "apple", "banana").forEach { sorted.addMaterial(it) }
    sorted.printContainer()  // по алфавиту: apple, banana, pear

    println("\n--- KeyFrontValueBackMaterials ---")
    val kv = KeyFrontValueBackMaterials()
    kv.addMaterial("middle") // увидим, что он останется в «середине»
    kv.addPairs(linkedMapOf("k1" to "v1", "k2" to "v2", "k3" to "v3"))
    kv.printContainer()
    // Результат: k3, k2, k1, middle, v1, v2, v3
}