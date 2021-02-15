package com.doanducdat.testfirstkotlincoroutines.exception_handing

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import java.util.logging.Handler

fun c1andc2(){
    runBlocking {
        val job = GlobalScope.launch {
            try {
                println("Throw exception from lanch")
                throw NullPointerException() // <- nó sẽ ném lỗi nên dùng try catch
            }catch (ex:NullPointerException){
                println(ex.toString())
            }
        }
        job.join()
        var job1 = GlobalScope.async {
            println("Throw excaption from async")
            throw IndexOutOfBoundsException() // <- k ném lỗi, trả về 1 deferred
        }
        //job 1 trả vế 1 deferred
        // lấy giá trị = await(), lúc này lỗi mới dc ném ra
        try{
            job1.await()
        }catch (ex: IndexOutOfBoundsException){
            println(ex.toString())
        }
    }
}

fun c3(){
    //launch -> dung handler
    //async -> k the dung launch -> phai dung trycatch
    runBlocking {
        val handler = CoroutineExceptionHandler{_, exception ->
            println("Error here, ${exception.toString()}")
        }
        val job:Job = GlobalScope.launch (handler + Dispatchers.Default){
            println("Throw exception from launch")
            throw NullPointerException()
        }
        job.join()
    }
}

fun c3vsMultiException() {
    runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Error here, ${exception.toString()}, exception was suppressed: ${exception.suppressed.contentToString()}")
        }
        var job: Job = GlobalScope.launch(handler) {
            launch {
                println("1")
                delay(300)
                throw NullPointerException("Error 1")
            }
            launch {
                println("2")
                delay(300)
                throw ArithmeticException("Error 2")

            }
            launch {
                println("3")
                delay(300)
                throw IndexOutOfBoundsException("Error 3")
            }
        }
        job.join()
    }

}

fun c4(){
    runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)){
            val job1 = launch {
                println("job 1")
                throw NullPointerException()
            }
            val job2 = launch {
                println("job 2")
                throw NullPointerException()
            }
            val job3 = launch {
                println("job 3")
                throw NullPointerException()
            }
        }
    }
}

fun main() {
    /*// c1: dung try cathc (Nhan loi) - throw( nem loi) launch
    // c2: dung await(nhan loi) - throw (nem loi) async
    //c1andc2()*/

    /*// c3: dung handler
    //c3()*/

    /*Exception aggregation
     c3 with multi exception: nhieu coroutine cung 1 loi -> handler chi xu ly loi dau tien, nhung cai
     con lai da bi suppress
     */
    //c3vsMultiException()
    //c4: dung supervisor job,
    // (job thông thường : job cha bị hủy, sẽ hủy all job con bên trong) # (supervisor job bị hủy, job con vẫn sẽ chạy tiếp)


}
