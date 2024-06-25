import dev.srsouza.swift.interop.binder.SwiftExpectDeclarationBinder
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalForeignApi::class)
actual fun swiftDeclaration(): WithDeclaration {
    // call header function
    SwiftExpectDeclarationBinder.registerDeclaration()

    return swiftExpectDeclarations!!.swiftDeclaration()
}

var swiftExpectDeclarations: SwiftExpectDeclarations? = null

interface SwiftExpectDeclarations {
    fun swiftDeclaration(): WithDeclaration
}