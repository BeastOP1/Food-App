package com.example.foodapp.ui.Auth

import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.R
import com.example.foodapp.ui.common.Sign_in_Method
import com.example.foodapp.ui.theme.Orange

@Composable
fun AuthScreen(modifier: Modifier = Modifier, it: PaddingValues) {

    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }

    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
        ),
        startY = imageSize.value.height.toFloat() / 4
    )
    Box(
        modifier = Modifier.fillMaxSize().padding(it)
    ) {
        Image(
            painter = painterResource(R.drawable.authback),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .onGloballyPositioned {
                    imageSize.value = it.size
                }
                .alpha(0.7f)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.BottomCenter)
                .background(brush)
        )

        Button(
            onClick = {
            },
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.TopEnd),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = CircleShape
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = Orange,
                modifier = Modifier.wrapContentWidth(),
                lineHeight = 5.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.welcome_to),
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = stringResource(R.string.foodhub),
                fontSize = 48.sp,
                fontWeight = FontWeight.SemiBold,
                color = Orange
            )

            Text(
                text = stringResource(R.string.description),
                fontSize = 19.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(0.85f).compositeOver(Color.White.copy(0.55f)),
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp, vertical = 45.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sign_in_Method(onGoogleClick = {}, onFacebookClick = {})

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray.copy(alpha = 0.6f)
                ),
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape,
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 16.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.start_with_email_or_phone),
                    color = Color.White,
                    fontSize = 17.sp,
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    lineHeight = 20.sp
                )
            }


        }


    }
}


@PreviewLightDark
@Composable
fun AuthPreview(modifier: Modifier = Modifier) {

    FoodAppTheme {
//        AuthScreen()
    }
}