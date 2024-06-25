//
//  SwiftExpectDeclaration.swift
//  iosApp
//
//  Created by Gabriel Souza on 24/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

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
