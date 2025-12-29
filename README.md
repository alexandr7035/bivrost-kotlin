### SwissETH-Solidity

A refreshed KMP fork of [Gnosis's Bivrost](https://github.com/5afe/bivrost-kotlin) - Bridge between Solidity Contracts and Kotlin

## TODO usage examples

#### Setup
* TODO library setup steps

* TODO Apply plugin

* Add abi json to project in `app/abi` folder (see sample app)


#### TODO Setup of Sample App
* Optional: Generate the Solidity types:
  - `./gradlew :swisseth-solidity-types-generator:runSolidityTypeGenerator`
* Publish library artifacts to local maven:
  - `./gradlew :swisseth-solidity-types:publishToMavenLocal`
  - `./gradlew :swisseth-solidity-utils:publishToMavenLocal`
  - `./gradlew :swisseth-solidity-abi-parser:publishToMavenLocal`
  - `./gradlew :swisseth-solidity-types-plugin:publishToMavenLocal`
  
* Uncomment `include ':sample:app'` in the `settings.gradle` to include sample app module.

* Build sample app. This should also generate the class `MultiSigWalletWithDailyLimit`
