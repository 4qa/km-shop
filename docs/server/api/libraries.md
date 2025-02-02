Libraries
===

#### Hibernate Validator

Allows to express and validate application constraints.

```toml title="Version Catalogs"
[libraries.hibernate-validator]
module = "org.hibernate.validator:hibernate-validator"
[libraries.hibernate-validator.version]
ref = "hibernateValidatorVersion"
```

#### Jakarta

Expression Language defines an expression language for Java applications.

```toml title="Version Catalogs"
[libraries.jakarta-el]
module = "org.glassfish:jakarta.el"
[libraries.jakarta-el.version]
ref = "jakartaElVersion"
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

[libraries.exposed-jdbc]
module = "org.jetbrains.exposed:exposed-jdbc"
[libraries.exposed-jdbc.version]
ref = "exposedVersion"
```

#### Serialization

JSON serialization library dependency

```toml title="Version Catalogs"
[libraries.kotlinx-serialization-json]
module = "org.jetbrains.kotlinx:kotlinx-serialization-json"
[libraries.kotlinx-serialization-json.version]
ref = "jsonSerializationVersion"
```

#### Koin

Koin is a smart Kotlin dependency injection library to keep you focused on your app, not on your tools

```toml title="Version Catalogs"
[libraries.koin-ktor]
module = "io.insert-koin:koin-ktor"
[libraries.koin-ktor.version]
ref = "koinKtorVersion"
```

#### Ktor

Create asynchronous client and server applications. Anything from microservices to multiplatform HTTP client apps in a simple way. Open Source, free, and fun!

```toml title="Version Catalogs"
[libraries.ktor-server-core-jvm]
module = "io.ktor:ktor-server-core-jvm"
[libraries.ktor-server-core-jvm.version]
ref = "ktorVersion"

[libraries.ktor-server-status-pages]
module = "io.ktor:ktor-server-status-pages"
[libraries.ktor-server-status-pages.version]
ref = "ktorVersion"

[libraries.ktor-server-sessions-jvm]
module = "io.ktor:ktor-server-sessions-jvm"
[libraries.ktor-server-sessions-jvm.version]
ref = "ktorVersion"

[libraries.ktor-server-netty-jvm]
module = "io.ktor:ktor-server-netty-jvm"
[libraries.ktor-server-netty-jvm.version]
ref = "ktorVersion"

[libraries.ktor-client-content-negotiation]
module = "io.ktor:ktor-server-content-negotiation"
[libraries.ktor-client-content-negotiation.version]
ref = "ktorVersion"

[libraries.ktor-server-html-builder]
module = "io.ktor:ktor-server-html-builder"
[libraries.ktor-server-html-builder.version]
ref = "ktorVersion"

[libraries.ktor-serialization-kotlinx-json-jvm]
module = "io.ktor:ktor-serialization-kotlinx-json-jvm"
[libraries.ktor-serialization-kotlinx-json-jvm.version]
ref = "ktorVersion"

[libraries.ktor-server-auth]
module = "io.ktor:ktor-server-auth"
[libraries.ktor-server-auth.version]
ref = "ktorVersion"

[libraries.ktor-server-auth-jwt]
module = "io.ktor:ktor-server-auth-jwt"
[libraries.ktor-server-auth-jwt.version]
ref = "ktorVersion"

[libraries.ktor-server-call-loggin]
module = "io.ktor:ktor-server-call-logging"
[libraries.ktor-server-call-loggin.version]
ref = "ktorVersion"
```

#### Logback Project

Logback is intended as a successor to the popular log4j project, picking up where log4j 1.x leaves off.

```toml title="Version Catalogs"
[libraries.logback-classic]
module = "ch.qos.logback:logback-classic"
[libraries.logback-classic.version]
ref = "logbackVersion"
```

#### Versions

```toml
[versions]
# https://insert-koin.io/docs/setup/koin#ktor
koinKtorVersion="3.2.2"

# https://github.com/ktorio/ktor/releases
ktorVersion = "2.1.3"

# https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
logbackVersion = "1.4.4"

# https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/README.md#json
jsonSerializationVersion="1.4.1"

# https://github.com/JetBrains/Exposed/releases
exposedVersion="0.39.2"

# https://mvnrepository.com/artifact/org.glassfish/jakarta.el
jakartaElVersion="4.0.2"

# https://hibernate.org/validator/documentation/
hibernateValidatorVersion="7.0.5.Final"
```