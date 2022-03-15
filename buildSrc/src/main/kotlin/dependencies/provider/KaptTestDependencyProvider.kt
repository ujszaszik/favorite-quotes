package dependencies.provider

interface KaptTestDependencyProvider {

    fun kaptTestDependencies(): List<String>
}