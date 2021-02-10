package com.doanducdat.testfirstkotlincoroutines.coroutinecontext

import kotlinx.coroutines.*
import kotlin.concurrent.thread

//job.join
fun TestJobFirst(){
    val job:Job = GlobalScope.launch {
        delay(5000)
        println("job 1 nè")

    }
    val joi2:Job = GlobalScope.launch {
        job.join() //-> khi có dòng này: job 1 chạy xong 5s mới chạy job2(delay2s -> ...)
        delay(2000)
        println("Job 2 nè")
    }
    Thread.sleep(10000)
}

//job.cancel
fun TestJobSecond(){
    //job: nắm giữ vòng đời của coroutine
    runBlocking {
        val job = launch ( Dispatchers.Default ){
            repeat(1000){
                delay(500)
                println("I'm sleeping $it nè")
            }
        }
        delay(1500)
        job.cancel()
        println("Cancelled coroutines")
    }
}

//job.cancelAndJoin
fun TestJobThird(){
    runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            //while (i < 5) {
            while (isActive) {// check xem coroutines này đã bị hủy chưa để tiếp tục hoặc dừng hẵn
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300) // delay a bit
        println("main: I'm tired of waiting!")
       /* 1. job.cancel: hủy coroutines và chạy tiếp print (sau khi print, thì coroutine vẫn chạy)
          2. job.cancelAndJoin: hủy coroutines và đợi coroutine chạy xong sau đó mới print
          3. tại sao cả 2 khi hủy coroutines mà coroutine vẫn chạy:
         - Vì: bản chất(Cancellation is cooperative) của biến job chứa coroutines là chuyển true -> false
            coroutines muốn bị dừng hẵn phải có delay, isActive...(vì delay sẽ check xem coroutines còn sống hay k, true hay false
             để chạy tiếp hoặc dừng hẵn)*/

        job.cancelAndJoin()
        println("main: Now I can quit.")
    }
}

fun TestJobFourth(){
    runBlocking {
        val job = launch {
            try {
                repeat(1000) {
                    delay(100)
                    println("hello lần $it ")
                }
            } finally {
               /* cancel -> try đã dc cancel vì có delay,
                  nhưng finally vẫn dc chạy sau khi cancel, muốn ngừng phải thêm delay
                  (delay để check xem coroutine còn sống hay k, */
                println("print from finally")
                //delay(100) -> sẽ hủy vì check dc là job(coroutines) này đã bị cancel
                withContext(NonCancellable){
                    repeat(5){
                        delay(100) // delay ở đây nó sẽ check NHƯNG nằm trong NONCANCELLABLE -> nó sẽ chạy vì đã dc ưu tiên bất tử
                        println("bat tu k?")
                    }
                }
            }
        }
        delay(500)
        println("stop coroutine")
        job.cancel()
    }
}
fun main() {
    TestJobFourth()
}