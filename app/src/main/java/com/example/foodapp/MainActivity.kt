package com.example.foodapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.data.FoodApi
import com.example.foodapp.navigation.AuthScreen
import com.example.foodapp.navigation.Home
import com.example.foodapp.navigation.LogIn
import com.example.foodapp.navigation.SignUp
import com.example.foodapp.ui.Auth.AuthScreen
import com.example.foodapp.ui.Auth.login.LogInScreen
import com.example.foodapp.ui.Auth.login.LogInViewModel
import com.example.foodapp.ui.Auth.signup.SIgnUpViewModel
import com.example.foodapp.ui.Auth.signup.SignUpScreen
import com.example.foodapp.ui.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var splashScreen = true

    @Inject
    lateinit var foodApi: FoodApi

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreen
            }

            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(/* target = */ screen.iconView, /* property = */
                    View.SCALE_X, /* ...values = */
                    0.5f, 0f
                )

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.5f, 0f
                )

                zoomX.duration = 500
                zoomY.duration = 500
                zoomX.interpolator = OvershootInterpolator()
                zoomY.interpolator = OvershootInterpolator()
                zoomX.doOnEnd {
                    screen.remove()
                }
                zoomY.doOnEnd {
                    screen.remove()
                }
                zoomX.start()
                zoomY.start()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AuthScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<AuthScreen>() {
                            AuthScreen(navController)

                        }

                        composable<Home>() {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(text = "Home Screen")
                            }
                        }

                        composable<SignUp>() {
                            val sIgnUpViewModel: SIgnUpViewModel = hiltViewModel()
                            SignUpScreen(navController, sIgnUpViewModel)
                        }

                        composable<LogIn>() {
                            val logInViewModel: LogInViewModel = hiltViewModel()
                            LogInScreen(logInViewModel, navController)
                        }

                    }
                }

            }
        }

        if (::foodApi.isInitialized) {
            Log.d("MainActivity", "Food Api Initialized")
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            splashScreen = false
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodAppTheme {
        Greeting("Android")
    }
}