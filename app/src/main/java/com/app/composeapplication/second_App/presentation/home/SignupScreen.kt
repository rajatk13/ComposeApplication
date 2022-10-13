package com.app.composeapplication.second_App.presentation.home

import android.graphics.fonts.Font
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.composeapplication.R
import com.app.composeapplication.second_App.viewPager.model.HOME_ROUTE
import com.app.composeapplication.second_App.viewPager.model.Screen_S

@Composable
fun SignupScreen(navController: NavController){
    Box{
        Image(
            painter = painterResource(id = R.drawable.export_data),
            contentDescription = "content",
            modifier = Modifier.fillMaxSize()
            , contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.LightGray, BlendMode.ColorBurn)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           /* Text(
                text = "New projects..", color = Color.White, fontWeight = FontWeight.Bold,
                fontSize = 36.sp, textAlign = TextAlign.Center)*/
            Text(text = "SignUp Page...", color = Color.Green, fontWeight = FontWeight.Bold, fontSize =22.sp,
                modifier = Modifier.clickable {
                    navController.navigate(route = Screen_S.LoginScreen.route)
                })
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Get back...", color = Color.Black, fontWeight = FontWeight.Bold, fontSize =22.sp,
                modifier = Modifier.clickable {
                    navController.navigate(route = HOME_ROUTE){
                        popUpTo(HOME_ROUTE)
                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}

@Preview(showBackground = true, name = "signup...")
@Composable
fun Preview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignupScreen(navController = rememberNavController())
    }

}