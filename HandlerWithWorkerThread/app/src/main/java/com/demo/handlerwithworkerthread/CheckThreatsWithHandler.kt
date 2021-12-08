package com.demo.handlerwithworkerthread

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import com.demo.handlerwithworkerthread.MainActivity.Companion.printCurrentThread
import com.scottyab.rootbeer.RootBeer
import java.lang.ref.WeakReference
import java.util.concurrent.CountDownLatch

class CheckThreatsWithHandler(context: Context) {

    var handlerThread = HandlerThread("ThreatDetector")
    var handler: Handler? = null

    private var mContext = context
    private var rootBeer = RootBeer(mContext)
    private var latch = CountDownLatch(10)

    private val SLEEP_TIME = 3000L

    private fun createHandler() {
        if (handler == null) {
            if (handlerThread.state == Thread.State.NEW)
                handlerThread.start()
            else if (handlerThread.state == Thread.State.TERMINATED) {
                handlerThread = HandlerThread("ThreatDetector")
                handlerThread.start()
            }

            handler = Handler(handlerThread.looper)
        }
    }

    private fun threadCleanup(){
        handlerThread?.quit()
        handler = null
    }

    fun postRunnable(runnalble: Runnable) {
        handler?.post(runnalble)
    }

    fun checkThreats() : Boolean {
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

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            detectRootManagementApps = rootBeer.detectRootManagementApps() == true
            Log.d(MainActivity.TAG, "detectRootManagementApps result is: "+detectRootManagementApps)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            detectPotentiallyDangerousApps = rootBeer.detectPotentiallyDangerousApps() == true
            Log.d(MainActivity.TAG, "detectPotentiallyDangerousApps result is: "+detectPotentiallyDangerousApps)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            detectRootCloakingApps = rootBeer.detectRootCloakingApps() == true
            Log.d(MainActivity.TAG, "detectRootCloakingApps result is: "+detectRootCloakingApps)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForSuBinary = rootBeer.checkForSuBinary() == true
            Log.d(MainActivity.TAG, "checkForSuBinary result is: "+checkForSuBinary)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkSuExists = rootBeer.checkSuExists() == true
            Log.d(MainActivity.TAG, "checkSuExists result is: "+checkSuExists)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForBusyBoxBinary = rootBeer.checkForBusyBoxBinary() == true
            Log.d(MainActivity.TAG, "checkSuExists result is: "+checkSuExists)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForRWPaths = rootBeer.checkForRWPaths() == true
            Log.d(MainActivity.TAG, "checkForRWPaths result is: "+checkForRWPaths)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForDangerousProps = rootBeer.checkForDangerousProps() == true
            Log.d(MainActivity.TAG, "checkForDangerousProps result is: "+checkForDangerousProps)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForRootNative = rootBeer.checkForRootNative() == true
            Log.d(MainActivity.TAG, "checkForRootNative result is: "+checkForRootNative)
            printCurrentThread()
            latch.countDown()
        })

        postRunnable(Runnable {
            Thread.sleep(SLEEP_TIME)
            checkForMagiskBinary = rootBeer.checkForMagiskBinary() == true
            Log.d(MainActivity.TAG, "checkForMagiskBinary result is: "+checkForMagiskBinary)
            printCurrentThread()
            latch.countDown()
        })

        Log.d(MainActivity.TAG, "Waiting for thread to be released")

        latch.await()
        threadCleanup()

        Log.d(MainActivity.TAG, "Thread released")

        return detectRootManagementApps || detectPotentiallyDangerousApps
                || detectRootCloakingApps || detectRootCloakingApps || checkForSuBinary ||
                checkSuExists || checkForRWPaths || checkForDangerousProps ||
                checkForRootNative || checkForMagiskBinary || checkForBusyBoxBinary
    }

    init {
        createHandler()
    }


}