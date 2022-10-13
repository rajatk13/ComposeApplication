package com.app.composeapplication.second_App.viewPager.model

 const val MyArgss = "id"
 const val MyArgssName = "name"
 const val AUTHENTICATION_ROUTE = "authRoute"
 const val ROOT_ROUTE = "rootRoute"
 const val HOME_ROUTE = "homeRoute"

sealed class Screen_S(val route:String){
  //  object OnBoardingScreen : Screen_S(route = "board_screen/{$MyArgss}/{$MyArgssName}"){  or
    object OnBoardingScreen : Screen_S(route = "board_screen?id={$MyArgss}&name={$MyArgssName}"){
       //single argument
        /*fun  passId(id:Int):String{
          //  return "board_screen/$id"  or
            return this.route.replace(oldValue = "{$MyArgss}", newValue = "$id")
        }*/
        //multiple argument
        fun passnameAndId(name:String= "rajat K", id:Int=0):String{
            //return this.route.replace(oldValue = "{$MyArgssName}", newValue = "$name").replace(oldValue = "{$MyArgss}", newValue = "$id") or
            return  "board_screen?id=$id&name=$name"
        }
    }

    object MyApp_NScreen : Screen_S(route = "Splas_screen")
    object LoginScreen : Screen_S(route = "Login_screen")
    object SignUpScreen : Screen_S(route = "SignUp_screen")
    object ProfileScreen : Screen_S(route = "Profile_screen")
}
