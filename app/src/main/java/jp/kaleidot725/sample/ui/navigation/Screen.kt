package jp.kaleidot725.sample.ui.navigation

sealed class Screen(val route: String, val title: String) {
    object Home : Screen(route = "home", title = "HOME")
    object LaunchedEffectCounter: Screen(route = "launchedEffectCounter", title = "Launched Effect Sample (Counter)")
    object LaunchedEffectToggle: Screen(route= "launchedEffectToggle", title = "Launched Effect Sample (Toggle)")
    object DisposableEffect: Screen(route = "disposableEffect", title = "Disposable Effect Sample")

    companion object {
        val SAMPLE_SCREENS = listOf(LaunchedEffectCounter, LaunchedEffectToggle, DisposableEffect)
    }
}