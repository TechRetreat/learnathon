##Java
To start todays lesson were going to take a quick look at the language Android apps are written in, Java. Open up the [Java Online Editor](https://www.compilejava.net/) to get started.

### Main
To start, replace the generated code with the following 
```java
public class JavaExamples {
  // The main function is the start of a normal Java program
  // You can ignore this function as Android starts running in a different spot
  public static void main(String[] args) {
    // This will print out "Hello world!" to the console
    System.out.println("Hello World!");
  }
}
```
If we run our code now, after compiling, it will run the `main` function and execute the command `System.out.print("Hello World!");` which prints "Hello World!" to the console. Note that anything after `//` does not run as code, this is called a comment.


### Data types
In Java, we store a few different types of values, the most common are `Integers` (numbers), `Strings` (text), and `Booleans` (true or false values). We store these values in things called variables by writing
```java
String myText = "Text to Print";
```
After we have stored our variable, a `String` (or text) named `myText`, we can access its value with just the variable name. Replace our old `System.out.println("Hello World!");` with
```java
String myText = "Text to Print";
System.out.println("Hello World!");
```
and rerun our program.

We can also store numbers or booleans like so
```java
int myNumber = 1337;
System.out.println("My favorite number is: " + myNumber);

boolean arewWeDoneYet = false;
System.out.println("Are we done yet: " + arewWeDoneYet);
```
As you can see in the above code, Strings (things in quotes) can be joined with numbers or booleans and they will display as their text values.

Variables can also be modified after they have been created (initialized).
```java
int countdown = 10;
System.out.println("Count: " + countdown);

countdown = 9; // Reassign countdown to 9
System.out.println("Count: " + countdown);

countdown = countdown - 1; // Lower countdowns value by 1
System.out.println("Count: " + countdown);

countdown -= 2; // Short form of writing countdown = countdown - 2
System.out.println("Count: " + countdown);

int countdownSpeed = 3;
countdown -= countdownSpeed; // Lower coundowns value by ammount in a variable we define
System.out.println("Count: " + countdown);
```
### Logic
Storing variables is nice, but you cant do very much with just variables. There are a few different ways to control what happens in our code. The first is `if` statements, which will check if a value is true, and if so run the code inside. Try running the following main function in the online editor.
```java
public static void main(String[] args) {
  boolean someValue = false; // Try setting this to true after 
  if (someValue) {
    System.out.println("The value chosen was true");
  }
}
```
Comparing numbers also results in boolean values (true or false), for example `4 == 4` or `5 > 3` are true, while `4 == 5` and `5 < 3` are false. `if` statements can also choose to run some other code if the value is false
```java
public static void main(String[] args) {
  int myNumber = 7; // Try setting this to different numbers
  if (myNumber < 8) {
    System.out.println(myNumber + " is smaller than 8");
  } else {
    System.out.println(myNumber + " is not smaller than 8");
  }
}
```
You can also check if multiple things are true using `&&` or if one of a list of things is true with `||`, try running the code below with different values for myNumber and shouldPrint.
```java
public static void main(String[] args) {
  int myNumber = 7;
  boolean shouldPrint = false;
  if (myNumber < 8 && shouldPrint) {
    System.out.println(myNumber + " is smaller than 8 and shouldPrint is true");
  }
  if (myNumber < 8 || shouldPrint) {
    System.out.println(myNumber + " is smaller than 8 or shouldPrint is true (or both)");
  }
}
```

### Loops
- for, while

### Data Structures
- array, map

### Functions

### Objects
- Object
- Extention
- Implementation