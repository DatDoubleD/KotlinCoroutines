package com.doanducdat.testfirstkotlincoroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull


fun main() {
    // giong sequences nhung flow chay trong bat dong bo
    runBlocking {
        //1.introduce
        // foo dc chay chi khi dung collec{}, goi ham don thuan se k chay
        /*foo(5).collect {
            println("i = $it")
        }*/

        //2.cancellation, khi cancel coroutine hien tai ->  flow will be canceled
        withTimeoutOrNull(3500){
            foo(10).collect {
                println("i = $it")
            }
        }

        //3. tạo flow từ các list
     /*   (1..5).asFlow().collect { println(it) }
        val arr = arrayOf("a", "b", "c")
        arr.asFlow().collect { println(it) }
        val list = listOf<Int>(6,7,8,9,10)
        list.asFlow().collect { println(it) }*/
    }
}

fun foo(x:Int):Flow<Int> = flow<Int> {
    for (i in 0..x){
        delay(1000)
        emit(i)
    }
}