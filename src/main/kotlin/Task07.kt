import kotlin.system.exitProcess

/**
В школе перед Новым Годом устраивают игру в Тайного Санту. Каждому ученику i выдается ученик a_i, которому он
должен подарить подарок. Костя, как администратор игры, определил каждому школьнику свое число a_i. Но потом его
коллега Маша спросила: А правда ли, что если начать цепочку подарков от школьника 1 к школьнику a_1, потом a_a1 и
так далее, то цепочка замкнется на школьнике 1, после того, как задействует всех остальных учеников ровно по
одному разу?
Костя не знает, правда это или нет, но он собирается изменить ровно одно число a_i, чтобы получить конфигурацию,
которая устроит Машу. Помогите ему с этим.

Формат входных данных
В первой строке находится натуральное число n — количество школьников (2≤n≤10e5). В следующей строке находится n
натуральных чисел a_i — ученик, который достался Тайному Санте с номером i(1≤a_i≤n).

Формат выходных данных
В первой строке выведите два числа (1≤x,y≤n,x!=y) — номер ученика x, которому нужно изменить число a_x, и новое
значение a_x.
Должно выполняться условие a_x != y. Если ответов несколько — выведите любой.
Если сделать это невозможно — выведите <<−1−1>>

Замечание
В первом примере хотя бы один школьник будет дарить подарок сам себе.
Во втором примере после изменения происходят передачи подарков 1→2→3→1.

Примеры данных
Пример 1
Ввод
3
1  2  3
Вывод
-1  -1
Пример 2
Ввод
3
1  3  1
Вывод
1  2
 */
class Task07 {
    private val countLearner = readln().trim().toInt()
    private val listLearner = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }

    init {
        val listAnswer = mutableListOf<Int>()
        var countModification = 0
        var indexModification = -1


        if (countLearner % 2 == 0) {
            var value = countLearner
            for (i in 0 until countLearner) {
                listAnswer.add(i, value--)
                if (listLearner[i] != listAnswer[i]) {
                    countModification++
                    indexModification = i
                }
                if (countModification > 2) {
                    printNoAnswer()
                    exitProcess(0)
                }
            }
            println("${indexModification + 1} ${listAnswer[indexModification]}")
        } else {
            var value = 1
            listAnswer.add(0, value)
            if (listLearner[0] == countLearner || listLearner[0] == 1) {
                countModification++
                indexModification = value
            }
            for (i in 1 until countLearner) {
                value++
                listAnswer.add(i, value)
                if (listLearner[i] == i + 1 || listLearner[i] == listAnswer[i - 1]) {
                    countModification++
                    indexModification = i
                }
                if (countModification > 1) {
                    printNoAnswer()
                    exitProcess(0)
                }
            }
            println("$indexModification ${listAnswer[indexModification]}")
        }
    }
}

private fun printNoAnswer() {
    println("-1 -1")
}