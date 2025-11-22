~### BivKMP-Solidity

A refreshed fork of [Gnosis's Bivrost](https://github.com/5afe/bivrost-kotlin) - Bridge between Solidity Contracts and Kotlin

## TODO usage examples

#### Setup
* TODO library setup steps

* TODO Apply plugin

* Add abi json to project in `app/abi` folder (see sample app)


#### TODO Setup of Sample App
* Optional: Generate the Solidity types:
  - `./gradlew :bivkmp-solidity-types-generator:runSolidityTypeGenerator`
* Add library artifacts to local maven:
  - `./gradlew :bivkmp-utils:uploadArchives`
  - `./gradlew :bivkmp-solidity-types:uploadArchives`
  - `./gradlew :bivkmp-abi-parser:uploadArchives`
  - `./gradlew :bivkmp-gradle-plugin:uploadArchives`
  
* Uncomment `include ':sample:app'` in the `settings.gradle` to include sample app module.

* Build sample app. This should also generate the class `MultiSigWalletWithDailyLimit`
