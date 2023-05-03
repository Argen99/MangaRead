package com.geektech.mangaread.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.geektech.mangaread.core.ui_state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : Fragment(layoutRes) {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkInternetConnection()
        initViewModel()
        initialize()
        setupRequest()
        checkArguments()
        performListeners()
        setupObservers()
        setupClickListeners()
    }

    protected open fun checkInternetConnection() {}
    protected open fun initViewModel() {}
    protected open fun initialize() {}
    protected open fun checkArguments() {}
    protected open fun performListeners() {}
    protected open fun setupRequest() {}
    protected open fun setupObservers() {}
    protected open fun setupClickListeners() {}

    protected fun <T> StateFlow<UIState<T>>.collectState(
        onLoading: () -> Unit,
        onError: (message: String) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            onLoading()
                        }
                        is UIState.Error -> {
                            onError(state.message)
                        }
                        is UIState.Success -> {
                            onSuccess(state.data)
                        }
                        is UIState.Empty -> {}
                    }
                }
            }
        }
    }

    protected fun <T : Any> Flow<PagingData<T>>.spectatePaging(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: suspend (data: PagingData<T>) -> Unit,
    ) {
        safeFlowGather(lifecycleState) {
            collectLatest {
                success(it)
            }
        }
    }

    private fun safeFlowGather(
        lifecycleState: Lifecycle.State,
        gather: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                gather()
            }
        }
    }
}