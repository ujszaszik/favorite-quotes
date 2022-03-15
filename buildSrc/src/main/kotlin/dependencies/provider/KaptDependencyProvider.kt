package dependencies.provider

interface KaptDependencyProvider {

    fun kaptDependencies(): List<String>
}