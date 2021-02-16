package com.doanducdat.testfirstkotlincoroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        TestZip()
        TestCombine()
    }
}
suspend fun TestSingle(){
   /* kiểm tra có phải exit 1 giá trị hay k?, k phải -> lỗi
     null -> lỗi*/
    //(1..10).asFlow().single()
    /*null -> trả về null, chỉ >1 mới lỗi*/
    val a = listOf<Int>().asFlow().singleOrNull()
    println(a)
}
suspend fun TestZip(){
    //duyệt 2 collection cùng 1lúc với asflow + zip
    //DUYỆT FLOW 1 + CHỜ FLOW 2 ĐỂ DUYỆT FLOW 2: KQ -> 1-ONE, 2-TWO...
    val num = (1..3).asFlow().onEach { delay(100) } // delay trước khi emit kết quả
    val strs = listOf<String>("one", "two", "three").asFlow().onEach { delay(400) }
    num.zip(strs){
        x, i -> "num = $x - strs = $i"
    }.collect {
        println(it)
    }
}
suspend fun TestCombine(){
    //duyệt 2 collection cùng 1lúc với asflow + zip
    //DUYỆT HÉT FLOW 1 RỒI MỚI CHẠY FLOW 2: KQ-> 3-ONE, 3-TWO...
    val num = (1..3).asFlow().onEach { delay(100) } // delay trước khi emit kết quả
    val strs = listOf<String>("one", "two", "three").asFlow().onEach { delay(400) }
    num.combine(strs){
        x, i -> "num = $x - strs = $i"
    }.collect {
        println(it)
    }
}