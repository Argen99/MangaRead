package com.geektech.mangaread.presentation.ui.activities

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.ActivityMainBinding
import com.geektech.mangaread.core.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initialize() {
//        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }

    override fun userVerification() {
        navController.navigate(R.id.mainFragment)
    }

    override fun addOnDestinationChangedListener() {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.authorizationFragment, R.id.startFragment -> {
//                    binding.bottomNav.visibility = View.GONE
//                }
//                else -> {
//                    binding.bottomNav.visibility = View.VISIBLE
//                }
//            }
//        }
    }
}