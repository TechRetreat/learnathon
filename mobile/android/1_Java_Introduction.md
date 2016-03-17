##Java

To start todays lesson were going to take a quick look at the language Android apps are written in, Java. Open up the [Java Online Editor](https://www.compilejava.net/) to get started.

### Main
To start, replace the generated code with the following 
```java
public class JavaExamples {
  // The main method is the start of a normal Java program
  public static void main(String[] args) {
    // This will print out "Hello world!" to the console
    System.out.println("Hello World!");
  }
}
```
If we run our code now, after compiling, it will run the `main` method and execute the command `System.out.print("Hello World!");` which prints "Hello World!" to the console. Note that anything after `//` does not run as code, this is called a comment. For now all the code we write will go inside the `main` method, where `System.out.println("Hello World!");` is now.


### Data types
In Java, we store a few different types of values, the most common are `Integers` (numbers), `Strings` (text), and `Booleans` (true or false values). We store these values in things called variables by writing
```java
String someText = "Text to Print";
```
After we have stored our variable, a `String` (or text) named `myText`, we can access its value with just the variable name. Replace our old `System.out.println("Hello World!");` with
```java
String someText = "Text to Print";
System.out.println(someText);
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
int countdown;
countdown = 10;
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
Storing variables is nice, but you cant do very much with just variables. There are a few different ways to control what happens in our code. The first is `if` statements, which will check if a value is true, and if so run the code inside. Try running the following inside our main method in the online editor.
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
You can also check if multiple things are true using 'and' (written `&&`) or if one of a list of things is true with 'or' (written `||`), try running the code below with different values for myNumber and shouldPrint.
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
  // `--` after a variable decreses its value by 1, `++` increases by 1
  countdown--;
}
System.out.println("Finished");
```
Another way to accomplish this result is with a for loop. A for loop has a few forms which we'll discuss later, but for now a for loop has an initialization step, which runs once before looping starts, a condition that decides whether the loop will run or not, and an incremement step which runs every time after the code in the body.
```java
//   initialization;     condition;      increment
for (int countdown = 10; countdown >= 0; countdown--) {
  System.out.println(countdown);
}
System.out.println("Finished");
```

### Data Structures
We also have a lot of situations where we want to store values together, like a list of names. We call the things we use to store these values data structures. The first data structure well use is an `Array`, basically a list of values with a fixed size, in the code below the array has 3 elements.
```java
// String[] means array of Strings, new String[5] gives the length
String[] names = new String[3];
// names[0] is the first item in our list, w'ere setting it to "Jason"
names[0] = "Bob";
names[1] = "Betty";
names[2] = "Barnaby";
```
One cool thing we can do with our data structures is iterate through them with loops.
```java
// For every index in the array print name at that index
for (int i = 0; i < names.length; i--) {
  System.out.println(names[i]);
}
```
Since iterating over arrays happens a lot, Java has a shorter way to write it
```java
// For every String in names, print
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
  
// Same as going through an array
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
System.out.println("Bob's age is " + nameToAgeMap.get("Bob"));

// Here we'll iterate through all the entries in the map
// '.keySet()' returns the list of keys
for (String name : nameToAgeMap.keySet()) {
  System.out.println(name + "'s age is " + nameToAgeMap.get(name));
}
```

### Methods
Sometimes we want to reuse code we write, for this we use methods. Methods are blocks of code that we pass a set of values, called parameters, and then run. In our last example we have some printing code that looks very similar, we could replace these calls with our own method, like below.
```java
public static void main(String[] args) {
  HashMap<String, Integer> nameToAgeMap = new HashMap<>();
  nameToAgeMap.put("Bob", 19);
  nameToAgeMap.put("Betty", 17);
  nameToAgeMap.put("Barnaby", 21);

  // We now call printNameAndAge with the name and age instead of printing here
  printNameAndAge("Bob", nameToAgeMap.get("Bob"));
  
  for (String name : nameToAgeMap.keySet()) {
    printNameAndAge(name, nameToAgeMap.get(name));
  }
}
 
// Void means the method returns no value
// String name, int age are the parameters that we give this method when we call it
public static void printNameAndAge(String name, int age) {
  System.out.println(name + "'s age is " + age);
}
```
Variables can be made avaliable across methods by declaring them outside of a method, making them 'instance' variables.
```java
// Declare the map outside of any methods
static HashMap<String, Integer> nameToAgeMap;
  
public static void main(String[] args) {
  nameToAgeMap = new HashMap<>();
  nameToAgeMap.put("Bob", 19);
  nameToAgeMap.put("Betty", 17);
  nameToAgeMap.put("Barnaby", 21);

  // We only need to pass in the name now, the method can access the map
  printNameAndAge("Bob");
  
  for (String name : nameToAgeMap.keySet()) {
    printNameAndAge(name);
  }
}
 
public static void printNameAndAge(String name) {
  // Inside this method we can now use nameToAgeMap to get the age
  System.out.println(name + "s age is " + nameToAgeMap.get(name));
}
```
Methods can also return values instead of `void` meaning no return type.
```java
static HashMap<String, Integer> nameToAgeMap;
  
public static void main(String[] args) {
  nameToAgeMap = new HashMap<>();
  nameToAgeMap.put("Bob", 19);
  nameToAgeMap.put("Betty", 17);
  nameToAgeMap.put("Barnaby", 21);

  // We can use our method here now as it returns an integer
  System.out.println("Bobs age is " + getAgeFromName("Bob"));
  
  for (String name : nameToAgeMap.keySet()) {
    System.out.println(name + "s age is " + getAgeFromName(name));
  }
}

// Declare return type as int here
public static int getAgeFromName(String name) {
  // Instead of printing well return the age for a given name
  return nameToAgeMap.get(name);
}
```

### Objects
In Java, we can create things called `Object`s. An `Object` is a collection of values and methods, you can think of them like most objects in the real world. Each `Object` is an instance of some `Class`, a template that describes what values as object has, and what it can do. Lets try defining a `Class` Dog and instantiating (creating) an `Object` of class, or type Dog, called `firstDog`.
```java
public class JavaExamples {
  public static void main(String[] args) {
    // Create new dogs, call their constructor methods with their names
    Dog firstDog = new Dog("Buddy");
    Dog secondDog = new Dog("Lassie");
    // Print the name of the dog objects we instantiated
    System.out.println("The first dogs name is " + firstDog.getName());
    System.out.println("The second dogs name is " + secondDog.getName());
  }
}

// This is the definition of our class Dog
class Dog {
  // Our dogs will each have some name
  // private means it cant be accessed outside of this class
  private String name;
  
  // This is the constructor method for the Dog class,
  // note the lack of return type, and the name being the same as the class
  // Our constructor takes in a String name, so to create a dog call `new Dog("someName");`
  public Dog(String name) {
    this.name = name;
  }
  
  // Each dog has a method to return its name
  public String getName() {
    return name; 
  }
}
```
Our `Class` Dog has been defined as having one value `name`, and one method `getName` which returns the dogs name as a `String`. In our `main` function, we create two new Dogs called with the names "Buddy" and "Lassie" and print their names.

### Extension
Sometimes we need to be able to treat different kinds of `Object`s as one, for example if we want an `ArrayList` (which holds only one type of thing eg. `ArrayList<String>`) of Cats and Dogs. One way we can get around this is using inheritance. Inheritance is when a class, in this case Cat and Dog, inherits the values and methods from a 'parent' class using `extends`, as well as adding new methods or values and optionally overriding the behavior of its 'parent's methods.
```java
import java.util.ArrayList;
public class JavaExamples {
  public static void main(String[] args) {
    // Add a Cat and Dog to a list of Animals
    ArrayList<Animal> animals = new ArrayList<>(); 
    animals.add(new Dog("Rover"));
    animals.add(new Cat("Garfield"));
    
    // Print the Animals names and sounds
    for (Animal animal : animals) {
      System.out.println("The animals name is " + animal.getName());
      System.out.println("Its favorite food is " + animal.getFavoriteFood());
    }
  }
}

// Abstract means it cannot be instantiated, you cant say new Animal("name");
abstract class Animal {
  // Every Animal will now have a name
  private String name;
  
  // Base constructor for any Animal 
  public Animal(String name) {
    this.name = name;
  }
  
  // Every Animal has the getName function
  public String getName() {
    return name; 
  }
  
  // Every Animal also likes some kind of food
  public String getFavoriteFood() {
    return "unknown"; 
  }
}

class Dog extends Animal {
  // Dogs constructor calls Animals instructor with the same arguments 'super(name)'
  public Dog(String name) {
   	super(name); 
  }
  
  // Override means this method replaces getFavoriteFood() in its parent class
  @Override
  public String getFavoriteFood() {
    return "Bones";
  }
}

// Same structure as Dog without a defined favorite food
class Cat extends Animal {  
  public Cat(String name) {
   	super(name); 
  }
}
```
Note here that Cat didn't Override getFavoriteFood(), so when the method was called it used the version written in its parent class, Animal. `Object`s like Cat or Dog can also be extended, leading to a heirarchy structure similar to the classification of animals. We could define
```java
class Corgi extends Dog
```
Or put things in between like
```java
class Mammal extends Animal { ... }
class Dog extends Mammal { ... }
```
And define some values and methods at each level.

### Implementation
One problem with Extension is that you can only `extend` one Class, which means you can't share values or methods over different parts of the heirarchy. For example if we have
```java
class Fish extends Animal { ... }
class Mammal extends Animal { ... }
class Whale extends Mammal { ... }
```
We can no longer inherit the method swim() in both Fish and Whales without also having it for all Mammals. To solve this problem we use Implementation instead of Extension. Implementation is when you implement an Interface (a set of undefined methods), and you define the behavior for every method given. For example the Interface LandAnimal might have the methods `walk` and `run`. Below is a small example of inheratance.

```java
import java.util.ArrayList;
public class JavaExamples {
  public static void main(String[] args) {
    Shark shark = new Shark();
    Dog dog = new Dog("Buddy");
    
    ArrayList<Animal> animals = new ArrayList<>(); 
    animals.add(shark);
    animals.add(dog);
    
    ArrayList<FourLegged> fourLeggeds = new ArrayList<>(); 
    fourLeggeds.add(dog);
    
    ArrayList<Swimmer> swimmers = new ArrayList<>(); 
    swimmers.add(shark);
    
    System.out.println("Animals:");
    for (Animal animal : animals) {
      System.out.println("The animals name is " + animal.getName());
	  animal.makeSound();
    }
    System.out.println("");
    
    System.out.println("FourLeggeds:");
    for (FourLegged animal : fourLeggeds) {
      animal.run();
    }
    System.out.println("");
    
    System.out.println("Swimmers:");
    for (Swimmer animal : swimmers) {
      animal.swim();
    }
  }
}

// Anything extending Animal must define what these methods do
interface Animal {
  public String getName();
  public void makeSound();
}

interface FourLegged {
  public void run();
}

interface Swimmer {
  public void swim();
}

// A Dog is an Animal that has four legs (can run)
class Dog implements Animal, FourLegged {
  private String name;

  public Dog(String name) {
   	this.name = name;
  }
  
  // Override means this method replaces getName() in a parent class
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public void makeSound() {
    System.out.println(getName() + " says Woof");
  }
  
  @Override
  public void run() {
    System.out.println(getName() + " goes running...");
  }
}

class Shark implements Animal, Swimmer {  
  public Shark() {
  }
  
  @Override
  public String getName() {
    return "Shark...";
  }
  
  @Override
  public void makeSound() {
    System.out.println("The shark makes no sound");
  }
  
  @Override
  public void swim() {
    System.out.println("The Shark swims away");
  }
}
```
