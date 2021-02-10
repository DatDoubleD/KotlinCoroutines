package com.doanducdat.testfirstkotlincoroutines.coroutinecontext

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    //nếu time chạy quá time định trước -> lỗi
    /*runBlocking {
        withTimeout(1600){
            repeat(3){
                println("Ahihi lan $it")
                delay(500)
            }
        }
    }*/

    // ta dùng withtitimeoutornull
    runBlocking {
        val result = withTimeoutOrNull(1600) {
            repeat(10) { // lặp 10 lan nhưng chỉ chạy đúng theo 1600s quy định
                println("Ahihi lan $it")
                delay(500)
            }
            "done" // giá trị trả về khi task hoàn thành (time chạy >= time quy định result sẽ trả về done)
        }
        println("result = $result")
    }

}