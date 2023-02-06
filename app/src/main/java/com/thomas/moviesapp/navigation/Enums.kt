package com.thomas.moviesapp.navigation

enum class Screens(
    val navRoute: String,

    ) {
    SHOWS_LIST_SCREEN(
        navRoute = "shows_list_screen"
    ),
    SHOW_DETAILS_SCREEN(
        navRoute = "show_details_screen?" +
                "showName={showName}" +
                "&showLanguage={showLanguage}" +
                "&showGenres={showGenres}" +
                "&showRuntime={showRuntime}" +
                "&showImageOriginal={showImageOriginal}" +
                "&showImageMedium={showImageMedium}" +
                "&showRating={showRating}" +
                "&showSummary={showSummary}"
    )
}