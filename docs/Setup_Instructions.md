# Setup Instructions

## JDK Version

This project was developed and tested using **JDK 17** (any JDK 8+ version will work).

## Environment Setup

### 1. Install JDK

- Download the JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/).
- Follow the installation instructions for your operating system.

### 2. Verify Installation

Open a terminal and run:

```bash
java -version
javac -version
```

You should see output similar to:

```
java version "17.0.x"
javac 17.0.x
```

### 3. Set JAVA_HOME (if not already set)

**macOS/Linux:**
```bash
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$JAVA_HOME/bin:$PATH
```

**Windows:**
```
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%
```

## Compiling and Running

### Option 1: Using the run script

```bash
chmod +x run.sh
./run.sh
```

### Option 2: Manual steps

```bash
# Create output directory
mkdir -p out

# Compile all Java files
javac -d out src/com/airtribe/learntrack/enums/*.java \
             src/com/airtribe/learntrack/constants/*.java \
             src/com/airtribe/learntrack/exception/*.java \
             src/com/airtribe/learntrack/entity/*.java \
             src/com/airtribe/learntrack/util/*.java \
             src/com/airtribe/learntrack/repository/*.java \
             src/com/airtribe/learntrack/service/*.java \
             src/com/airtribe/learntrack/Main.java

# Run the application
java -cp out com.airtribe.learntrack.Main
```

## Hello World Verification

Before working on this project, we verified Java was installed correctly by creating a simple program:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run:
```bash
javac HelloWorld.java
java HelloWorld
```

Output: `Hello, World!`

This confirms the JDK is installed and configured correctly.
