# The Annotation
[Home](README.md)
[Previous](8-DataManager.md) - [Next](10-Map.md)
- Create another "CocoaTouchClass" file, we're going to create a class to represent an Annotation object on the map
- For this annotation object, we want it to conform to the "MKAnnotation" protocol. So right beside `NSObject` add `, MKAnnotation` to show this
- Since we say we're implementing the `MKAnnotation` protocol, if you command-click on the protocol you can see that we need to have three properties we need to have:

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

- The last piece of this class is to return an actual view that we can display on our map. This will be a class function, very similar to the function where we returned a cell. It'll start off like this.

```swift
static func createViewAnnotationForMapView(mapView: MKMapView, annotation: MKAnnotation) -> MKAnnotationView {
  var returnedAnnotationView: MKAnnotationView

  // populate returnedAnnotationView

  return returnedAnnotationView
}
```
- Similarly to when we made a cell in a table, we will have a "reusable identifier", defined the same was as we did in the "MenuViewController".
- Then use an `if let` to see if `mapView`'s `dequeueReusableAnnotationViewWithIdentifer` method returned `nil` or an `MKAnnotationView`. If it does return a `MKAnnotationView`, then assign it to the variable we just declared (`returnedAnnotationView`), and set its annotation property to the `annotation` we passed into the function.
- If it returns `nil`, then `returnedAnnotationView` should be set to an instance of `MKAnnotationView`. When we create an instance, we need to make sure we pass in the annotation as well as the reuseIdentifier. On this view, we also want to set the `canShowCallout` to be true. For more information about this property, we can option-click on `canShowCallout` for more information.

[Previous](8-DataManager.md) - [Next](10-Map.md)
