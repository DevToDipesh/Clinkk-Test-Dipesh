package com.clinkk.language.bean

object Languages {
    fun getAvailableLanguages() = listOf(
        Language("en","en", "English"),
        Language("ka","ಕನ್ನಡ", "Kannada"),
        Language("hi","हिन्दी", "Hindi"),
        Language("ta","தமிழ்", "Tamil"),
        Language("te","తెలుగు", "Telugu"),
        Language("ml","മലയാളം", "Malayalam"),
        Language("gu","ગુજરાતી", "Gujarathi"),
        Language("pa","ਪੰਜਾਬੀ", "Punjabi"),
        Language("mr","मराठी", "Marathi"),
        Language("or","ଓଡ଼ିଆ", "Odia"),
        Language("bn","বাংলা", "Bengali"),

    )
}