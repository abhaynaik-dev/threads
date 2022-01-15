package com.demo.coroutines

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CoroutineThreadExample"
        fun printCurrentThread() {
            Log.d(TAG, "Thread name: " + Thread.currentThread().name)
            Log.d(TAG, "Is a main thread: " + (Looper.myLooper() == Looper.getMainLooper()))
            Log.d(TAG, "---------------------------------------------------")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val threatsObj = CheckThreatsWithCoroutines(this)
        //Run root threat check in parallel
        lifecycleScope.launch {
            printCurrentThread()
            Log.d(TAG, "[RootBeer] is device rooted- " + threatsObj.checkRootThreatsParallel())
        }
    }
}