package com.newstore.favqs.features.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.newstore.favqs.features.main.util.KeyboardManager
import com.newstore.favqs.features.main.util.LocalKeyboardManager
import com.newstore.favqs.navigation.graph.LocalNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val keyboardManager = KeyboardManager(this)
            val onBackPressed = viewModel.onBackPressed.observeAsState().value ?: false

            if (onBackPressed) {
                navController.popBackStack()
                viewModel.resetBackPress()
            }

            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalKeyboardManager provides keyboardManager
            ) { MainHost() }
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }
}