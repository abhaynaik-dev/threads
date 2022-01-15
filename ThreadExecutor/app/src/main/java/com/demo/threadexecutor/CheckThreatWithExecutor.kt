package com.demo.threadexecutor

import android.content.Context
import android.os.Looper
import android.util.Log
import com.demo.threadexecutor.MainActivity.Companion.printCurrentThread
import com.scottyab.rootbeer.RootBeer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

class CheckThreatWithExecutor(context: Context) {
    private var mContext = context
    private var rootBeer = RootBeer(mContext)
    private var latch = CountDownLatch(10)

    private val SLEEP_TIME = 3000L


    fun checkThreats(): Boolean{
        val threadPool = createExecutorThreat()

        var detectRootManagementApps = false
        var detectPotentiallyDangerousApps = false
        var detectRootCloakingApps = false
        var checkForBusyBoxBinary = false
        var checkForSuBinary = false
        var checkSuExists = false
        var checkForRWPaths = false
        var checkForDangerousProps = false
        var checkForRootNative = false
        var checkForMagiskBinary = false

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            detectRootManagementApps = rootBeer.detectRootManagementApps()
            Log.d(MainActivity.TAG, "detectRootManagementApps result is: "+detectRootManagementApps)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            detectPotentiallyDangerousApps = rootBeer.detectPotentiallyDangerousApps()
            Log.d(MainActivity.TAG, "detectPotentiallyDangerousApps result is: "+detectPotentiallyDangerousApps)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            detectRootCloakingApps = rootBeer.detectRootCloakingApps()
            Log.d(MainActivity.TAG, "detectRootCloakingApps result is: "+detectRootCloakingApps)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForSuBinary = rootBeer.checkForSuBinary()
            Log.d(MainActivity.TAG, "checkForSuBinary result is: "+checkForSuBinary)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkSuExists = rootBeer.checkSuExists()
            Log.d(MainActivity.TAG, "checkSuExists result is: "+checkSuExists)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForBusyBoxBinary = rootBeer.checkForBusyBoxBinary()
            Log.d(MainActivity.TAG, "checkSuExists result is: "+checkSuExists)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForRWPaths = rootBeer.checkForRWPaths()
            Log.d(MainActivity.TAG, "checkForRWPaths result is: "+checkForRWPaths)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForDangerousProps = rootBeer.checkForDangerousProps()
            Log.d(MainActivity.TAG, "checkForDangerousProps result is: "+checkForDangerousProps)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForRootNative = rootBeer.checkForRootNative()
            Log.d(MainActivity.TAG, "checkForRootNative result is: "+checkForRootNative)
            printCurrentThread()
            latch.countDown()
        }

        threadPool.execute {
            Thread.sleep(SLEEP_TIME)
            checkForMagiskBinary = rootBeer.checkForMagiskBinary()
            Log.d(MainActivity.TAG, "checkForMagiskBinary result is: "+checkForMagiskBinary)
            printCurrentThread()
            latch.countDown()
        }

        Log.d(MainActivity.TAG, "Waiting for thread to be released")

        latch.await()

        Log.d(MainActivity.TAG, "Thread released")

        return detectRootManagementApps || detectPotentiallyDangerousApps
                || detectRootCloakingApps || detectRootCloakingApps || checkForSuBinary ||
                checkSuExists || checkForRWPaths || checkForDangerousProps ||
                checkForRootNative || checkForMagiskBinary || checkForBusyBoxBinary
    }

    private fun createExecutorThreat(): ExecutorService {
        // This will add the threads as instantiated
        //val threadPool = Executors.newCachedThreadPool()

        // This will perform all the task on single thread
        //val threadPool = Executors.newSingleThreadExecutor()

        // This will perform the task parallel on N threads i.e newFixedThreadPool(N)
        val threadPool = Executors.newFixedThreadPool(2)

        return threadPool
    }


}