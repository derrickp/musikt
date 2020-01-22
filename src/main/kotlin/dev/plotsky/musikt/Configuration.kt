package dev.plotsky.musikt

data class Configuration(
    val baseUrl: String,
    val appName: String,
    val contact: String
)

fun buildDefaultConfiguration(): Configuration {
    return Configuration(
        baseUrl = "https://musicbrainz.org/ws/2",
        appName = "Musikt",
        contact = "Derrick Plotsky https://github.com/derrickp/musikt"
    )
}
