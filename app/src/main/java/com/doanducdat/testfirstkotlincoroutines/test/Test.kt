package com.doanducdat.testfirstkotlincoroutines.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
fun main() {
    // khoi tao 1 launch
    GlobalScope.launch {
        delay(2000)
        print("Hello")
    }
    print(" World ")

    //main se ket thuc khi in ra "World", can sleep de cho coroutines chay in ra "Hello"(da luong)
    // k dùng runblocking mà chạy trên main -> phải sleep
    Thread.sleep(3000)
} */
/*
fun main() {
    // it dung -> freeze main , chạy xong runBlocking mới chạy in ra "abc"
    runBlocking {
        delay(2000)
        print("Hello")
        delay(1000)
        print(" Coroutines")
    }
    print("abc")
} */
fun main() {
    var start = System.currentTimeMillis()
    runBlocking {
        repeat(1000000){
            launch {
                println("ahihi")
            }
        }
    }
    var end = System.currentTimeMillis()
    println("Time = ${end - start} ms")
}