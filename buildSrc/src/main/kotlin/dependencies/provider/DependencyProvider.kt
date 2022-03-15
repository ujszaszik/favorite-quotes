package dependencies.provider

interface DependencyProvider {

    fun dependencies(): List<String>
}