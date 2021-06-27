# Claim Plugin Api

## Build your self

`mvn clean install`

## How to Use

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
