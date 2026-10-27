# CSC303 Lab 02 - Java Programming Fundamentals

## Execution Commands
- Compile: `javac Main.java Device.java Connectable.java MobileDevice.java`
- Run: `java -cp . Main`

## Key Findings & Issues
- Integer division vs Floating point division: `90 / 60` evaluates to `1` because both operands are integers, truncating decimals. `90 / 60.0` promotes division to floating-point (`double`), yielding `1.5`.
- Polymorphism: Calling `summary()` on a `Device` reference bound to a `MobileDevice` instance invokes the overridden `MobileDevice.summary()` method dynamic method dispatch at runtime.
