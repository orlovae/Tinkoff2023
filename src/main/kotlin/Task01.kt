/**
Костя подключен к мобильному оператору «Мобайл». Абонентская плата Кости составляет A рублей в месяц. За эту
стоимость Костя получает B мегабайт интернет-трафика. Если Костя выйдет за лимит трафика, то каждый следующий
мегабайт будет стоить ему C рублей.
Костя планирует потратить D мегабайт интернет-трафика в следующий месяц. Помогите ему сосчитать, во сколько рублей
ему обойдется интернет.
Формат входных данных
Вводится 4 целых положительных числа A, B, C, D (1≤A,B,C,D≤100) — стоимость тарифа Кости, размер тарифа Кости,
стоимость каждого лишнего мегабайта, размер интернет-трафика Кости в следующем месяце. Числа во входном файле
разделены пробелами.

Формат выходных данных
Выведите одно натуральное число — суммарные расходы Кости на интернет.

Замечание
В первом примере Костя сначала оплатит пакет интернета, после чего потратит на 5 мегабайт больше, чем разрешено по
тарифу. Следовательно, за 5 мегабайт он дополняет отдельно, получившаяся стоимость 100+12×5=160 рублей.
Во втором примере Костя укладывается в тарифный план, поэтому платит только за него.

Примеры данных
Пример 1
100  10  12  15
160
Пример 2
100  10  12  1
100
 */
class Task01 {
    private val inputString = readln()
        .split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
    private val priceTariff = inputString[0]
    private val sizeTariff = inputString[1]
    private val priceOneMb = inputString[2]
    private val realTariff = inputString[3]

    init {
        val diff = realTariff - sizeTariff
        if (diff < 0) {
            println(priceTariff)
        } else {
            println(priceTariff + diff * priceOneMb)
        }
    }
}