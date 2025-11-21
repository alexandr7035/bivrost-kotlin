# AGENTS.md

## Project Overview

**Bivrost Kotlin** generates type-safe Kotlin wrapper classes from Solidity smart contract ABI JSON files. 
It automatically creates Kotlin classes with encode/decode methods for contract functions and events.

### Main Modules

- **bivrost-solidity-types**: Runtime types for Solidity data types (UInt, Int, Bytes, Address, etc.)
- **bivrost-utils**: Utility functions (hex conversion, Keccak256 hashing)
- **bivrost-abi-parser**: Core ABI parsing and Kotlin code generation (uses KotlinPoet)
- **bivrost-gradle-plugin**: Android Gradle Plugin integration for automatic code generation
- **bivrost-solidity-types-generator**: Standalone generator for Solidity type classes

## Testing

Run all tests:
```bash
./gradlew test --no-daemon
```

Run tests for specific module:
```bash
./gradlew :bivrost-abi-parser:test --no-daemon
./gradlew :bivrost-solidity-types:test --no-daemon
```

Test output shows status for each test (PASSED/FAILED/SKIPPED) configured in `build.gradle` test blocks.

## Publishing Locally

Publish all modules to Maven Local:
```bash
./gradlew :bivrost-solidity-types:publishToMavenLocal \
          :bivrost-utils:publishToMavenLocal \
          :bivrost-abi-parser:publishToMavenLocal \
          :bivrost-gradle-plugin:publishToMavenLocal --no-daemon
```

Publish single module:
```bash
./gradlew :bivrost-abi-parser:publishToMavenLocal --no-daemon
```

Artifacts are published to `~/.m2/repository/pm/gnosis/` directory.

## Key Files

- `build.gradle`: Root project config with version definitions
- `bivrost-abi-parser/src/test/resources/automatic_tests/`: test contract scenarios
- `bivrost-gradle-plugin/src/main/kotlin/pm/gnosis/plugin/BivrostPlugin.kt`: Gradle plugin entry point
