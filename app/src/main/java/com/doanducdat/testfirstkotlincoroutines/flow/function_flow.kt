package com.doanducdat.testfirstkotlincoroutines.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    val list: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 9, 10)
    runBlocking {
       TestFold(list)
    }
}
suspend fun TestTransfrom(list: List<Int>){
    list.asFlow().transform {
        emit(it*it) // co the bien doi nhiu gia tri
        emit(it+1.0)
    }.collect {
        println("value = $it")
    }
}

suspend fun TestMap(list: List<Int>){
    list.asFlow().map {
        it*it // khác với transform -> map chỉ "emit" 1 gia trị, transform thì nhìu emit
    }.collect{
        println("value = $it")
    }
}

suspend fun TestTake(list: List<Int>){
    //lấy số lượng giá trị đầu tiên trong list
    list.asFlow().take(3).collect{
        println("value = $it")
    }
}

suspend fun TestFilter(list: List<Int>){
    list.asFlow().filter {
        it%2==0
    }.collect {
        println("value = $it")
    }
}

suspend fun TestReduce(list: List<Int>){
    //tính cộng dồn
    // accumulator: gia tri đã tính truoc đó, value : gia tri từ list
    var sum = list.asFlow().reduce { accumulator, value ->
        println("accumulator = $accumulator -  value = $value")
        value + accumulator // accumulator là giá trị đã cộng trước đó
    }
    println(sum)
}

suspend fun TestFold(list: List<Int>){
    //Giống reduce nhưng có thể tạo giá trị ban đầu
    // accumulator: gia tri = 5 ban đầu và lưu kết quá tính trước đó
    // , value : gia tri duyệt trong list
    var sum = list.asFlow().fold(5) { accumulator, value ->
        println("accumulator = $accumulator -  value = $value")
        value + accumulator // accumulator là giá trị đã cộng trước đó
    }
    println(sum)
}