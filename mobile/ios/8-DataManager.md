# Data Model Manager
[Home](README.md)
[Previous](7-DetailView.md) - [Next](9-AnnotationObject.md)

## The JSON
  - We are going to read in the values of the caches from a JSON file. JSON is a popular format to store information, especially when asking for information from web servers. We are going to start off with our JSON locally.
  - Download the two starter JSON files for the [Initial Cache List]() and the [Initial Caches Found]()
  After downloading these files, we're going to drag them into our project. Once they're in your project, we need to create a class to turn our JSON files into classes that our program can understand. We're going to call this class a `DataModelManager`.
  Looking at the JSON a bit first, we can see that the entire structure is shaped as a dictionary. The "caches.json" file has the generic format:
  ```
  {
    "Cache name": {
        name: "Cache name",
        description: "Cache description",
        difficulty: 8,
        location: {
          latitude: 12.123,
          longitude: 43.123
        }
    }
  }
  ```
If we look at the types of this, we would get this:
  ```
  {
    String: {
      String: String,
      String: String,
      String: Int,
      String: {
        String: Double,
        String: Double
      }
    }
  }
  ```

  - We can go through and [parse]() it. That means we are going to convert the JSON into an object. Since this is a fairly uninteresting part of the application, the code will be provided and explaind.
  - In this code, we load a file called "caches.json". `NSBundle.mainBundle().pathForResource("caches", ofType: "json")` will load that. If the file does not exist, it will return nil and the `if let` statement will fail. Next we convert the contents of this file to `NSData`, which is just a series of "1"s and "0"s: the raw data of the file. From this format, we can use a calss called `NSJSONSerialization` provided with Swift that tries to convert this NSData to the dictionary we specified above. The `try`, `guard` and `throw` keywords work together to `throw` an `InvalidFormat` exception, which just means that it will error with a custom error (InvalidFormat). If anything went wrong, it will print out "Something went wrong...", which is done by `do`, `catch` pair.
    ```swift
    func loadCaches() { // Returns a dictionary of String ids to the cache object
      do {
        if let path = NSBundle.mainBundle().pathForResource("caches", ofType: "json") {
          if let jsonData = NSData(contentsOfFile: path) {
            guard let jsonResult = try NSJSONSerialization.JSONObjectWithData(jsonData, options: .MutableContainers) as? CacheListJSONFormat else {
                throw DataModelError.InvalidFormat
            }
            caches = [String:Cache]() // clear out old caches
              for (id, cacheObject) in jsonResult {
                let cache = Cache(json: cacheObject) // We have not implemented this yet. We will by the end of the module
                caches[id] = cache
              }
          }
        }
      } catch {
        print("Something went wrong...")
      }
    }
    ```

    We can do nearly the same for the "found.json" file, which specifies the caches that we already found.
    ```swift
    func updateFoundStates() {
      do {
        if let path = NSBundle.mainBundle().pathForResource("found", ofType: "json") {
          if let jsonData = NSData(contentsOfFile: path) {
            guard let jsonResult = try NSJSONSerialization.JSONObjectWithData(jsonData, options: NSJSONReadingOptions.MutableContainers) as? [String:[String:Double]] else {
                throw DataModelError.InvalidFormat
            }

            if let cacheEntry = jsonResult["found_times"] {
              for (id, time) in cacheEntry {
                if let cache = self.caches[id] {
                  cache.found = time
                }
              }
            }
          }
        }
      } catch {
        print("Something went wrong...")
      }
    }
  ```
  
Now we're going to make an initalizer that takes in a dictionary of this format to create an actual `Cache` object. We're going to go to our "Cache.swift" file and add an additional initalizer. This initializer is a bit different. Start off with a normal initializer, similar to the other one. This one, however, can fail. Because it can fail, we add a `?` after the `init`. If it does fail we will return [nil]().
Looking at the structure of the file we can define some types at the top of the `Cache` file. Looking at a cache item, specifically:
```
{
    name: "Cache name",
    description: "Cache description",
    difficulty: 8,
    location: {
      latitude: 12.123,
      longitude: 43.123
    }
}
```
We notice, that it's just a dictionary, where the value can be either a `String`, `Int`, or a location. The location object itself would be a dictionary with `String`s and keys and coordinates as `Double`s. We can use the `typealias` keyword to call this a `LocationJSON` type. Together it looks like `typealias LocationJSON = [String:Double]`. This typically goes at the top of the file.
Going back to the JSON, we can look back an see that the value can be a `String`, `Int`, or `LocationJSON`. We can use the special `AnyObject` type to describe this type. Since typing out `[String:AnyObject]` again and again would be tedious, we can just call that the `CacheJSONFormat` with the `typealias` keyword. Now looking at the entire JSON file, we have the following:
```
  {
    "Cache name": {
        name: "Cache name",
        description: "Cache description",
        difficulty: 8,
        location: {
          latitude: 12.123,
          longitude: 43.123
        }
    }
  }
```
Wich is just a dictionary with a string as the key and the `CacheJSONFormat` we just defined as the value. We can use a type alias and call this `CacheListJSONFormat`.
In the end, we will have something that looks like this:

```swift
typealias CacheListJSONFormat = [String:CacheJSONFormat]
typealias CacheJSONFormat = [String:AnyObject]

typealias LocationJSON = [String:Double]
```

This will allow us to manage types more neatly.

Going back to our initalizer, we will take in JSON of type `CacheJSONFormat`.

 ```swift
 // We add the question mark to this initializer, because it could fail if the input format is incorrect
 init?(json: CacheJSONFormat) {
  // code to parse the json goes here
 }
 ```
 
 Using the `if let` command in swift, we can make sure that the format is correct. If any condition fails, we'll set some default initial values and return nil. This is some sample code that can be used as the body of the initalzier.
```swift 
   // This large if statment makes sure everything is of proper format
   if let name = jsonObject["name"] as? TypeOfNameValue,
      let desc = jsonObject["description"] as? String,
      let diff = jsonObject["difficulty"] as? Int,
      let location = jsonObject["location"] as? TypeOfLocationValue,
      let long = location["longitude"],
      let lat = location["latitude"] {
      
         self.name = name
         self.description = desc
         self.difficulty = diff
         self.location = CLLocationCoordinate2D(latitude: lat, longitude: long)
    } else { // This code runs if the JSON input format is bad
         print("JSON FORMAT IS INVALID")
         return nil
    }
```

[Previous](7-DetailView.md) - [Next](9-AnnotationObject.md)
