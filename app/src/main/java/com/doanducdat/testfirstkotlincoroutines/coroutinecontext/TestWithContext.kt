package com.doanducdat.testfirstkotlincoroutines.coroutinecontext

import android.util.Log
import com.doanducdat.testfirstkotlincoroutines.MainActivity
import kotlinx.coroutines.*

object TestWithContext {
    //chuyển các code trong withcontext(Thread chỉ định) chạy sang 1 Thread khác dc chỉ định
    fun testMyFirstWithContext(){
        newSingleThreadContext("Thread 1").use { context1->
            Log.d(MainActivity::class.java.simpleName, "context 1 - Thread: ${Thread.currentThread().name} ")

            newSingleThreadContext("Thread 2").use { context2->
                Log.d(MainActivity::class.java.simpleName, "context 2 -> Thread: ${Thread.currentThread().name}")

                runBlocking (context1){
                    Log.d(MainActivity::class.java.simpleName, "working in context 1 -> Thread: ${Thread.currentThread().name}")

                    withContext(context2){
                        Log.d(MainActivity::class.java.simpleName, "working in context 2 -> Thread: ${Thread.currentThread().name}")
                    }

                    Log.d(MainActivity::class.java.simpleName, "back on context 1 -> Thread: ${Thread.currentThread().name}")
                }
            }
        }
    }

    fun testSecondWithContext(){
        GlobalScope.launch (Dispatchers.IO) {
            //run longtime task in IO
            Log.d(MainActivity::class.java.simpleName, "Chạy mất 2s nè: ${Thread.currentThread().name} ")
            //ví dụ mất 2s
            delay(2000)
            //sau đó update lên UI trong Main (dùng withcontext để chạy "Main" trong Thread "IO")
            withContext(Dispatchers.Main){
                Log.d(MainActivity::class.java.simpleName, "sau đó update UI nè!: ${Thread.currentThread().name} ")
            }
        }
    }
    fun testThirdWithContext(){
        GlobalScope.launch ( Dispatchers.IO ){
            withContext(Dispatchers.Main){
                Log.d("TestThirdWithContext", "Loading... 0% ${Thread.currentThread().name}")
            }
            var a = 0; var b = 100
            for (i in a..b){
                withContext(Dispatchers.Main){
                    Log.d("TestThirdWithContext", "Loading... $i% ${Thread.currentThread().name}")
                }
            }
        }
    }
}