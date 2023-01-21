package com.geektech.mangaread.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.geektech.mangaread.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _navController: NavController? = null
    lateinit var navController: NavController
    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        _navController = navHostFragment.navController
        navController = _navController as NavController

        initialize()
        userVerification()
        addOnDestinationChangedListener()

    }
    protected fun navigate(direction: Int, data: Bundle? = null) {
        _navController?.navigate(direction, data)
    }

    protected fun navigateUp() {
        _navController?.navigateUp()
    }

    protected open fun initialize() {}
    protected open fun userVerification() {}
    protected open fun addOnDestinationChangedListener() {}

}