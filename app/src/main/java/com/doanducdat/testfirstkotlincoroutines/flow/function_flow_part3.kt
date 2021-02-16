package com.doanducdat.testfirstkotlincoroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        TestFlatMapConcat()
        println("-----------------------")
        TestFlatMapMerge()
        println("-----------------------")
        TestFlatMapLates()
    }
}
suspend fun TestFlatMapConcat(){
    //chạy từ: requestflow -> collect xong quay lại requestflow -> emit "$i second" -> collect
    //-> chạy hết hàm requestflow mới duyệt đến giá trị it tiếp theo
    var startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
            .flatMapConcat { requestFlow(it) }
            .collect {
                println("value = $it at ${System.currentTimeMillis() - startTime}")
            }
}
fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms, flapmatconcat sẽ chờ delay, merge thì không
    emit("$i: Second")
}

suspend fun TestFlatMapMerge(){
    //chạy hết emit $i First rồi mới tới $i second
    var startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
            .flatMapMerge { requestFlow(it) }
            .collect {
                println("value = $it at ${System.currentTimeMillis() - startTime}")
            }
}
suspend fun TestFlatMapLates(){
    //chạy hết emit $i First và bỏ qua khi gặp delay trong requestflow, chỉ lấy $i second cuối cùng
    var startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }
            .flatMapLatest { requestFlow(it) }
            .collect {
                println("value = $it at ${System.currentTimeMillis() - startTime}")
            }
}