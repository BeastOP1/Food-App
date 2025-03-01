package com.example.foodapp.ui.Auth.login

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.foodapp.R
import com.example.foodapp.navigation.AuthScreen
import com.example.foodapp.navigation.Home
import com.example.foodapp.navigation.SignUp
import com.example.foodapp.ui.common.FoodButton
import com.example.foodapp.ui.common.FoodTextFiled
import com.example.foodapp.ui.common.Sign_in_Method
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.ui.theme.Orange
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LogInScreen(logInViewModel: LogInViewModel, navController: NavHostController) {
    val email = logInViewModel.email.collectAsStateWithLifecycle()
    val password = logInViewModel.password.collectAsStateWithLifecycle()
    val uiState = logInViewModel.uiState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val isError = remember { mutableStateOf<String?>(null) }
    when (uiState.value) {
        LogInViewModel.LogInStatus.Failure -> {
            isError.value = "Failed"
            isLoading.value = false
        }

        LogInViewModel.LogInStatus.Loading -> {
            isError.value = ""
            isLoading.value = true
        }

        LogInViewModel.LogInStatus.Success -> {
            isError.value = ""
            isLoading.value = false
        }

        else -> {
            isError.value = ""
            isLoading.value = false

        }
    }

    LaunchedEffect(true) {
        logInViewModel.navigationEvent.collectLatest {
            when (it) {
                LogInViewModel.LogInNavigation.NavigateToHome -> {
                    navController.navigate(Home) {
                        popUpTo(AuthScreen) {
                            inclusive = true
                        }
                    }
                    Toast.makeText(context, "LogIn SuccessFull", Toast.LENGTH_SHORT).show()
                }

                LogInViewModel.LogInNavigation.NavigateToSignUp -> {
                    navController.navigate(SignUp)
                }

                else -> {
                    Toast.makeText(context, "LogIN Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            painter = painterResource(R.drawable.auth_back),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier
            .padding(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .size(45.dp)
            .background(Color.White)
            .clickable(
                role = Role.Button,
                onClick = {
                    navController.navigateUp()
                },
                indication = rememberRipple(
                    bounded = true,
                    radius = 25.dp
                ),
                interactionSource = remember { MutableInteractionSource() }
            ), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )


        }

        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, bottom = 40.dp, top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.log_in),
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FoodTextFiled(
                    value = email.value,
                    onValueChange = {
                        logInViewModel.onEmailChange(it)

                    },
                    placeholder = {
                        Text(text = stringResource(R.string.your_email_or_phone))
                    },
                    label = R.string.e_mail,

                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            this.defaultKeyboardAction(imeAction = ImeAction.Next)
                        }
                    )
                )


                FoodTextFiled(
                    value = password.value,
                    onValueChange = {
                        logInViewModel.onPasswordChange(it)
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.password))
                    },
                    label = R.string.password,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            this.defaultKeyboardAction(imeAction = ImeAction.Done)
                        },
                    ),
                    trailingIcon = {
                        val icon =
                            if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color.LightGray
                            )
                        }
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Text(
                    text = stringResource(R.string.forgot_password),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Orange,
                    textDecoration = TextDecoration.None,
                    lineHeight = 16.sp
                )
                FoodButton(
                    onClick = logInViewModel::onLogInClick,
                    modifier = Modifier
                        .width(240.dp)
                        .height(60.dp),
                    contentPadding = PaddingValues(
                        vertical = 5.dp,
                        horizontal = 5.dp
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    AnimatedContent(targetState = isLoading.value, label = "", transitionSpec = {
                        fadeIn(tween(1000)) togetherWith fadeOut(tween(1000))
                    }) {
                        if (it) {
                            CircularProgressIndicator(
                                color = Color.Yellow,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {

                            Text(
                                text = stringResource(R.string.log_in).toUpperCase(Locale.current),
                                fontSize = 16.sp,
                                modifier = Modifier.clickable(enabled = true, onClick = {
                                    navController.navigate(
                                        SignUp
                                    )
                                }),
                                letterSpacing = 2.sp
                            )
                        }
                    }
                }


            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = stringResource(R.string.sign_up),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Orange,
                    textDecoration = TextDecoration.None,
                    lineHeight = 16.sp
                )
            }

            Sign_in_Method(onGoogleClick = {
                logInViewModel.onGoogleSignInClicked(context)
            }, onFacebookClick = {}, color = Color.Gray)
        }

    }

}


@Composable
fun PreviewLogInScreen(modifier: Modifier = Modifier) {

    FoodAppTheme {
//        LogInScreen(navController = navController)
    }
}