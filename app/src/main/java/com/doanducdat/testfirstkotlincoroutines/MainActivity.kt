package com.doanducdat.testfirstkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doanducdat.testfirstkotlincoroutines.coroutinecontext.TestDispatchers
import com.doanducdat.testfirstkotlincoroutines.coroutinecontext.TestWithContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TestDispatchers.runMyFirstCoroutines()
        //TestWithContext.testMyFirstWithContext()
        //TestWithContext.testSecondWithContext()
        TestWithContext.testThirdWithContext()
    }
}