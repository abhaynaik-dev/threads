package com.demo.threadexecutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printCurrentThread()
        val threatsObj = CheckThreatWithExecutor(this)
        Log.d(TAG, "[RootBear] is device rooted -"+threatsObj.checkThreats())
        printCurrentThread()
    }

    companion object{
        const val TAG = "ThreadExecutor"

        fun printCurrentThread(){
            Log.d(TAG, "Thread name: "+Thread.currentThread().name)
            Log.d(TAG, "Is a main thread: "+ (Looper.myLooper() == Looper.getMainLooper()))
            Log.d(TAG, "---------------------------------------------------")
        }
    }
}