package com.example.hermes

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.hermes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webViewSetup()

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
}