package org.example.lessons.lesson16.homework

class Cart {
    private val items: MutableMap<Int, Int> = mutableMapOf() // id -> qty

    fun addToCart(itemId: Int) {
        items[itemId] = items.getOrDefault(itemId, 0) + 1
    }
    fun addToCart(itemId: Int, amount: Int) {
        if (amount > 0) items[itemId] = items.getOrDefault(itemId, 0) + amount
    }
    fun addToCart(batch: Map<Int, Int>) {
        batch.forEach { (id, q) -> if (q > 0) addToCart(id, q) }
    }
    fun addToCart(ids: List<Int>) {
        ids.forEach { addToCart(it) }
    }

    override fun toString(): String {
        if (items.isEmpty()) return "Корзина пуста."
        val header = String.format("%-10s | %-8s", "ItemID", "Qty")
        val line = "-".repeat(header.length)
        val rows = items.entries.sortedBy { it.key }.joinToString("\n") {
            String.format("%-10d | %-8d", it.key, it.value)
        }
        val totalKinds = items.size
        val totalQty = items.values.sum()
        val summary = "Итого позиций: $totalKinds, всего товаров: $totalQty"
        return listOf(header, line, rows, line, summary).joinToString("\n")
    }
}

fun demoCart() {
    val cart = Cart()
    cart.addToCart(1001)
    cart.addToCart(1002, 3)
    cart.addToCart(mapOf(1001 to 2, 1003 to 5))
    cart.addToCart(listOf(1002, 1002, 1004))
    println(cart)
}

// Точка входа этого файла
fun main() {
    demoCart()
}