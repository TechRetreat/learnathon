# Models

## The Cache

Since we're making a geocaching application, we're going to create a `Cache` class. Just like we did for `Astronaut` earlier.

Let's take a minute to think about what properties and methods a `Cache` object might need.

Okay, here's what I've come up with.
These are the properties I think it's going to need:
- The cache name
- The cache description
- The difficulty of finding the cache
- If the cache was found
  - When the cache was found (we can just set the found time to `nil`)
- The location of the cache

These are some of the methods that may be useful:
- Finding a cache
- Losing a cache
- Finding the distance to other locations


1. Let's get started by making a new file called "Cache.swift" (File -> New -> File -> Swift File)
2. Create the `Cache` object just like we did with the astronauts. We should have 5.
  - Note, if we `import MapKit` at the top of our file, we can use a type called `CLLocationCoordinate2D`, which is a useful way to store locations.
3. Add the properties that we described above.
4. Now we need to make an initializer like we did for the `Person` class so we can set all of the properties.
5. Implement the initializer
6. A method that takes in a time and sets it to the found time.
 - There is a special type called `NSDate` which can be any time value and handles tricky things like timezones and special calendars for us.
  - To convert this into a number we can use "epoch time". This is a popular time format in computer science. It is simply the number of seconds or milliseconds since January 1st 1970. Read more about it [here]().
  - To get the "epoch time" (an integer) from an NSDate, you can use the `timeIntervalSince1970` property.
7. Implement a method that loses the cache. This should set the found time to `nil`.
Need a hint? Take a look at how I did it [here](gistLink).
8. How we want to be able to find the distance between two caches?
  - Create a function called `getDistanceFrom(origin: CLLocationCoordinate2D)` that returns an integer, the distance between `self` and `origin`
  - It may look something like this:

    ```swift
    func getDistanceFrom(origin: CLLocationCoordinate2D) -> Int {
      let originLocaiton = CLLocation(latitude: origin.latitude, longitude: origin.longitude)
      let distance = originLocaiton.distanceFromLocation(CLLocation(latitude: self.location.latitude, longitude: self.location.longitude))
      return Int(distance)
    }
    ```

That's it! That's the cache object that we're going to use in our app.
