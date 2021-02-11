package com.doanducdat.testfirstkotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.doanducdat.testfirstkotlincoroutines.coroutinecontext.TestDispatchers
import com.doanducdat.testfirstkotlincoroutines.coroutinecontext.TestWithContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var btnClick:Button ? = null
    var txtNumber:TextView ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
        //TestDispatchers.runMyFirstCoroutines()
        //TestWithContext.testMyFirstWithContext()
        //TestWithContext.testSecondWithContext()
        //TestWithContext.testThirdWithContext()
    }

    private fun addEvents() {
        btnClick?.setOnClickListener{
            GlobalScope.launch (Dispatchers.IO) {
                for (i in 0..100){
                    launch (Dispatchers.Main) {
                        txtNumber?.text = "$i%"
                    }
                    delay(100)
                }
            }
        }
    }

    private fun addControls() {
        btnClick = findViewById(R.id.btnClick)
        txtNumber = findViewById(R.id.txtNumber)
    }
}