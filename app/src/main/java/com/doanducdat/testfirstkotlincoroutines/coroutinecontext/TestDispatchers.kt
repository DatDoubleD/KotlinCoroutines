package com.doanducdat.testfirstkotlincoroutines.coroutinecontext

import android.util.Log
import com.doanducdat.testfirstkotlincoroutines.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

object TestDispatchers {
    fun runMyFirstCoroutines(){
        GlobalScope.launch (Dispatchers.Default){
            Log.d(MainActivity::class.java.simpleName, "Dispatchers Default run on ${Thread.currentThread().name}")
        }

        GlobalScope.launch (Dispatchers.IO){
            Log.d(MainActivity::class.java.simpleName, "Dispatchers IO run on ${Thread.currentThread().name}")
        }

        GlobalScope.launch (Dispatchers.Main){
            Log.d(MainActivity::class.java.simpleName, "Dispatchers Main run on ${Thread.currentThread().name}")
        }

        GlobalScope.launch (Dispatchers.Unconfined){
            // chay tren main Threat nhung neu bi delay se chay tren 1 thread khac
            Log.d(MainActivity::class.java.simpleName, "Dispatchers Unconfined run on ${Thread.currentThread().name}")
        }
        GlobalScope.launch (newSingleThreadContext("My thread")){
            Log.d(MainActivity::class.java.simpleName, "Dispatchers \"newSignleThreadContext\" run on ${Thread.currentThread().name}")
        }
    }
}