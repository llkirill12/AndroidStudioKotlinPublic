package com.example.hermes

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import com.example.hermes.databinding.ActivityMainBinding
import com.example.pin_1.utils.EasyLock
import com.example.pin_1.utils.LockscreenHandler

class MainActivity : LockscreenHandler() {

    lateinit var binding : ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EasyLock.checkPassword(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webViewSetup()

        binding.apply {
            //open.setOnClickListener {
                drawer.openDrawer(GravityCompat.START)  // открываем левое меню
                nv.setNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.item1 -> {
                            Toast.makeText(this@MainActivity, "Аккаунт", Toast.LENGTH_SHORT).show()
                            //drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                        }
                        R.id.item2 -> {
                            Toast.makeText(this@MainActivity, "Кошельки", Toast.LENGTH_SHORT).show()
                            //drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                        }
                        R.id.item3 -> {
                            Toast.makeText(this@MainActivity, "Настройки", Toast.LENGTH_SHORT).show()
                            //drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                            nv.menu
                        }
                        R.id.item4 -> {
                            Toast.makeText(this@MainActivity, "Закрыть меню", Toast.LENGTH_SHORT).show()
                            drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                        }
                        R.id.item5 -> {
                            Toast.makeText(this@MainActivity, "Выход", Toast.LENGTH_SHORT).show()
                            drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                            System.exit(0)
                        }
                        R.id.item11 -> {
                            Toast.makeText(this@MainActivity, "Задать ПИН", Toast.LENGTH_SHORT).show()
                            EasyLock.setPassword(this@MainActivity, this@MainActivity::class.java)
                        }
                        R.id.item22 -> {
                            Toast.makeText(this@MainActivity, "Отключить ПИН", Toast.LENGTH_SHORT).show()
                            EasyLock.disablePassword(this@MainActivity, this@MainActivity::class.java)
                        }
                        /*R.id.item33 -> {
                            Toast.makeText(this@MainActivity, "item33", Toast.LENGTH_SHORT).show()
                        }
                        R.id.item44 -> {
                            Toast.makeText(this@MainActivity, "item44", Toast.LENGTH_SHORT).show()
                        }
                        R.id.item55 -> {
                            Toast.makeText(this@MainActivity, "item55", Toast.LENGTH_SHORT).show()
                        }*/
                    }
                    true
                }
                //button3.setOnClickListener {
                //    drawer.closeDrawer(GravityCompat.START) // закрываем левое меню
                //}
            //}
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup () {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            loadUrl("https://www.hermes-ltd.com/lk/aut")  // загружаем это url
            settings.javaScriptEnabled = true // включаем java script на странице
            settings.safeBrowsingEnabled = true // безопасный просмотр страниц, предупреждает о предоносном ПО
        }
    }

    override fun onBackPressed() { // переопределяем нажатие на кнопку назад
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()
    }

    fun setPass(view: View?) {
        EasyLock.setPassword(this, MainActivity::class.java)
    }

    fun changePass(view: View?) {
        EasyLock.changePassword(this, MainActivity::class.java)
    }

    fun disable(view: View?) {
        EasyLock.disablePassword(this, MainActivity::class.java)
    }

    fun checkPass(view: View?) {
        EasyLock.checkPassword(this)
    }

}