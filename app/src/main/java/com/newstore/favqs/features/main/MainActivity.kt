package com.newstore.favqs.features.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.newstore.favqs.coroutines.collectAsStateValue
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

            val exitRequest = viewModel.onExitRequest.collectAsStateValue()
            LaunchedEffect(exitRequest) {
                if (true == exitRequest) finishAffinity().run { viewModel.resetExitRequest() }
            }

            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalKeyboardManager provides keyboardManager
            ) { MainHost(viewModel) }
        }
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }
}