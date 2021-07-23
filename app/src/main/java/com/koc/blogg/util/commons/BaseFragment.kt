package com.koc.blogg.util.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException

/**
Created by kelvin_clark on 7/23/2021 10:47 AM
 */
abstract class BaseFragment<T: ViewBinding>: Fragment() {
    private var _binding: T? = null
    protected val binding
        get() = _binding!!
    protected lateinit var navController: NavController

    protected abstract fun inflateBinding(layoutInflater: LayoutInflater, viewGroup: ViewGroup?): T

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return _binding?.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            navController = findNavController()
        }catch (exception: IllegalStateException) {
            exception.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}