# Models
[Home](Scavenger.md)

[Previous](menu.md)

- Since we're building a geocaching app, we need to be able to represent a cache, to do this make a new file called "Cache.swift"
  - We want our `Cache` object to have the following properties:
    - `name` (a `String`)
    - `description` (a `String`)
    - `difficulty` (an `Int`)
    - `found` (a `Double?`, the question mark means that the property can be set to `nil`, or nothing. Which means we have not found it yet.)
    - `location` (a `CLLocationCoordinate2D`)
    - To get access to the `CLLocationCoordinate2D`, we need to add `import MapKit` at the top of our file
  - Next, we need to define an initilizer. We can do so like this:

  ```swift
  init(name: String, description: String, difficulty: Int, location: CLLocationCoordinate2D) {
    self.name = name
    self.description = description
    self.difficulty = difficulty
    self.location = location
  }
  ```
  - This allows us to create a `Cache` object
  - We can also create methods which allow us to find a `Cache` object:
  
    ```swift
    func foundItem(atTime time: Double) {
      self.found = time
    }

    func foundItem(atTime time: NSDate) {
      self.found = time.timeIntervalSince1970
    }

    func loseItem() {
      self.found = nil
    }
    ```
    
  - How we want to be able to find the distance between two caches.
  - Create a function called `getDistanceFrom(origin: CLLocationCoordinate2D)` that returns an integer, the distance between `self` and `origin`
  - It may look something like this:
  
    ```swift
    func getDistanceFrom(origin: CLLocationCoordinate2D) -> Int {
      let originLocaiton = CLLocation(latitude: origin.latitude, longitude: origin.longitude)
      let distance = originLocaiton.distanceFromLocation(CLLocation(latitude: self.location.latitude, longitude: self.location.longitude))
      return Int(distance)
    }
    ```

## The annotation
  - Create another "CocoaTouchClass" file, we're going to create a class to represent an Annotation object on the map
  - For this annotation object, we want it to conform to the "MKAnnotation" protocol. So right beside `NSObject` add `, MKAnnotation` to show this
  - Since we say we're implementing the `MKAnnotation` protocol, if you command-click on the protocol you can see that we need to have three properties:
  
    ```swift
    var coordinate: CLLocationCoordinate2D
    var title: String?
    var subtitle: String?
    ```
  - We're also going to add our own custom property, a `Cache` object that the Annotation object is presenting.
  - When we set the cache object of this class, we also want to set the `coordinate`, `title` and `subtitle` to match the cache. Swift let's us do this with the following code:

    ```swift
    var cache: Cache {
      didSet {
        self.titleProperty = cache.name
        self.subtitleProperty = cache.description
        self.placeProperty = cache.location
      }
    }
    ```
  - Let's create the initializer. The initializer should take in a `Cache` object and set it to the local `cache` property. Note, since we're in the initializer, the `didSet` code will not be run in this special case. 

    ```swift
    init(cache: Cache) {
      self.title = cache.name
      self.subtitle = cache.description
      self.coordinate = cache.location
      self.cache = cache
    }
    ```

  - The last peice of this class is to return an actual view that we can display on our map. This will be a class function, very similar to the function where we returned a cell. It'll start off like this.

```swift
static func createViewAnnotationForMapView(mapView: MKMapView, annotation: MKAnnotation) -> MKAnnotationView { 
  var returnedAnnotationView: MKAnnotationView
  
  // populate returnedAnnotationView
  
  return returnedAnnotationView
}
```
  - Similarly to when we made a cell in a table, we will have a "reusable identfier", defined the same was as we did in the "MenuViewController". 
  - Then use an `if let` to see if `mapView`'s `dequeueReusableAnnotationViewWithIdentifer` method returned `nil` or an `MKAnnotationView`. If it does return a `MKAnnotationView`, then assign it to the variable we just declared (`returnedAnnotationView`), and set its annotation peropty to the `annoation` we passed into the function.
  - If it returns `nil`, then `returnedAnnotationView` should be set to an instance of `MKAnnotationView`. When we create an instance, we need to make sure we pass in the annotation as well as the reuseIdentifier. On this view, we also want to set the `canShowCallout` to be true. For more inforamtion about this property, we can option-click on `canShowCallout` for more information.

[Next](map.md)
