## Vocabulary
### User Interface (UI)
- **View:** An element on the screen, like a text block, a button, or a collection or other elements
- **XML:** The format android uses to store view data like text, color, and size
- **Resources:** All our data files defining our views, strings, or a bunch of other things
- **Layout:** Layouts define how views are organized and laid out 

### Top level classes
- **Activity:** Our Activity is the entry point into the app, and holds the UI not contained in fragments. Also used as a Context.
- **Fragment:** A Fragment is what holds the Views for our UI pages
- **Controller:** The Controller is what we will use to handle and logic and data for our fragments.
- **Utility:** Utilities consist of useful functions to share across our fragments, like fetching data, or getting preferences

### Data Structures
- **Data Class:** An class of Objects that holds data
- **HashMap:** A HashMap stores values by 'keys' or unique id's used to access them later
- **Array:** An Array stores an ordered list of values

### Methods
- **Method:** Reusable block of code, take in parameters and returns some value
- **Parameters:** Values passed to methods
- **Return:** Values passed back from methods

### Types
- **Class:** Set of values and methods
- **Interface:** Set of methods to implement behaviors for in an Object
- **Type:** What kind of value a given variale is eg. some Object, or an int
- **Variable:** Some value of a given type
- **Casting:** 
- **Generic:** Allowing a method or class to use different types of Objects
- **Primitive:** Value that is not an object eg. int
- **Null:** Variable that isn't set to an Object

### Objects
- **Object:** A collection of values and things you can do with them
- **Instance:** One Object of a given class
- **Extends:** A class has the values and functions of its parent, as well as any more you define, can only extend one class. A class can be casted to the class it extends
- **Implements:** A class must define how the methods in an implemented interface work. A class can be casted to any interface it implements
- **Interface:** A collection of methods that can be implemented
- **Override:** Notation used to show that a function replaces the same named function in a class it extends or implements

### Primitive Data Types
- **Integer:** Whole numbers eg -1, 0, 1 up to 2147483647
- **Long:** Whole numbers larger in magnitude than ints
- **Double:** Decimal numbers
- **Boolean:** True of False
- **String:** Group of characters (Word, sentence, or longer)

### Variable/Method Access
- **Public:** Accessible by anyone
- **Private:** Accessible only in this class
- **Static:** Code that can be called without an object of that class
- **Final:** Variable that cannot be modified
- **Constant:** Variable that is hard coded to have some value (Never changes)

### Android
- **Context:** Used to get resources and interact with a lot of Andriod internal stuff
- **Gradle:** Android build system
- **SharedPreferences:** One place to store data on a device

### Misc
- **Callback:** An Object that implements a method to call on some event
- **Asynchronous:** Code that runs in the background
