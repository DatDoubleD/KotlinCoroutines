package com.doanducdat.testfirstkotlincoroutines.coroutines_scope

import kotlinx.coroutines.*

fun main() {
    // muốn chạy hết tác vụ khi coroutine cha bị hủy -> dùng globlesope
    runBlocking {
       var job:Job = launch {
            GlobalScope.launch {
                delay(100)
                println("lần 1")
                delay(1000)
                println("lần 2")
                delay(2000)
                println("lần 3")
            }
        }
        delay(500)
        job.cancel()
        delay(1000) // muốn task chạy xong dù cancel
        //-> DELAY ĐỦ TIME CHO TASK(job) CHẠY SAU KHI CANCEL
        // PHẢI DÙNG GlobalScope ĐỂ CHẠY

    }


}