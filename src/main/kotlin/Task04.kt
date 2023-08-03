import kotlin.math.log10
import kotlin.system.exitProcess

/**
У Кости есть бумажка, на которой написано n чисел. Также у него есть возможность не больше, чем k раз, взять любое
число с бумажки, после чего закрасить одну из старых цифр, а на ее месте написать новую произвольную цифру.
На какое максимальное значение Костя сможет увеличить сумму всех чисел на листочке?

Формат входных данных
В первой строке входного файла даны два целых числа n,k — количество чисел на бумажке и ограничение на число
операций. (1≤n≤1000,1≤k≤10e4). Во второй строке записано n чисел a_i — числа на бумажке (1≤a_i≤10e9)

Формат выходных данных
В выходной файл выведите одно число — максимальную разность между конечной и начальной суммой.

Замечание
В первом примере Костя может изменить две единицы на две девятки, в результате чего сумма чисел увеличится на 16.
Во втором примере Костя меняет число 85 на 95.
В третьем примере можно ничего не менять.
Обратите внимание, что ответ может превышать вместимость 32-битного типа данных.

Примеры данных
Пример 1
Ввод
5  2
1  2  1  3  5
Вывод
16
Пример 2
Ввод
3  1
99  5  85
Вывод
10
Пример 3
Ввод
1  10
9999
Вывод
0
 */
class Task04 {
    private val inputStringFirst = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
    private val sizeList = inputStringFirst[0]
    private var countDiff = inputStringFirst[1]
    private val list = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .sorted()

    private val listExtreme = listOf(9, 99, 999, 9999)

    init {
        val modifiedList = mutableListOf<MutableList<Int>>()
        for (i in 0..<getBitDepth(list.last())) {
            modifiedList.add(mutableListOf())
        }
        list.forEach {
            when (getBitDepth(it)) {
                1 -> {
                    modifiedList[0].add(it)
                }

                2 -> {
                    modifiedList[1].add(it)
                }

                3 -> {
                    modifiedList[2].add(it)
                }

                4 -> {
                    modifiedList[3].add(it)
                }
            }
        }

        var isItemNotModified = true
        for (i in modifiedList.lastIndex downTo 0) {
            val item = modifiedList[i]

            if (item.isNotEmpty() && isNotContainsExtremeValues(item)) {
                isItemNotModified = false

                for (j in 0..item.lastIndex) {
                    if (isNotExtremeValues(item[j]) && countDiff > 0) {
                        item[j] = when (getBitDepth(item[j])) {
                            1 -> 9
                            2 -> 90 + item[j].toString().drop(1).toInt()
                            3 -> 900 + item[j].toString().drop(1).toInt()
                            else -> 9000 + item[j].toString().drop(1).toInt()
                        }
                        countDiff--
                    } else {
                        continue
                    }
                }
            }
            if (i == 0 && isItemNotModified) {
                println(0)
                exitProcess(0)
            }
        }

        println(modifiedList.sumOf { it.sum() } - list.sum())
    }

    private fun getBitDepth(value: Int): Int {
        return when {
            value == 0 -> 1
            value < 0 -> log10(-value.toFloat()).toInt() + 2
            else -> log10(value.toFloat()).toInt() + 1
        }
    }

    private fun isNotContainsExtremeValues(list: List<Int>): Boolean {
        var flag = true
        run breaking@{
            list.forEach {
                flag = when (it) {
                    9 -> false
                    99 -> false
                    999 -> false
                    9999 -> false
                    else -> {
                        return@breaking
                    }
                }
            }
        }
        return flag
    }

    private fun isNotExtremeValues(value: Int): Boolean {
        return !listExtreme.contains(value)
    }
}