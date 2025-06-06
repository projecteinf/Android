package cat.mba.noactivity.features.common.screen.library

fun getTopBarTitle(currentRoute: String?): String? {
    return when (currentRoute) {
        "home" -> "Home"
        "settings" -> "Settings"
        else -> "App"
    }
}