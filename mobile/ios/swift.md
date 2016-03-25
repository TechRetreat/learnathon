# Swift

Swift is one of the programming languages used for iOS development.

To get started **open a new "Playground" document** in Xcode (File -> New -> File -> Playground)
Let's start off fresh so **delete the contents of the file**

## Hello world!
Traditionally the first program that you write when you're learning a new programming language is one that prints "Hello world!" to the screen. To do this in Swift just type:

```swift
print("Hello world!")
```

### Comments
In Swift, if you type "//", the computer will ignore the rest of the line. This is useful to add some explantion of what your code does to other humans.
Let's add comments to our first program
```swift
// This program prints out "Hello world!"
print("Hello world!")
```

## Data types
There are many different types in Swift. We will go over the most commonly used ones: Strings, Integers, Floats, and Booleans.

### Strings (`String`)
Strings are just what programmers call text. In the case of our program above, "Hello world!" is a string. We can store these strings inside a [variable](), for example:

```swift
var favFruit = "Apple"
```

We can also "add" strings together like this:
```swift
var name = "YOUR NAME HERE"
print("Hello! My name is " + name)
```

Here the `var` means that `favFruit` will be a [variable](), then we assign the value of this variable to the string "Apple".

### Integers (`Int`)
We can store numbers too. Any whole numbers are "Integers" and decimal numbers are called "Floats".
```swift
var intVariable = 3
var floatVariableName = 4.3
```

We can also do some math! Be careful though, when you divide two integers, you will always get an integer. The end is chopped off.
```swift
var five = 2 + 3
var three = 7 / 2 
var threeAndAHalf = 7.0 / 2.0
```

### Booleans (`Bool`)
Booleans are values that can either be `true` or `false`.

```swift
var wittyBoolName = true
```

We can also use [comparison operators](), such as ">", "<", "==", "<=", ">=".
*Note: We use "==" for checking if two things are equal, since we use a single equals sign to assign one thing to another.*

```swift
var moneyInWallet = 20
var pizzaCost = 4.99

var hasEnoughMoney = pizzaCost <= moneyInWallet
// hasEnoughMoney is true, becuase pizzaCost is less than moneyInWallet
```

### Printing results
We can also print out strings, numbers and booleans, like we did in our first program:
```swift
print(4+3)
var favDrink = "Tea"
```

### Variables (`var`)
We've already been creating variables, but we can also go ahead and change their value!
```swift
var numberOfApples = 4
numberOfApples = numberOfApples + 1 // numberOfApples is now 5
print("We now have \(numberOfApples) apples.")
numberOfApples += 1 // This is the short form of the line above
// numberOfApples is now 6

// We can also double the number of apples:
numberOfApples *= 2
print("Woah! We suddenly have \(numberOfApples) apples! Think of all the pie we can make!")
```

### Constants (`let`)
But some things we want to make sure don't change. Those are constants. We create them with the `let` keyword:
```swift
let zero = 0 // Will always be 0. Forever.
```

## If statements
Now that we're getting comfortable storing values into variables and constants, now we can make some decisions in our code.
We do this with an "if-statement", it looks like this:

```swift
var condition = true
if (condition) {
  print("The condition was true")
}
```

You can also use the comparisons operators we saw earlier. It's also possible to execute different code if the condition is not true, using the `else` statement:

```swift
var a = 3
var b = 2
if (a > b) {
  print("a is greater than b")
} else {
  print("a is less than or equal to b")
}
```

With an `if-else`, you can check for more conditions:

```swift
var cond1 = false
var cond2 = true
if (cond1) {
  print("Cond1 is true")
} else if (cond2) {
  print("Cond1 is false, but cond2 is true")
} else {
  print("Cond1 and cond2 are both false")
}
```

You can also connect different expressions with `&&`, which means "and", as well as, "||", which means "or".
```swift
a = true
b = false

if (a || b) {
  print("At least one of a or b is true")
}

if (a && b) {
  print("A and B are both true")
}
```

## While loop
Humans have no trouble doing what we've done so far. But where the real power of computers kicks in is loops. Computers can repeat similar tasks again and again, and never get bored!

The while loop will repeat certain instructions until a condition is false.

```swift
var countdown = 10
while (countdown > 0) {
  print(countdown)
}
print("Blastoff!")
```

## Lists
We can also create a group of elements. One way to group elements is into a list.

```swift
var list = [1,2,3,4]
var anotherlist = 1...4 // A shortcut in Swift that does the same as above
var class = ["Joe", "Sarah", "Dave"] // Lists can hold anything, as long as they are the same type
```

Looking at the `class` list, the first student, "Joe" is stored at the "zeroth" index. We can assign this to a variable.

```swift
var class = ["Joe", "Sarah", "Dave"] 
var firstStudent = class[0] // joe
var secondStudent = class[1] // sarah
```

You can also change the values in a list, as long as the list is a [variable](). 
```swift
var class = ["Joe", "Sarah", "Dave"]
class[0] = "Jason"
print class

// Class is now ["Jason", "Sarah", "Dave"]
```

We can also add new elements to the end like this:
```swift
var class = ["Jason", "Sarah", "Dave"]
class.append("Cora")
print class

// Now class is ["Jason", "Sarah", "Dave", "Cora"]
```

## For loop
There is also another type of loop that can iterate through elements of a list.

```swift
var class = ["Joe", "Sarah", "Dave"]
for studentName in class {
  print("\(studentName) is in the class")
}
```

## Dictionaries
You can also group items in dictionaries, which act exactly like an actual dictionary.

```swift
var dictionary = ["spaceship":"vehicle to get around space time", "astronaut":"space explorer"]
var spaceShipDefinition = dictionary["spaceship"]
print spaceShipDefinition // prints "vehicle to get around space time"

// As long as they are variables, they can be change just like lists
dictionary["astronaut"] = "space wanderer"

print dictionary
```

## Function
We can group blocks of code into a function. This allows us to reuse the group of code without typing it all out again.
Functions can take arguments, but we need to specify the types of the arguments and what type it returns.

Let's make a function called `add` which takes two integers, `a` and `b` and returns their sum as an integer.
```swift
func add(a: Int, b: Int) -> Int {
  return a + b
}
```

We can also make functions that don't return anything, it looks like this:
```swift
func introduce(name: String) {
  print("Hello my name is " + name)
}
```

In Swift, it's common to have very descriptive function names. This will make more sense as we start making apps:
```swift
func methodWithFirstArgument(a: Int, andSecondArgument b: Int) {
      print("This is a common way to pass \(a) and \(b) into a function in Swift")
}
```

We can call these functions like this:
```swift
var five = add(2, 3)
introduce("Jonathan")
methodWithFirstArgument(42, andSecondArgument: 13)
```

## Objects
We can create a collection of related properties and functions into what is called an `Object`. This grouping of properties and functions act a lot like real world objects. You can set their attributed (properties), and ask them to do things (call their functions). To create an `object` we need to create a template for it, which properties and functions should be grouped together. This template is called a `class` and each object is an [instance]() of a particular `class`. This would look something like:

```swift
class Astronaut {
  private var name: String
  private var yearsOfExperience = 0 // You can set initial values like this
  
  // The initializer is the function that creates the object
  // You need at least one initializer for every class
  init(name: String) {
    // We can access the properties of an object using dot notation as below
    // "self" is how we refer to the current instance of the class
    self.name = name
  }
}

var astro1 = Astronaut(name: "Chris Hadfeild")
```

# Fun Notes
