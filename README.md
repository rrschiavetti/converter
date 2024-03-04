# Milliseconds to Time Converter

The Milliseconds to Time Converter is a simple utility library that converts milliseconds representation into human-readable time formats, including seconds, minutes, and hours.

## Installation

To use this library in your Java or Kotlin project, you can add it as a dependency using Maven or Gradle.

### Maven

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>milliseconds-to-time-converter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.example:milliseconds-to-time-converter:1.0.0'
```

## Usage
### Java
```java
import com.example.Converter;

public class Main {
    public static void main(String[] args) {
        Converter converter = new ConverterImpl(true, true, true, true);
        String milliseconds = "100000";
        String formattedTime = converter.convert(milliseconds);
        System.out.println("Formatted Time: " + formattedTime);
    }
}
```
### Kotlin
```kotlin
import com.example.ConverterImpl

fun main() {
    val converter = ConverterImpl(enableMinutes = true, enableHours = true)
    val milliseconds = "100000"
    val formattedTime = converter.convert(milliseconds)
    println("Formatted Time: $formattedTime")
}
```

## Parameters
## Parameters

- `enableMinutes`: A boolean flag to enable the conversion of milliseconds into minutes and seconds. Default is `false`.
- `enableHours`: A boolean flag to enable the conversion of milliseconds into hours, minutes, and seconds. Default is `false`. If `true` it ignores `enableMinutes` flag.
- `ignoreZero`: A boolean flag to ignore zero values in the output. Default is `false`.
- `shortForm`: A boolean flag to use short forms of time units (e.g., "s" for seconds, "m" for minutes). Default is `false`.
