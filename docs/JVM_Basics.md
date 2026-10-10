# JVM Basics

## What is JDK, JRE, and JVM?

**JDK (Java Development Kit)** is the complete development environment for building Java applications. It includes the compiler (`javac`), the Java Runtime Environment, debugging tools, and documentation utilities. As a developer, this is what you install to write and compile Java code.

**JRE (Java Runtime Environment)** is a subset of the JDK. It provides everything needed to *run* a compiled Java application — the JVM, core libraries, and supporting files. End users who only need to run Java programs (not develop them) would install the JRE.

**JVM (Java Virtual Machine)** is the engine that actually executes Java programs. It reads the compiled bytecode (`.class` files) and translates it into machine-specific instructions for the operating system it's running on. The JVM handles memory management, garbage collection, and runtime optimizations.

In short: **JDK** contains the **JRE**, which contains the **JVM**. Developers need the JDK; users need the JRE; and the JVM is the core engine inside both.

## What is Bytecode?

When you compile a Java source file (`.java`) using `javac`, the compiler does not produce native machine code. Instead, it produces **bytecode** — an intermediate, platform-independent set of instructions stored in `.class` files. This bytecode is not specific to any operating system or processor architecture. The JVM reads this bytecode at runtime and interprets (or JIT-compiles) it into the native machine code for the platform it's running on. This is what makes Java portable.

## What Does "Write Once, Run Anywhere" Mean?

"Write Once, Run Anywhere" (WORA) is Java's core promise. It means that a Java program compiled on one machine (say, a Windows laptop) can run on any other machine (say, a Linux server or a Mac) without recompiling — as long as that machine has a compatible JVM installed.

This works because the Java compiler produces platform-independent bytecode rather than platform-specific machine code. Each operating system has its own JVM implementation that knows how to translate bytecode into native instructions for that platform. So the developer writes and compiles the code once, and the JVM on each target platform takes care of running it correctly.
