# Claim Plugin Api

[![idea](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

![master](https://github.com/Infumia/ClaimPluginApi/workflows/build/badge.svg)
[![Release](https://jitpack.io/v/Infumia/ClaimPluginApi.svg)](https://jitpack.io/#Infumia/ClaimPluginApi)

## Build your self

`./gradlew publishToMavenLocal`

## How to Use

### Plugin.yml

```yml
depend:
  - InfumiaClaimPlugin
```

### Maven

```xml
<repositories>
  <repository>
    <id>jitpack</id>
    <url>https://jitpack.io/</url>
  </repository>
</repositories>
```

```xml
<dependencies>
  <dependency>
    <groupId>com.github.Infumia</groupId>
    <artifactId>ClaimPluginApi</artifactId>
    <version>${version}</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
  maven {
    url "https://jitpack.io"
  }
}
```

```groovy
dependencies {
  compileOnly("com.github.Infumia:ClaimPluginApi:${version}")
}
```
