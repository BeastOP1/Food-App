package com.example.foodapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.R

@Composable
fun Sign_in_Method(
    color: Color = Color.White,
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit,
) {


    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.sign_in_with),
                color = color,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500
            )
            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            SocialButton(
                modifier = Modifier,
                imageResource = R.drawable.fb_logo,
                buttonTextRes = R.string.facebook,
                onClick = onFacebookClick
            )

            SocialButton(
                modifier = Modifier,
                imageResource = R.drawable.google_logo,
                buttonTextRes = R.string.google,
                buttonPaddingValues = PaddingValues(
                    start = 12.dp,
                    top = 14.dp,
                    end = 24.dp,
                    bottom = 14.dp
                ),

                onClick = onGoogleClick

            )
        }
    }

}