package com.geektech.mangaread.presentation.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tokenManager: TokenManager by inject()
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        when {
            tokenManager.getAccessToken() == null -> {
                navGraph.setStartDestination(R.id.authFlowFragment)
            }
            tokenManager.getAccessToken() != null -> {
                navGraph.setStartDestination(R.id.mainFlowFragment)
            }
        }
        navController.graph = navGraph
    }
}