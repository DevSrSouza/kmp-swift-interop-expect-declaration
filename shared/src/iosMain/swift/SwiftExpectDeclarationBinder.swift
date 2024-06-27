import Foundation

@objc(SwiftExpectDeclarationBinder)
class SwiftExpectDeclarationBinder: NSObject {
    @objc class func registerDeclaration() {
        print("Declaration registered in Swift")
        Platform_iosKt.swiftExpectDeclarations = iOSSwiftExpectDeclarations()
    }
}

class iOSSwiftExpectDeclarations : SwiftExpectDeclarations {
    func swiftDeclaration() -> WithDeclaration {
        return WithDeclaration(value: "That is coming from Swift")
    }
}
