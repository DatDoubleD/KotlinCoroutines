package com.doanducdat.testfirstkotlincoroutines.sequence

import kotlin.system.measureNanoTime

fun main() {
    //nen test ky truong hop (ly thuyet filter list nhanh hon nhung test sequence lai nhanh hon???)
    // k co operation -> list nhanh hon
    val sequence = generateSequence (1){ it + 1 }.take(10000000)
    val list = sequence.toList()
    println("L = ${measureNanoTime { list.average() }}")
    println("S = ${measureNanoTime { sequence.average() }}")
}