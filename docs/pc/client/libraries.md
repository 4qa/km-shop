Libraries
===

#### Apache Commons Lang

Package of Java utility classes for the classes that are in java.lang's hierarchy, or are considered to be so standard as to justify existence in java.lang.

```toml title="Version Catalogs"
[libraries.commons-lang3]
module = "org.apache.commons:commons-lang3"
[libraries.commons-lang3.version]
ref = "commonsLang3Version"
```

#### kotlinx-cli

Pure Kotlin implementation of a generic command-line parse.

```toml title="Version Catalogs"
[libraries.kotlinx-cli]
module = "org.jetbrains.kotlinx:kotlinx-cli"
[libraries.kotlinx-cli.version]
ref = "kotlinxCLIVersion"
```

#### Serialization

JSON serialization library dependency

```toml title="Version Catalogs"
[libraries.kotlinx-serialization-json]
module = "org.jetbrains.kotlinx:kotlinx-serialization-json"
[libraries.kotlinx-serialization-json.version]
ref = "jsonSerializationVersion"
```

#### D-Bus Java

D-Bus is a message-oriented middleware mechanism that allows communication between multiple processes running concurrently on the same machine.

```toml title="Version Catalogs"
[libraries.dbus-java]
module = "com.github.hypfvieh:dbus-java"
[libraries.dbus-java.version]
ref = "dbusJavaVersion"
```

#### Apache Commons Net

Apache Commons Net™ library implements the client side of many basic Internet protocols.

```toml title="Version Catalogs"
[libraries.commons-net]
module = "commons-net:commons-net"
[libraries.commons-net.version]
ref = "commonsNetVersion"
```

#### Exposed

ORM framework for Kotlin.

```toml title="Version Catalogs"
[libraries.exposed-core]
module = "org.jetbrains.exposed:exposed-core"
[libraries.exposed-core.version]
ref = "exposedVersion"

[libraries.exposed-dao]
module = "org.jetbrains.exposed:exposed-dao"
[libraries.exposed-dao.version]
ref = "exposedVersion"
```

#### HOCON Config

Configuration library for JVM languages.

```toml title="Version Catalogs"
[libraries.config]
module = "com.typesafe:config"
[libraries.config.version]
ref = "configVersion"
```

#### Koin

Koin is a smart Kotlin dependency injection library to keep you focused on your app, not on your tools

```toml title="Version Catalogs"
[libraries.koin-core]
module = "io.insert-koin:koin-core"
[libraries.koin-core.version]
ref = "koinVersion"
```

#### kotlinx-coroutines

Library support for Kotlin coroutines with multiplatform support.

```toml title="Version Catalogs"
[libraries.kotlinx-coroutines-core]
module = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
[libraries.kotlinx-coroutines-core.version]
ref = "coroutinesVersion"
```

#### Logback Project

Logback is intended as a successor to the popular log4j project, picking up where log4j 1.x leaves off.

```toml title="Version Catalogs"
[libraries.logback-classic]
module = "ch.qos.logback:logback-classic"
[libraries.logback-classic.version]
ref = "logbackVersion"
```

#### Ktor

Ktor is an asynchronous framework for creating microservices, web applications and more.

```toml title="Version Catalogs"
[libraries.ktor-client-logging]
module = "io.ktor:ktor-client-logging"
[libraries.ktor-client-logging.version]
ref = "ktorVersion"

[libraries.ktor-client-websockets]
module = "io.ktor:ktor-client-websockets"
[libraries.ktor-client-websockets.version]
ref = "ktorVersion"
```

#### Versions

```toml
[versions]
# https://github.com/ktorio/ktor/releases
ktorVersion = "2.1.3"

# https://insert-koin.io/docs/setup/koin#core
koinVersion = "3.2.2"

# https://github.com/Kotlin/kotlinx.coroutines/blob/master/README.md
coroutinesVersion = "1.6.4"

# https://github.com/lightbend/config
configVersion = "1.4.2"

# https://github.com/JetBrains/Exposed/releases
exposedVersion = "0.39.2"

# https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
logbackVersion = "1.4.4"

# https://commons.apache.org/proper/commons-net/
commonsNetVersion = "3.6"

# https://search.maven.org/artifact/com.github.hypfvieh/dbus-java/3.3.1/jar
dbusJavaVersion = "3.3.1"

# https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/README.md#json
jsonSerializationVersion = "1.4.1"

# https://github.com/Kotlin/kotlinx-cli
kotlinxCLIVersion = "0.3.5"

# https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
commonsLang3Version = "3.12.0"
```