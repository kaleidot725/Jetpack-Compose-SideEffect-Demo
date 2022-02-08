package jp.kaleidot725.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.sample.ui.sample.Menus
import jp.kaleidot725.sample.ui.theme.SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "menus") {
                    composable("menus") {
                        Menus(
                            onNavigateCounter = { navController.navigate("counterSample") },
                            onNavigateToggle = { navController.navigate("toggleSample") }
                        )
                    }
                    composable("counterSample") {
                        Counter()
                    }
                    composable("toggleSample") {
                        Toggle()
                    }
                }
            }
        }
    }
}
