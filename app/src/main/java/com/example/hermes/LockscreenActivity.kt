package com.example.hermes

import android.content.Intent
import android.os.Build
import android.os.Bundle
//import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.hermes.interfaces.ActivityChanger
import com.example.pin_1.utils.EasyLock
import com.example.pin_1.utils.FayazSP
import com.example.pin_1.utils.FayazSP.getString
import com.example.pin_1.utils.FayazSP.init
import com.example.pin_1.utils.FayazSP.put
import com.example.pin_1.utils.LockscreenHandler


class LockscreenActivity : LockscreenHandler(), ActivityChanger {
    var tempPass = ""
    private val passButtonIds = intArrayOf(
        R.id.lbtn1,
        R.id.lbtn2,
        R.id.lbtn3,
        R.id.lbtn4,
        R.id.lbtn5,
        R.id.lbtn6,
        R.id.lbtn7,
        R.id.lbtn8,
        R.id.lbtn9,
        R.id.lbtn0
    )
    private var textViewDot: TextView? = null
    private var textViewHAHA: TextView? = null
    private var textViewForgotPassword: TextView? = null
    private var buttonTick: Button? = null
    private var imageButtonDelete: ImageButton? = null
    private var relativeLayoutBackground: RelativeLayout? = null
    private var passString = ""
    private var realPass: String? = ""
    private var status = ""

    //
    private val checkStatus = "check"
    private val setStatus = "set"
    private val setStatus1 = "set1"
    private val disableStatus = "disable"
    private val changeStatus = "change"
    private val changeStatus1 = "change1"
    private val changeStatus2 = "change2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lockscreen)
        init(this)
        realPass = password
        initViews()
        status = intent.extras!!.getString("passStatus", "check")
        if (status == setStatus) textViewHAHA!!.text = "Enter a New Password"
        if (status == disableStatus) {
            put("password", null)
            Toast.makeText(this, "Password Disabled", Toast.LENGTH_SHORT).show()
            gotoActivity()
        }
    }

    private fun initViews() {
        textViewHAHA = findViewById(R.id.haha_text)
        textViewDot = findViewById(R.id.dotText)
        textViewForgotPassword = findViewById(R.id.forgot_pass_textview)
        buttonTick = findViewById(R.id.lbtnTick)
        imageButtonDelete = findViewById(R.id.lbtnDelete)
        relativeLayoutBackground = findViewById(R.id.background_layout)

        relativeLayoutBackground?.setBackgroundColor(EasyLock.backgroundColor)
        textViewForgotPassword?.setOnClickListener(EasyLock.onClickListener)
        imageButtonDelete?.setOnClickListener(View.OnClickListener {
            if (passString.length > 0) passString = passString.substring(0, passString.length - 1)
            textViewDot?.setText(passString)
        })
        buttonTick?.setOnClickListener(View.OnClickListener {
            //
            if (status == checkStatus) {
                if (passString == realPass) {
                    finish()
                } else {
                    passString = ""
                    textViewDot?.setText(passString)
                    Toast.makeText(
                        this@LockscreenActivity,
                        "Incorrect Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (status == setStatus) {
                //
                tempPass = passString
                passString = ""
                status = setStatus1
                textViewHAHA?.setText("Confirm Password")
                textViewDot?.setText(passString)
            } else if (status == setStatus1) {
                //
                if (passString == tempPass) {
                    put("password", passString)
                    Toast.makeText(this@LockscreenActivity, "Password is set", Toast.LENGTH_SHORT)
                        .show()
                    gotoActivity()
                } else {
                    tempPass = passString
                    passString = ""
                    tempPass = ""
                    status = setStatus
                    textViewDot?.setText(passString)
                    textViewHAHA?.setText("Enter a New Password")
                    Toast.makeText(
                        this@LockscreenActivity,
                        "Please Enter a New Password Again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (status == changeStatus) {
                if (passString == realPass) {
                    tempPass = passString
                    passString = ""
                    tempPass = ""
                    status = changeStatus1
                    textViewHAHA?.setText("Enter a New Password")
                    textViewDot?.setText(passString)
                } else {
                    passString = ""
                    textViewDot?.setText(passString)
                    Toast.makeText(
                        this@LockscreenActivity,
                        "Please Enter Current Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (status == changeStatus1) {
                tempPass = passString
                passString = ""
                status = changeStatus2
                textViewHAHA?.setText("Confirm Password")
                textViewDot?.setText(passString)
            } else if (status == changeStatus2) {
                if (passString == tempPass) {
                    put("password", passString)
                    Toast.makeText(this@LockscreenActivity, "Password Changed", Toast.LENGTH_SHORT)
                        .show()
                    gotoActivity()
                } else {
                    tempPass = passString
                    passString = ""
                    tempPass = ""
                    status = changeStatus1
                    textViewDot?.setText(passString)
                    textViewHAHA?.setText("Enter a New Password")
                    Toast.makeText(
                        this@LockscreenActivity,
                        "Please Enter a New Password Again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        for (i in passButtonIds.indices) {
            val button = findViewById<Button>(passButtonIds[i])
            button.setOnClickListener {
                if (passString.length >= 4) {
                    Toast.makeText(this@LockscreenActivity, "Max 4 characters", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    passString += button.text.toString()
                }
                textViewDot?.setText(passString)
            }
        }
    }

    private val password: String?
        private get() = getString("password", null)

    private fun gotoActivity() {
        val intent = Intent(this@LockscreenActivity, classToGoAfter)

        // не запускаем второй раз MainActivity
        //startActivity(intent)
        finish()
    }

    override fun activityClass(activityClassToGo: Class<*>?) {
        classToGoAfter = activityClassToGo
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (status == "check") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity()
            } else {
                ActivityCompat.finishAffinity(this)
            }
        }
    }

    companion object {
        private var classToGoAfter: Class<*>? = null
    }
}