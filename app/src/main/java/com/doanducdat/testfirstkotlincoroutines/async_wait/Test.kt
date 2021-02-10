package com.doanducdat.testfirstkotlincoroutines.async_wait

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

suspend fun doSimeThingFunny1():Int{
    delay(1000)
    return 10
}
suspend fun doSomeThingfunny2():Int{
    delay(1000)
    return 20
}
fun main() {
   /* runBlocking {
        val timeRun = measureTimeMillis {
            val a = doSimeThingFunny1()
            val b = doSomeThingfunny2()
            println(a + b)
        }
        //time chạy mất hơn 2000milis -> ..funny1 delay1000, funny2 delay1000 -> hơn 2000
        println("Time run = $timeRun")
    }*/
    // muốn chạy cùng lúc
    // val a = launch{...funny1}
    // (k thể tạo 2 launch chạy cùng lúc vì trả về là Job k thể thực hiện a+b)

    //giải pháp -> Async: trả về Deferred
    runBlocking {
        val timeRun = measureTimeMillis {
            val a:Deferred<Int> = async { doSimeThingFunny1() }
            val b:Deferred<Int> = async { doSomeThingfunny2() }
            println(a.await() + b.await())
        }
        println("Time run = $timeRun")
    }
}