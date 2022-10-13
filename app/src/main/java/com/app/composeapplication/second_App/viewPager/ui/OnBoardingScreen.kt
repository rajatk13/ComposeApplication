package com.app.composeapplication.second_App.viewPager.ui


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.composeapplication.second_App.viewPager.model.PagerModel
import com.app.composeapplication.second_App.viewPager.model.dataList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.app.composeapplication.R
import com.app.composeapplication.second_App.viewPager.model.ModelToParse
import com.app.composeapplication.second_App.viewPager.model.Screen_S
import com.app.composeapplication.second_App.viewPager.viewModel.ShareViewModel

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    navController: NavController,
   // result: ModelToParse?,
    sharedViewModel: ShareViewModel
) {

 /*   val name by remember {
        mutableStateOf("")
    }*/
    var modelparse = sharedViewModel.person
    LaunchedEffect(key1 =modelparse ){
        if(modelparse!=null){
            Log.e( "SetupnavGraph:", modelparse?.firstName.toString()+ modelparse?.lastName.toString() )
        }
    }

     val pageState = rememberPagerState()
             HorizontalPager(count = dataList.size, state = pageState) { page->
                 PageUi( dataList[page])
             }
    Column(
         modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPagerIndicator(pagerState = pageState, modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(align = Alignment.BottomCenter), activeColor = Color.Yellow)
        Text(text = "Getting Started....${modelparse?.firstName?:""} ${modelparse?.lastName?:""}", color = Color.Red)

        AnimatedVisibility(visible = pageState.currentPage==2) {
          Column {
              Button(onClick = {  navigateTo_(navController, "back") }, modifier = Modifier.fillMaxWidth()) {
                  Text(text = "Get Back...")
              }

              Button(onClick = {  navigateTo_(navController,"forword") }, modifier = Modifier.fillMaxWidth()) {
                  Text(text = "Getting Started1...${modelparse?.firstName?:""} ${modelparse?.lastName?:""}")
              }
          }

        }
    }
}
fun navigateTo_(navController: NavController, moveMent: String){
 //   navController.popBackStack()
//    or

    if(moveMent.equals("back")){
        navController.navigate(Screen_S.MyApp_NScreen.route)
        {
            popUpTo(Screen_S.MyApp_NScreen.route){ inclusive =true}
        }
    }else  if(moveMent.equals("forword")){
        navController.navigate(Screen_S.LoginScreen.route)
    }

}

@Composable
fun PageUi(pagerModel: PagerModel){
    Image(
        painter = painterResource(id = pagerModel.image),
        contentDescription = pagerModel.description,
        modifier = Modifier.fillMaxSize()
        , contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(Color.LightGray, BlendMode.ColorBurn)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = pagerModel.description, color = Color.Yellow)
        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Preview(showBackground = true, name = "one...")
@Composable
fun PreviewS() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageUi( dataList[0])
    }

}
@Preview(showBackground = true, name = "two...")
@Composable
fun Preview2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageUi( dataList[1])
    }

}@Preview(showBackground = true, name = "three...")
@Composable
fun Preview3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageUi( dataList[2])
    }

}