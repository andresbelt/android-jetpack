package com.platzi.android.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.platzi.android.presentation.ui.navigation.SetupNavGraph
import com.platzi.android.presentation.ui.theme.DemoTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DemoTheme {
                val navController = rememberNavController()
                val scaffoldState: ScaffoldState = rememberScaffoldState()
                SetupNavGraph(navController = navController, scaffoldState)
            }
        }
    }
}
