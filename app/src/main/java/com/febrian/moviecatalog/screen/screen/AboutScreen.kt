package com.febrian.moviecatalog.screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.febrian.moviecatalog.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "About Me",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(
                    start = 24.dp, top = 16.dp
                )
                .fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(42.dp))

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(128.dp),
            alignment = Alignment.Center
        )

        Text(
            text = "M. Dwi Febrian",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = "Email : febrian26022001@gmail.com",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        Text(
            text = "Github : https://github.com/FebrianDev/",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

        Text(
            text = "Linkedin : https://www.linkedin.com/in/md-febrian/",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        )

    }
}