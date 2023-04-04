package com.geektech.mangaread.presentation.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.mangaread.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tokenManager: TokenManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}