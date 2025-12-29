This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you're sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

## Build and Run project

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## SwissETH Plugin - Solidity Contract Wrappers

This project uses the **SwissETH** Gradle plugin to automatically generate type-safe Kotlin wrapper classes from Solidity smart contract ABI JSON files.

**Configuration:**
- ABI files are located in [`composeApp/abi/`](./composeApp/abi/)
- Generated wrappers are placed in `build/generated/source/abi/commonMain/`
- Package name for generated contracts: `org.example.project.contracts`

**Usage:**
1. Place your Solidity contract ABI JSON files in the `composeApp/abi/` directory
2. Each ABI file must contain a `contractName` field (camelCase)
3. Run `./gradlew :composeApp:generateAbiWrapper` to generate wrappers (or they will be generated automatically during build)
4. Use generated classes in your code:
   ```kotlin
   import org.example.project.contracts.HelloWorld
   
   val encoded = HelloWorld.SayHelloWorld.encode()
   ```

**Example:**
The project includes a sample contract `HelloWorld.json` that demonstrates encoding a function call. See [`App.kt`](./composeApp/src/commonMain/kotlin/org/example/project/App.kt) for usage example.

**Dependencies:**
- `io.swisseth:swisseth-solidity-types` - wrapper classes for Solidity data types (KMP-compatible)
- `io.swisseth.solidity.types` - Gradle plugin for code generation  

