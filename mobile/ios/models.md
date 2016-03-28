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

 ## The JSON
  - We are going to read in the values of the caches from a JSON file. JSON is a popular format to store information, especially when asking for information from web servers. We are going to start off with our JSON locally.
  - Download the two starter JSON files for the [Initial Cache List]() and the [Initial Caches Found]()
  After downloading these files, we're going to drag them into our project. Once they're in your project, we need to create a class to turn our JSON files into classes that our program can understand. We're going to call this class a `DataModelManager`.
  Looking at the JSON a bit first, we can see that the entire structure is shaped as a dictionary. The "caches.json" file has the generic format:
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

  - We can go through and parse it like this:
    // TODO: explain this code
    ```swift
    func loadCaches() { // Returns a dictionary of String ids to the cache object
      do {
        if let path = NSBundle.mainBundle().pathForResource("caches", ofType: "json") {
          if let jsonData = NSData(contentsOfFile: path) {
            guard let jsonResult = try NSJSONSerialization.JSONObjectWithData(jsonData, options: .MutableContainers) as? CacheListJSONFormat else {
              print("We gucci")
                throw DataModelError.InvalidFormat
            }
            caches = [String:Cache]() // clear out old caches
              for (id, cacheObject) in jsonResult {
                let cache = Cache(json: cacheObject)
                  caches[id] = cache
              }
          }
        }
      } catch {
        print("Something went wrong...")
      }
    }

  func updateFoundStates() {
    do {
      if let path = NSBundle.mainBundle().pathForResource("found", ofType: "json") {
        if let jsonData = NSData(contentsOfFile: path) {
          guard let jsonResult = try NSJSONSerialization.JSONObjectWithData(jsonData, options: NSJSONReadingOptions.MutableContainers) as? [String:[String:Double]] else {
            print("We gucci")
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
        self.title = cache.name
        self.subtitle = cache.description
        self.coordinate = cache.location
      }
    }
    ```
  - Let's create the initializer. The initializer should take in a `Cache` object and set it to the local `cache` property. Since an `MKAnnotation` object always needs to have a location, we also need to set that up up-front. We can do this like this:
    ```swift
    init(cache: Cache) {
      self.coordinate = cache.location
      self.cache = cache
    }
    ```
  - The last peice of this class is to return an actual view that we can display on our map. This will be a class function, very similar to the function where we returned a cell:
   // TODO: Explain
    ```swift
    static func createViewAnnotationForMapView(mapView: MKMapView, annotation: MKAnnotation) -> MKAnnotationView {
      var returnedAnnotationView: MKAnnotationView
      if let annotView = mapView.dequeueReusableAnnotationViewWithIdentifier(Annotation.annotationReuseIdentifier) { 
        returnedAnnotationView = annotView
        returnedAnnotationView.annotation = annotation
      } else {
        returnedAnnotationView = MKAnnotationView(annotation: annotation, reuseIdentifier: Annotation.annotationReuseIdentifier)
        returnedAnnotationView.canShowCallout = true
      }
      return returnedAnnotationView
    }

    ```
[Next](map.md)
