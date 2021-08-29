package com.example.pin_1.utils

import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.ComponentCallbacks2
import android.content.ComponentName
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pin_1.utils.EasyLock.checkPassword


/**
 * Created by p32929 on 11/16/17.
 */
open class LockscreenHandler : AppCompatActivity(), ComponentCallbacks2 {

    private val TAG = "Fayaz"

    override fun onTrimMemory(i: Int) {
        var packageName: String = ""
        val am = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val taskInfo = am.getRunningTasks(1)
        Log.d(
            "topActivity", "CURRENT Activity ::"
                    + taskInfo!![0].topActivity!!.className
        )
        if (taskInfo != null && taskInfo.size > 0) {
            val componentInfo = taskInfo[0].topActivity
            packageName = componentInfo!!.packageName
        }
        if (packageName != getPackageName() && i == TRIM_MEMORY_UI_HIDDEN) {
            // We're in the Background
            wentToBg = true
            Log.d(TAG, "wentToBg: " + wentToBg)
        }
    }

    override fun onResume() {
        super.onResume()
        if (wentToBg && FayazSP.getString("password", null) != null) {
            // We're in the foreground & password != null
            wentToBg = false
            Log.d(TAG, "wentToBg: " + wentToBg)
            checkPassword(this)
        }
    }

    companion object {
        private var wentToBg = false
    }
}