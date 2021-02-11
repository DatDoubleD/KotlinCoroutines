package com.doanducdat.testfirstkotlincoroutines.exception_handing

import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException

fun main() {
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
        job1.await()

    }
}