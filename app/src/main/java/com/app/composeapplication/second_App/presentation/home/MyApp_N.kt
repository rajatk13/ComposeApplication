package com.app.composeapplication.second_App.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.app.composeapplication.second_App.animation.LoadingAnimation
import com.app.composeapplication.second_App.viewPager.model.ModelToParse
import com.app.composeapplication.second_App.viewPager.model.Screen_S
import com.app.composeapplication.second_App.viewPager.viewModel.ShareViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun MyApp_NScreen(navController: NavController, sharedViewModel: ShareViewModel) {
    Scaffold{

        Box(modifier = Modifier.padding(it))   {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .wrapContentSize(align = Alignment.Center)
            )

            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = "main background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                colorFilter = ColorFilter.tint(Color.DarkGray, BlendMode.ColorBurn)
            )

            Column(modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                LoadingAnimation()
            }
            Column(modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 80.dp)
                .fillMaxSize())
            {
                Text(
                    text = "New projects..",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.65f))
                Card(elevation = 4.dp, modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(0.6f),
                        shape = RoundedCornerShape(27.dp)
                    )
                    .clip(RoundedCornerShape(27.dp)))
                {
                    Image(
                        painter = painterResource(id = R.drawable.flag_bck),
                        contentDescription = "Card Background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "New projects Started..",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "New projects Started..Now Apis and Developer Required",
                            color = Color.White.copy(0.7f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                val modelToParse = ModelToParse("Rajat","kumar modelToParse..")
                             //   navController.currentBackStackEntry?.savedStateHandle?.set(key = "modelToParse", value = modelToParse) or
                                sharedViewModel.addModelToParse(modelToParse)
                            navController.navigate(
                             //   route = Screen_S.OnBoardingScreen.passnameAndId("rajat",30) )    or                               },
                                route = Screen_S.OnBoardingScreen.passnameAndId() )                                   },
//                             navController.navigate(route = "board_screen/" + 33)                                   },
                                //navController.navigate(route = Screen_S.OnBoardingScreen.route+"/"+1)                                   },
                            shape = RoundedCornerShape(percent = 50),
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = Color.DarkGray.copy(0.4f),
                                shape = RoundedCornerShape(percent = 50)
                            ),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(151, 169, 246, 0x32)
                                , contentColor = Color.DarkGray),

                            ) {


                            Icon(
                                imageVector = Icons.Default.Grass,
                                modifier = Modifier
                                    .size(18.dp)
                                ,
                                contentDescription = "drawable icons",
                                tint = Color.Unspecified
                            )
                            Text(text = "Get Started now",modifier = Modifier.padding(
                                horizontal  = 20.dp,
                                vertical = 5.dp,
                            ), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }

            }
        }
    }
}



@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
       MyApp_NScreen(navController = rememberNavController(), sharedViewModel = ShareViewModel())
    }

}