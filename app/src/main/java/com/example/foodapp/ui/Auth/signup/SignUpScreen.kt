package com.example.foodapp.ui.Auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.ui.common.FoodTextFiled
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.R
import com.example.foodapp.ui.common.FoodButton
import com.example.foodapp.ui.common.Sign_in_Method
import com.example.foodapp.ui.theme.Orange

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {

    val name = remember { mutableStateOf("hassan") }

    var passwordVisible by remember { mutableStateOf(false) }
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

        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, bottom = 40.dp, top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(R.string.sign_up),
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
                    value = "",
                    onValueChange = {},
                    label = R.string.full_name,
                    placeholder = {
                        Text(text = stringResource(R.string.your_name))
                    },
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
                        },
                    )

                )

                FoodTextFiled(
                    value = "",
                    onValueChange = {},
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
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = stringResource(R.string.your_password))
                    },
                    label = R.string.password,
                    modifier = Modifier.fillMaxWidth()
                        .height(60.dp)
                        ,
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


                FoodButton(
                    onClick = {},
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
                    Text(
                        text = stringResource(R.string.sign_up).toUpperCase(Locale.current),
                        fontSize = 16.sp,
                        letterSpacing = 2.sp
                    )
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
                    text = stringResource(R.string.log_in),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Orange,
                    textDecoration = TextDecoration.None,
                    lineHeight = 16.sp
                )
            }

            Sign_in_Method(onGoogleClick = {}, onFacebookClick = {}, color = Color.Gray)
        }

    }

}


@PreviewLightDark
@Composable
fun PreviewSignUpScreen(modifier: Modifier = Modifier) {

    FoodAppTheme {
        SignUpScreen()

    }


}