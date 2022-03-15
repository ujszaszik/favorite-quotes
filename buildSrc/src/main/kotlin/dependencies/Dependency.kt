package dependencies

class Dependency(
    groupId: String,
    artifactId: String,
    version: String
) {
    val value = "$groupId:$artifactId:$version"
}

fun List<Dependency>.values(): List<String> = map { it.value }