package com.airlibs.app.features.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.airlibs.app.features.dashboard.DashBoardPage
import com.airlibs.app.features.login.LoginPage
import com.airlibs.app.features.medicine.MedicinePage
import kotlinx.serialization.Serializable


@Composable
fun AirLibsTestApp() {

    AirLibNavHost()
}

@Serializable
object Login

@Serializable
object DashBoard

@Serializable
data class Medicine( val id: Long)

@Composable
fun AirLibNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginPage(
                onLoginSuccess = {
                    navController.navigate(
                        DashBoard,
                        navOptions = NavOptions.Builder().setPopUpTo(Login, true).build()
                    )
                }
            )
        }

        composable<DashBoard> {
            DashBoardPage(
                onNavigate = {
                    navController.navigate(it)
                }
            )
        }

        composable<Medicine> {
//            backStackEntry ->
//            val medicine: Medicine = backStackEntry.toRoute()
            MedicinePage(
                onBackPressed = navController::popBackStack
            )
        }
    }

}
