import java.math.BigDecimal
import kotlin.math.abs

/**
Соня с Сашей купили торт в форме выпуклого многоугольника на n вершинах. Они хотят разделить этот торт на две
равные части одним строго вертикальным разрезом. А, именно, линия разреза на торте должна быть параллельна
координатной оси O_y.
Найдите x-координату, вдоль которой надо сделать искомый разрез.

Формат входных данных
В первой строке вводится число n — количество вершин многоугольника (1≤n≤1000)
В следующих n строках записаны записаны координаты вершин торта-многоугольника в порядке обхода. Гарантируется,
что координаты — целые числа, не превосходящие по модулю 10e3.

Формат выходных данных
Выведите одно число — искомую x-координату. Ответ надо выводить с точностью не меньше 10^-6.

Замечание
Первый тест — это квадрат, разделенный на две равные части.

Примеры данных
Ввод
4
0  0
0  2
2  2
2  0
Вывод
1.000000000
 */
class Task10 {
    private val countPoint = readln().trim().toInt()
    private val listPoint = getListXSorted(countPoint)

    init {
        val preAnswer = (abs(listPoint.first()) + abs(listPoint.last())) / 2.0000000
        val answer = if (listPoint.last() > 0) {
            listPoint.last() - preAnswer
        } else {
            0 - preAnswer
        }

        println(BigDecimal(answer).setScale(9))

    }
}

private fun getListXSorted(size: Int): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 0 until size) {
        val inputString = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        list.add(
            inputString[0]
        )
    }
    return list.sorted().toList()
}

//private fun getListPoint(size: Int): List<Point> {
//    val list = mutableListOf<Point>()
//    for (i in 0 until size) {
//        val inputString = readln()
//            .split(" ")
//            .filter { it.isNotEmpty() }
//            .map { it.toInt() }
//        list.add(
//            Point(
//                x = inputString[0],
//                y = inputString[1]
//            )
//        )
//    }
//    return list.toList()
//}

private data class Point(
    val x: Int, val y: Int
)