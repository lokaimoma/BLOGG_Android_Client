package com.koc.blogg.views.splashScreen

import android.os.Bundle
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.koc.blogg.R
import com.koc.blogg.databinding.FragmentSplashScreenBinding
import com.koc.blogg.util.PreferenceManager
import com.koc.blogg.util.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIfUserHasLogin()
    }

    private fun checkIfUserHasLogin()= viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        preferenceManager.userId.first {userId ->
            if (userId == null) {
                navController.navigate(R.id.splash_screen_to_login_fragment)
            }
            // else navigate to blog screen
            true
        }
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSplashScreenBinding =
        FragmentSplashScreenBinding.inflate(layoutInflater, viewGroup, false)
}