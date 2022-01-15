package com.demo.coroutines

import android.content.Context
import android.util.Log
import com.demo.coroutines.MainActivity.Companion.printCurrentThread
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

class CheckThreatsWithCoroutines(context: Context) {

    private var rootBeer = RootBeer(context)

    private val SLEEP_TIME = 3000L

    suspend fun checkRootThreatsParallel(): Boolean = coroutineScope {

        val detectRootManagementApps = async {
            delay(SLEEP_TIME)
            rootBeer.detectRootManagementApps()
        }
        val detectPotentiallyDangerousApps = async {
            delay(SLEEP_TIME)
            rootBeer.detectPotentiallyDangerousApps()
        }
        val detectRootCloakingApps = async {
            delay(SLEEP_TIME)
            rootBeer.detectRootCloakingApps()
        }
        val checkForBusyBoxBinary = async {
            delay(SLEEP_TIME)
            rootBeer.checkForBusyBoxBinary()
        }
        val checkForSuBinary = async {
            delay(SLEEP_TIME)
            rootBeer.checkForSuBinary()
        }
        val checkSuExists = async {
            delay(SLEEP_TIME)
            rootBeer.checkSuExists()
        }
        val checkForRWPaths = async {
            delay(SLEEP_TIME)
            rootBeer.checkForRWPaths()
        }
        val checkForDangerousProps = async {
            delay(SLEEP_TIME)
            rootBeer.checkForDangerousProps()
        }
        val checkForRootNative = async {
            delay(SLEEP_TIME)
            rootBeer.checkForRootNative()
        }
        val checkForMagiskBinary = async {
            delay(SLEEP_TIME)
            rootBeer.checkForMagiskBinary()
        }

        detectRootManagementApps.await() || detectPotentiallyDangerousApps.await()
                || detectRootCloakingApps.await() || detectRootCloakingApps.await() || checkForSuBinary.await() ||
                checkSuExists.await() || checkForRWPaths.await() || checkForDangerousProps.await() ||
                checkForRootNative.await() || checkForMagiskBinary.await() || checkForBusyBoxBinary.await()
    }

    suspend fun checkRootThreatInSequence(): Boolean = coroutineScope{
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

        delay(SLEEP_TIME)
        detectRootManagementApps = rootBeer.detectRootManagementApps()
        detectPotentiallyDangerousApps = rootBeer.detectPotentiallyDangerousApps()
        detectRootCloakingApps = rootBeer.detectRootCloakingApps()
        delay(SLEEP_TIME)
        checkForSuBinary = rootBeer.checkForSuBinary()
        checkSuExists = rootBeer.checkSuExists()
        checkForBusyBoxBinary = rootBeer.checkForBusyBoxBinary()
        delay(SLEEP_TIME)
        checkForRWPaths = rootBeer.checkForRWPaths()
        checkForDangerousProps = rootBeer.checkForDangerousProps()
        checkForRootNative = rootBeer.checkForRootNative()
        delay(SLEEP_TIME)
        checkForMagiskBinary = rootBeer.checkForMagiskBinary()

        detectRootManagementApps || detectPotentiallyDangerousApps
                || detectRootCloakingApps || detectRootCloakingApps || checkForSuBinary ||
                checkSuExists || checkForRWPaths || checkForDangerousProps ||
                checkForRootNative || checkForMagiskBinary || checkForBusyBoxBinary
    }

}