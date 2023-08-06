import kotlin.system.exitProcess

/**
На физкультуре происходит разбиение по двум командам. Ребята выстроены в шеренгу, у каждого из них есть свой рост
a_i Разбиение по командам произойдет по принципу «четный-нечетный» — все школьники с четным ростом отправляются в
одну команду, а нечетные — в другую.
В отличие от привычного урока, ребята не выстроились по росту. Вместо привычного порядка они встали случайно.
Теперь физрук Яша смотрит на шеренгу и думает — может ли ровно одна пара учеников поменяться местами так, чтобы
команды оказались такими же, как и по принципу «первый-второй». Иначе говоря, он хочет получить такой порядок, при
котором все ученики с четным ростом стоят на четных позициях, а с нечетным — на нечетных.
Помогите Яше найти нужную замену.

Формат входных данных
В первой строке находится число n(2≤n≤1000) — количество учеников в шеренге.
В следующей строке находится n натуральных чисел a_i (1≤a_i≤10e9) — рост учеников.

Формат выходных данных
В единственной строке выведите i и j — номера элементов, которые нужно поменять местами, чтобы добиться заданного
условия (1≤i,j≤n, i!=j). Если ответов несколько — разрешается вывести любой.
Если не существует способа поменять два элемента местами — выведите -1−1.

Замечания
В первом примере хотя бы один ученик с четным ростом будет стоять на нечетной позиции.
Во втором тесте замена приведет к неправильному состоянию.
В третьем тесте из условия замена приведет шеренгу к валидному состоянию [1,2]

Примеры данных
Пример 1
Ввод
4
2 1 4 6
Вывод
-1 -1

Пример 2
Ввод
2
1 2
Вывод
-1 -1
Пример 3
Ввод
2
2 1
Вывод
1 2
 */
class Task06 {
    private val student = readln().trim().toInt()
    private val listHeightStudent = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toLong() }

    init {


        if (!isEven(listHeightStudent.lastIndex.toLong())) {
            println(getAnswerEvenList(listHeightStudent))
        } else {
            val modifiedList = listHeightStudent.dropLast(1)
            val answer = getAnswerEvenList(modifiedList)
            if (!isEven(listHeightStudent.last())) {
                answer.append(" ${listHeightStudent.last()}")
                println(answer.toString())
            } else {
                printNoAnswer()
            }
        }


    }
}

private fun isEven(value: Long): Boolean {
    return value % 2 == 0L
}

private fun getAnswerEvenList(list: List<Long>): StringBuilder {
    val answer = StringBuilder()
    var index = 0
    while (index <= list.lastIndex) {
        val first = list[index]
        val second = list[index + 1]

        if (isEven(first) && !isEven(second)) {
            answer.append("$second $first")
        } else {
            printNoAnswer()
        }
        index += 2
    }
    return answer
}

private fun printNoAnswer(){
    println("-1 -1")
    exitProcess(0)
}