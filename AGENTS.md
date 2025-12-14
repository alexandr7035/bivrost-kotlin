# AGENTS.md

## Project Overview

**BivKMP Kotlin** generates type-safe Kotlin wrapper classes from Solidity smart contract ABI JSON files. 
It automatically creates Kotlin classes with encode/decode methods for contract functions and events.

### Main Modules

- **bivkmp-solidity-types**: Runtime types for Solidity data types (UInt, Int, Bytes, Address, etc.) - **Kotlin Multiplatform (KMP)**
- **bivkmp-utils**: Utility functions (hex conversion, Keccak256 hashing)
- **bivkmp-abi-parser**: Core ABI parsing and Kotlin code generation (uses KotlinPoet)
- **bivkmp-gradle-plugin**: Android Gradle Plugin integration for automatic code generation
- **bivkmp-solidity-types-generator**: Standalone generator for Solidity type classes

## Testing

**Note**: All tests have been migrated from JUnit to Kotlin Test framework for multiplatform compatibility.

Run all tests:
```bash
./gradlew test --no-daemon
```

Run tests for specific module:
```bash
./gradlew :bivkmp-abi-parser:test --no-daemon
./gradlew :bivkmp-solidity-types:test --no-daemon
```

Test output shows status for each test (PASSED/FAILED/SKIPPED) configured in `build.gradle` test blocks.

### Test Infrastructure

- **Kotlin Test**: All tests use `kotlin.test` framework (multiplatform-compatible)
- **No Reflection**: Tests use automatically generated `SolidityTypeRegistry` instead of Java reflection for type instantiation
- **Registry Location**: `SolidityTypeRegistry.kt` is generated in `commonTest` source set (internal, test-only)

## Publishing Locally

Publish all modules to Maven Local:
```bash
./gradlew :bivkmp-solidity-types:publishToMavenLocal \
          :bivkmp-utils:publishToMavenLocal \
          :bivkmp-abi-parser:publishToMavenLocal \
          :bivkmp-gradle-plugin:publishToMavenLocal --no-daemon
```

Publish single module:
```bash
./gradlew :bivkmp-abi-parser:publishToMavenLocal --no-daemon
```

Artifacts are published to `~/.m2/repository/by/alexandr7035/` directory.

### Kotlin Multiplatform Publishing

The `bivkmp-solidity-types` module is published as a multiplatform library:
- **Main artifact**: `bivkmp-solidity-types` (contains common code and metadata)
- **Platform-specific artifacts**: 
  - `bivkmp-solidity-types-jvm` (JVM bytecode)
  - `bivkmp-solidity-types-linuxx64` (Native Linux klib)
  - iOS artifacts (iosX64, iosArm64, iosSimulatorArm64) - published when iOS toolchain is available

Gradle automatically selects the correct artifact variant based on the target platform.

## Key Files

- `build.gradle`: Root project config with version definitions
- `bivkmp-abi-parser/src/test/resources/automatic_tests/`: test contract scenarios
- `bivkmp-gradle-plugin/src/main/kotlin/by/alexandr7035/bivkmp/plugin/BivkmpPlugin.kt`: Gradle plugin entry point

## Architecture Notes

### Kotlin Multiplatform Support

- **bivkmp-solidity-types** is a Kotlin Multiplatform module supporting:
  - JVM
  - iOS (x64, arm64, simulatorArm64)
  - Linux (x64)
  
- **Code Generation**: 
  - `Solidity.kt` is generated in `commonMain` (production code)
  - `SolidityTypeRegistry.kt` is generated in `commonTest` (test-only, internal)
  - Generator automatically creates KMP-compatible code

### Reflection-Free Testing
**Current Solution:**
- Tests use automatically generated `SolidityTypeRegistry` with functional factory types instead of Java reflection usage in original lib
```kotlin
  (8..256 step 8).forEach {
      val factory = SolidityTypeRegistry.uintFactories["uint$it"]!!
      factory(value) // Test all UInt types in a single loop
  }
  ```
- Registry contains factories for all UInt, Int, and Bytes types (generated automatically)
- Enables multiplatform testing without JVM-specific reflection APIs
- Maintains the same compact test structure while being KMP-compatible
- Registry is internal and hidden from library consumers

## KMP Migration Summary

**Migrated to KMP:**
- `bivkmp-solidity-types`: Full KMP migration (JVM, iOS x64/arm64/simulatorArm64, Linux x64)
  - Source structure: `commonMain/` + `commonTest/` (was `main/` + `test/`)
  - Replaced `java.math.BigInteger` → `com.ionspin.kotlin.bignum.integer.BigInteger`
  - Tests migrated from JUnit → Kotlin Test
  - Added `SolidityTypeRegistry` for reflection-free type instantiation

**Remained JVM-only:**
- `bivkmp-abi-parser`: Uses KotlinPoet (JVM-specific)
- `bivkmp-utils`, `bivkmp-gradle-plugin`, `bivkmp-solidity-types-generator`: Build tools

**Dependency Replacements:**
- `org.bouncycastle:bcprov` → `org.kotlincrypto.hash:sha3` (KMP)
- `com.squareup.moshi` → `kotlinx-serialization-json` (KMP)
- `junit:junit` → `kotlin-test` (KMP)
- All build scripts migrated: Groovy → Kotlin DSL

## Development instructions

When moving/renaming files: **ALWAYS use `git mv`** to preserve history (shows as `R` in git status, not `D`+`A`).

```bash
git mv old/path/file.kt new/path/file.kt
```

If file already exists in new location: check `git status`, remove from index if needed, then use `git mv`.

## Gradle Plugin

### Overview

The `bivkmp-gradle-plugin` generates Kotlin wrapper classes from Solidity ABI JSON files for KMP projects.

**Features:**
- Generates code to `build/generated/source/abi/commonMain`
- Registers generated code in `commonMain` (KMP) or `main` (Android) source set
- Single task `generateAbiWrapper`
- Configurable via `bivkmp` extension

### Plugin Usage

```kotlin
// build.gradle.kts
plugins {
    id("by.alexandr7035.bivkmp")
}

solidityTypes {
    packageName.set("com.example.contracts")  // package for generated wrappers
}

dependencies {
    implementation("by.alexandr7035:bivkmp-solidity-types:0.1")
}
```

### Requirements

- ABI JSON files in `{module}/abi/` directory
- ABI files must use `contractName` field (camelCase)
- Dependency: `bivkmp-solidity-types` in `commonMain.dependencies`

## SampleApp Project

### Overview

`SampleApp/` is a Kotlin Multiplatform Compose application demonstrating bivkmp plugin usage.

**Structure:**
- `SampleApp/composeApp/` - Main KMP module
- `SampleApp/composeApp/abi/` - ABI JSON files
- Targets: Android, iOS, Desktop (JVM)

```
