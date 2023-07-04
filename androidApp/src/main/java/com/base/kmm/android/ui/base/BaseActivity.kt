package com.base.kmm.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<ViewBindingType : ViewBinding, ViewModelType : ViewModel> :
    AppCompatActivity(),
    ViewBindingHolder<ViewBindingType> by ViewBindingHolderImpl() {

    protected abstract val viewModel: ViewModelType

    protected val binding: ViewBindingType
        get() = requireBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            initBinding(
                binding = setupViewBinding(layoutInflater),
                lifecycle = lifecycle,
                className = this::class.simpleName,
                onBound = null
            )
        )
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun setupViewBinding(inflater: LayoutInflater): ViewBindingType
}