# AGENTS.md

## Project Overview

**BivKMP Kotlin** generates type-safe Kotlin wrapper classes from Solidity smart contract ABI JSON files. 
It automatically creates Kotlin classes with encode/decode methods for contract functions and events.

### Main Modules

- **bivkmp-solidity-types**: Runtime types for Solidity data types (UInt, Int, Bytes, Address, etc.)
- **bivkmp-utils**: Utility functions (hex conversion, Keccak256 hashing)
- **bivkmp-abi-parser**: Core ABI parsing and Kotlin code generation (uses KotlinPoet)
- **bivkmp-gradle-plugin**: Android Gradle Plugin integration for automatic code generation
- **bivkmp-solidity-types-generator**: Standalone generator for Solidity type classes

## Testing

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

## Key Files

- `build.gradle`: Root project config with version definitions
- `bivkmp-abi-parser/src/test/resources/automatic_tests/`: test contract scenarios
- `bivkmp-gradle-plugin/src/main/kotlin/by/alexandr7035/bivkmp/plugin/BivkmpPlugin.kt`: Gradle plugin entry point
