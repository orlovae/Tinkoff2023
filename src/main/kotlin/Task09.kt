/**
Даня в обеденный перерыв ходит в одно и то же кафе. Ему, как сотруднику банка, положено специальное предложение:
при каждой покупке больше, чем на 100 рублей, Даня получает купон на бесплатный обед. Даня узнал стоимость своих
обедов на ближайшие n дней. Ему хочется минимизировать свои затраты, грамотно используя талоны. Требуется найти
минимальные суммарные затраты Дани на обеды.

Формат входных данных
В первой строке дается натуральное число n (0≤n≤100). В каждой из n строк записана стоимость обеда в каждой из
дней (неотрицательное целое число, не больше, чем 300).

Формат выходных данных
В первой строке выдайте минимально возможную суммарную стоимость обедов.

Замечание
В первом примере Дане придется купить первые 3 обеда, после чего у него появится талон. Этот талон будет выгоднее
всего потратить на последний обед. Таким образом, он купит первые 4 обеда и получит пятый бесплатный.

Примеры данных
Ввод
5
35
40
101
59
63
Вывод
235

Скорее всего тут динамическое программирование.
 */
class Task09 {
    private val sizeList = readln().trim().toInt()

    init {
        val listPriceAfterFirstEntryIsCoupon = mutableListOf<Int>()
        val listPriceAfterFirstEntryIsNotCoupon = mutableListOf<Int>()
        val listPriceBeforeFirstEntryIsNotCoupon = mutableListOf<Int>()
        var isFirstEntry = false
        var countPrice = 0

        for (i in 0 until sizeList) {
            val price = readln().trim().toInt()
            countPrice += price

            if (isFirstEntry) {
                listPriceAfterFirstEntryIsNotCoupon.add(price)
            }

            if (price > 100) {
                isFirstEntry = true
                listPriceAfterFirstEntryIsCoupon.add(price)
            }

            if (!isFirstEntry) {
                listPriceBeforeFirstEntryIsNotCoupon.add(price)
            }
        }

        println(listPriceBeforeFirstEntryIsNotCoupon)
        println(listPriceAfterFirstEntryIsCoupon.sorted())
        println(listPriceAfterFirstEntryIsNotCoupon.sorted())

        val sortedListPriceAfterFirstEntryIsCoupon = listPriceAfterFirstEntryIsCoupon.sorted()
        val sortedListPriceAfterFirstEntryIsNotCoupon = listPriceAfterFirstEntryIsNotCoupon.sorted()

        var sumListPriceAfterFirstEntryIsNotCouponModifered = 0

        if (sortedListPriceAfterFirstEntryIsCoupon.size <= sortedListPriceAfterFirstEntryIsNotCoupon.size) {
            sumListPriceAfterFirstEntryIsNotCouponModifered = sortedListPriceAfterFirstEntryIsNotCoupon.dropLast(
                sortedListPriceAfterFirstEntryIsCoupon.size
            ).sum()
        }

        println(listPriceBeforeFirstEntryIsNotCoupon.sum() + sumListPriceAfterFirstEntryIsNotCouponModifered)
    }
}