package com.doanducdat.testfirstkotlincoroutines.sequence

import kotlin.system.measureNanoTime

fun main() {
    //giong flow(asFlow) nhung flow dung trong bat dong bo con sequence thi nguoc lai
    //create
    val numbers:List<String> = listOf("one", "two", "three")
    val create1 = numbers.asSequence()
    val create2 = sequenceOf("one", "two", "three")
    val create3 = generateSequence(1){ it + 1}.take(100)
    val create4:List<Int> = sequence {
        // tuy y them cac phan tu theo y thich
        yield(1)
        yield(3)
        yieldAll(listOf(3, 4))
        yieldAll(generateSequence(7) { it + 2 }.take(3))
    }.toList()
    //print element of create4
    create4.forEach{ println(it)}
    //


}