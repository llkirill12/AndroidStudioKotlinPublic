package com.example.pin_1.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import com.example.hermes.LockscreenActivity
import com.example.hermes.interfaces.ActivityChanger

/**
 * Created by p32929 on 7/17/2018.
 */
object EasyLock {
    private var activityChanger: ActivityChanger? = null
    var backgroundColor = Color.parseColor("#688163")
    var onClickListener: View.OnClickListener? = null
    private fun init(context: Context) {
        FayazSP.init(context)
        if (activityChanger == null) {
            activityChanger = LockscreenActivity()
        }
    }

    fun setPassword(context: Context, activityClassToGo: Class<*>?) {
        init(context)
        activityChanger?.activityClass(activityClassToGo)
        val intent = Intent(context, LockscreenActivity::class.java)
        intent.putExtra("passStatus", "set")
        context.startActivity(intent)
    }

    fun changePassword(context: Context, activityClassToGo: Class<*>?) {
        init(context)
        activityChanger?.activityClass(activityClassToGo)
        val intent = Intent(context, LockscreenActivity::class.java)
        intent.putExtra("passStatus", "change")
        context.startActivity(intent)
    }

    fun disablePassword(context: Context, activityClassToGo: Class<*>?) {
        init(context)
        activityChanger?.activityClass(activityClassToGo)
        val intent = Intent(context, LockscreenActivity::class.java)
        intent.putExtra("passStatus", "disable")
        context.startActivity(intent)
    }

    fun checkPassword(context: Context) {
        init(context)
        if (FayazSP.getString("password", null) != null) {
            val intent = Intent(context, LockscreenActivity::class.java)
            intent.putExtra("passStatus", "check")
            context.startActivity(intent)
        }
    }

    @JvmName("setBackgroundColor1")
    fun setBackgroundColor(backgroundColor: Int) {
        EasyLock.backgroundColor = backgroundColor
    }

    fun forgotPassword(onClickListener: View.OnClickListener?) {
        EasyLock.onClickListener = onClickListener
    }
}