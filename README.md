# Cucumber Library: Re-Pickled

![Mod Logo](src/main/resources/port_logo.png)

An independently maintained, unofficial port of Cucumber Library by BlakeBr0.

## Overview

Cucumber Library: Re-Pickled provides shared code, utilities, and functionality required by Mystical Agriculture and related mods.

The goal of this project is to bring the original library to newer Minecraft versions while preserving its functionality and compatibility wherever possible.

## Features

- Shared utilities and common functionality.
- Base item, block, and tool implementations.
- Helper functions for dependent mods.
- Shared rendering and crafting functionality.
- Library support for Mystical Agriculture and related addons.

## Port Changes

- Updated compatibility with Minecraft 26.3 and NeoForge.
- Adapted changed Minecraft and NeoForge APIs.
- Updated tool and block implementations.
- Updated client rendering and shader compatibility.
- Updated recipe management compatibility.
- Removed obsolete Mixin configuration references.
- Introduced independent branding, metadata, and versioning.
- Preserved the original Mod ID, namespace, and Java package structure.

## Downloads

- **CurseForge:** [Cucumber Library: Re-Pickled](https://www.curseforge.com/minecraft/mc-mods/cucumber-library-re-pickled)

Always download the appropriate version for your Minecraft version and mod loader.

## Building from Source

### Prerequisites

- Git
- JDK 25 for the Minecraft 26.3 branch
- An internet connection for downloading build dependencies

### Clone the Repository

```bash
git clone https://github.com/Kiyoshibara/Cucumber.git
cd Cucumber
git checkout 26.3
```

### Build on Windows

```cmd
gradlew.bat clean build
```

### Build on Linux or macOS

```bash
./gradlew clean build
```

The generated JAR files can be found in:

```text
build/libs/
```

Use the distributable mod JAR rather than the sources JAR.

## Development Environment

- **Language:** Java
- **Build System:** Gradle
- **Mod Loader:** NeoForge
- **Recommended IDE:** IntelliJ IDEA

Refer to the build configuration of the selected branch for the required Minecraft, Java, Gradle, and NeoForge versions.

## Versioning

This project uses an independent versioning system based on Semantic Versioning.

- **MAJOR:** Breaking changes.
- **MINOR:** Backward-compatible features and improvements.
- **PATCH:** Backward-compatible bug fixes.

Minecraft versions and port versions are maintained independently.

## Original Project & Attribution

This project is an independently maintained, unofficial port of Cucumber Library.

- **Original Project:** [Cucumber Library](https://www.curseforge.com/minecraft/mc-mods/cucumber)
- **Original Repository:** [BlakeBr0/Cucumber](https://github.com/BlakeBr0/Cucumber)
- **Original Source Branch:** `26.1`
- **Original Source Commit:** [5255f5b](https://github.com/BlakeBr0/Cucumber/commit/5255f5b495f1d2a24610e3e551a04321c59ba752)
- **Original Documentation:** [Blake's Mods](https://blakesmods.com/docs/cucumber)
- **Original Developer:** BlakeBr0
- **Unofficial Port Developer:** Kiyoshibara
- **Original License:** MIT License

All credit for the original library and its code goes to BlakeBr0 and the original contributors.

The original license and copyright notices are preserved.

This project is not affiliated with or endorsed by the original developer.

## License

This project is distributed under the MIT License.

Copyright (c) 2018 BlakeBr0.

See the [LICENSE](LICENSE) file for the full license terms.

## Support

If you encounter crashes, compatibility issues, or unexpected behavior specific to this port, please report them through this project's CurseForge comments or GitHub Issues.

Please do not report issues caused by this unofficial port to the original developer.

**Contact:** kiyoshibaradev@gmail.com

❤️ **Patreon:** [Kiyoshibara](https://www.patreon.com/c/Kiyoshibara)