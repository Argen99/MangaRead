package com.geektech.mangaread.presentation.ui.activities

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.geektech.data.local_db.prefs.TokenManager
import com.geektech.mangaread.R
import com.geektech.mangaread.databinding.ActivityMainBinding
import com.geektech.mangaread.core.base.BaseActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val tokenManager: TokenManager by inject()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun userVerification() {
//        if (tokenManager.getAccessToken() == null) {
//            navController.navigate(R.id.startFragment)
//        }
    }

}