import java.math.BigDecimal
import kotlin.math.log10

/**
Во время разработки некоторой задачи Саша решил сгенерировать несколько новых тестов. Каждый тест Саши должен
представлять собой натуральное число, не меньшее l и не большее r. Кроме того, натуральное число в тесте
обязательно должно состоять из одинаковых цифр.
Например, число 999 подходит под это требование, а число 123 — нет. Какое максимальное число различных тестов
сможет создать Саша?

Формат входных данных
В единственной строке вводятся два натуральных числа l,r (1≤l,r≤10e18) — ограничения на тесты Саши.
Обратите внимания, что эти числа не вместятся в 32-битный тип данных, используйте 64-битный при необходимости

Формат выходных данных
Выведите одно число — количество тестов, которое может сделать Саша.

Замечание
В первом тесте Саше подходят номера [4,5,6,7].
Во втором тесте подходят все числа, кратные 11, от 11 до 99.

Примеры данных
Пример 1
Ввод
4  7
Вывод
4
Пример 2
Ввод
10  100
Вывод
9

 */
class Task05 {
    private val inputString = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toBigDecimal() }
    private val firstInt = inputString[0]
    private val lastInt = inputString[1]

    init {
        val bitDepthFirst = getBitDepth(firstInt)
        val bitDepthLast = getBitDepth(lastInt)

        if (bitDepthLast != bitDepthFirst) {
            val countFirstList = getAnswerListFromRange(bitDepthFirst).filter { it >= firstInt }.size
            val countLastList = getAnswerListFromRange(bitDepthLast).filter { it <= lastInt }.size
            val countMiddle = (bitDepthLast - bitDepthFirst - 1) * 9
            println(countFirstList + countMiddle + countLastList)
        } else {
            val list = getAnswerListFromRange(bitDepthLast).filter { it in firstInt..lastInt }
            println(list.size)
        }
    }
}

private fun getAnswerListFromRange(bitDepth: Int): List<BigDecimal> {
    return if (bitDepth == 1) {
        (1..9).map {
            it.toBigDecimal()
        }
    } else {
        val list = mutableListOf<BigDecimal>()
        for (i in 1..9) {
            val number = StringBuilder()
            repeat(bitDepth) { number.append(i) }
            list.add(number.toString().toBigDecimal())
        }
        list
    }
}

private fun getBitDepth(value: BigDecimal): Int {
    return when {
        value.compareTo(BigDecimal.ZERO) == 0 -> 1
        value < BigDecimal.ZERO -> log10(-value.toFloat()).toInt() + 2
        else -> log10(value.toFloat()).toInt() + 1
    }
}