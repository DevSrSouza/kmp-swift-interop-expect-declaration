# kmp-swift-interop-expect-declaration

This a demo project this the ability of auto registering implementations that will be implemented in Swift.
By leveraging this with expect/actual and objc header.

How the project works:

- We have a shared_header/SwiftExpectDeclaration.h that contains a SwiftExpectDeclarationBinder.registerDeclaration
void function declaration.
- We import on Kotlin using CInterop by defining a `swift_expect_declaration.def` and generate the Kotlin definition with gradle `cinterops.create`.
- We define a expect function on Common Main
- We create on iosMain a interface with the same expect function signature
- We define a top level nullable property for the define interface
- We implement the actual function by: calling the Objc Function and then get the interface from the property and force call the function

On iOS/Swift:
- We configure the project to able to implement objc in Swift (Bridging Header - thanks gpt4)
- We add the Objc Header file reference to the iOS Project and add it to the bridging header
- We implement the interface that we define in Kotlin with the desire behavior
- We create in Swift the implementation of the Objc Header function that should instance the interface implementation and assign the property in Kotlin

We we we we we we weeeee

That is it! Now we have a expect/actual function that is implemented in Swift and does not required to Swift register by hand the implementation, it auto register it self.

