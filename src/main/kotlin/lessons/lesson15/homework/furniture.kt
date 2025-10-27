package org.example.lessons.lesson15.homework

class furnitureopen open class Мебель(
    open val материал: String,
    open val производитель: String
) {
    open fun паспорт() = println("Материал: $материал, производитель: $производитель")
}

open class Стул(
    материал: String,
    производитель: String,
    val спинкаЕсть: Boolean
) : Мебель(материал = материал, производитель = производитель)

open class Стол : Мебель {
    val посадочныхМест: Int

    constructor(материал: String, производитель: String, посадочныхМест: Int) : super(материал, производитель) {
        посадочныхМест.also { this.посадочныхМест = it }
    }
}

open class Шкаф : Мебель {
    val секций: Int

    constructor(материал: String, производитель: String, секций: Int) : super(материал, производитель) {
        this.секций = секций
    }
}

class КнижныйШкаф(
    материал: String,
    производитель: String,
    секций: Int,
    val полок: Int
) : Шкаф(материал, производитель, секций)

class ПлатянойШкаф(
    материал: String,
    производитель: String,
    секций: Int,
    val штанг: Int
) : Шкаф(материал, производитель, секций) {
}