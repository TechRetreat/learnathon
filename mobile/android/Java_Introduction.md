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
If we run our code now, after compiling, it will run the `main` function and execute the command `System.out.print("Hello World!");` which prints "Hello World!" to the console. Note that anything after `//` does not run as code, this is called a comment. For now all the code we write will go inside the `main` function, where `System.out.println("Hello World!");` is now.


### Data types
In Java, we store a few different types of values, the most common are `Integers` (numbers), `Strings` (text), and `Booleans` (true or false values). We store these values in things called variables by writing
```java
String someText = "Text to Print";
```
After we have stored our variable, a `String` (or text) named `myText`, we can access its value with just the variable name. Replace our old `System.out.println("Hello World!");` with
```java
String someText = "Text to Print";
System.out.println("Hello World!");
```
and rerun our program.

We can also store numbers or booleans like so
```java
int someNumber = 13;
System.out.println("My favorite number is: " + someNumber);

boolean areWeDoneYet = false;
System.out.println("Are we done yet: " + areWeDoneYet);
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
Storing variables is nice, but you cant do very much with just variables. There are a few different ways to control what happens in our code. The first is `if` statements, which will check if a value is true, and if so run the code inside. Try running the following inside our main function in the online editor.
```java
boolean someValue = false; // Try setting this to true after 
if (someValue) {
  System.out.println("The value chosen was true");
}
```
Comparing numbers also results in boolean values (true or false), for example `4 == 4` or `5 > 3` are true, while `4 == 5` and `5 < 3` are false. `if` statements can also choose to run some other code if the value is false
```java
int someNumber = 7; // Try setting this to different numbers
if (someNumber < 8) {
  System.out.println(someNumber + " is smaller than 8");
} else {
  System.out.println(someNumber + " is not smaller than 8");
}
```
You can also check if multiple things are true using `&&` or if one of a list of things is true with `||`, try running the code below with different values for myNumber and shouldPrint.
```java
int someNumber = 7;
boolean shouldPrint = false;
if (someNumber < 8 && shouldPrint) {
  System.out.println(someNumber + " is smaller than 8 and shouldPrint is true");
}
if (someNumber < 8 || shouldPrint) {
  System.out.println(someNumber + " is smaller than 8 or shouldPrint is true (or both)");
}     
```

### Loops
Often we encounter situations when we want to run a command many times, one way to accomplish this is using a `while` loop. A while loop keeps running the code inside while the condition in brackets is true, in this case while countdown is greater than or equal to 0.
```java
int countdown = 10;
while (countdown >= 0) {
  System.out.println(countdown);
  countdown --;
}
System.out.println("Finished");
```
Another way to accomplish this result is with a for loop. A for loop has a few forms which we'll discuss later, but for now a for loop has an initialization step, which runs once before looping starts, a condition that decides whether the loop will run or not, and an incremement step which runs after the code in the loops body.
```java
//   initialization;     condition;      increment
for (int countdown = 10; countdown >= 0; countdown --) {
  System.out.println(countdown);
}
System.out.println("Finished");
```

### Data Structures
We also have a lot of situations where we want to store values together, like a list of names. We call the things we use to store these values data structures. The first data structure well use is an `Array`, basically a list of values with a fixed size, in the code below the array has 3 elements.
```java
// String[] means array of Strings, new String[5] gives the length
String[] names = new String[3];
// names[0] is the first item in our list, were setting it to "Jason"
names[0] = "Bob";
names[1] = "Betty";
names[2] = "Barnaby";
```
One cool thing we can do with our data structures is iterate through them with loops.
```java
// For every String in names, execute the code in the block
for (String name : names) {
  System.out.println(name);
}
```
Another common data structure is the `ArrayList` which is very similar to an array, but has a size you can change (mutable size) which means you can keep adding elements to it.
```java
// ArrayList of Strings called names
ArrayList<String> names = new ArrayList<>();
names.add("Bob");
names.add("Betty");
names.add("Barnaby");
System.out.println("The first name in the list is " + names.get(0));
  
for (String name : names) {
  System.out.println(name);
}
```
If you try to run the above code it will complain that it cant find the symbol `Arraylist`. This is because ArrayList isnt built directly into the language. Add `import java.util.ArrayList;` above `public class HelloWorld` at the top of the file to import the `ArrayList` class.

The last data structure we'll cover for now is called `HashMap`. With `Array` and `ArrayList` we get elements by their position in the list eg. `names[0]` with an `Array` or `names.get(0)` with an `ArrayList`, however with `HashMap` we store and retrieve values by a 'key' value. For example we could store peoples ages by their names. For the code below to run, add `import java.util.HashMap;` at the top or the file.
```java
// HashMap mapping name (String) to age (Integer)
HashMap<String, Integer> nameToAgeMap = new HashMap<>();
nameToAgeMap.put("Bob", 19);
nameToAgeMap.put("Betty", 17);
nameToAgeMap.put("Barnaby", 21);
  
// Get a value by the key in the map, in this case "Bob" is the key
System.out.println("Bobs age is " + nameToAgeMap.get("Bob"));

// Here we'll iterate through all the entries in the map
// '.keySet()' returns the list of keys
for (String name : nameToAgeMap.keySet()) {
  System.out.println(name + "s age is " + nameToAgeMap.get(name));
}
```

### Functions

### Objects
- Object
- Extention
- Implementation
