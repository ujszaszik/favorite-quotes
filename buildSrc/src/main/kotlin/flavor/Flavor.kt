package flavor

enum class Flavor(val flavorName: String, val versionNameSuffix: String? = null) {
    DEFAULT("default", "-def")
}