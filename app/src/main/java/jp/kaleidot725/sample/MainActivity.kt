package jp.kaleidot725.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.kaleidot725.sample.ui.navigation.Screen
import jp.kaleidot725.sample.ui.sample.*
import jp.kaleidot725.sample.ui.theme.SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) {
                        NavList(navController)
                    }
                    composable(Screen.LaunchedEffectCounter.route) {
                        LaunchedEffectSampleCounter()
                    }
                    composable(Screen.LaunchedEffectToggle.route) {
                        LaunchedEffectSampleToggle()
                    }
                    composable(Screen.DisposableEffectToggle.route) {
                        DisposableEffectSampleToggle()
                    }
                    composable(Screen.DisposableEffectRefresh.route) {
                        DisposableEffectSampleRefresh()
                    }
                    composable(Screen.RememberCoroutineScopeLaunch.route) {
                        RememberCoroutineScopeLaunchSample()
                    }
                    composable(Screen.RememberUpdateState.route) {
                        RememberUpdateStateSample()
                    }
                    composable(Screen.SideEffect.route) {
                        SideEffectSample()
                    }
                    composable(Screen.DerivedStateOfSample.route) {
                        DerivedStateOfSample()
                    }
                    composable(Screen.SnapshotFlowSample.route) {
                        SnapshotFlowSample()
                    }
                }
            }
        }
    }
}

@Composable
private fun NavList(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Screen.SAMPLE_SCREENS.forEach { screen ->
                Button(
                    onClick = { navController.navigate(screen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = screen.title)
                }
            }
        }
    }
}
