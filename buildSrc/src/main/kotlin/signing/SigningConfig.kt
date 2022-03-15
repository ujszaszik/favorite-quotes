package signing

data class SigningConfig(
    val keyAlias: String,
    val keyPassword: String,
    val storeFilePath: String,
    val storePassword: String
)