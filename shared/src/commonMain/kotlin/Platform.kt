interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

data class WithDeclaration(
    val value: String
)

expect fun swiftDeclaration(): WithDeclaration