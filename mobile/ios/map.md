# Map
[Home](Scavenger.md)

[Previous](models.md)

Now, we're going to get started on building the map portion of the application. To do this, we're going to use a [framework]() called [MapKit](). A framework is a package of useful methods and classes. In the case of MapKit, it provides some classes and methods to interface with maps. One of the view's we're going to use is `MKMapView`, which is a view that automatically provides a map for use to use. As we create a new screen, we're going to create a new [View Controller](). Let's make a new "CocoaTouchClass" file, and make it a sublcass of "UIViewController".
The standard for naming view controllers is to have the class name end in "ViewController". So you can name this class "MapViewController" or something of the kind.
View controllers can have [private properties](), properties that only that view controller can see. Some properties that this `MapViewController` may have is the map view it will display, and a list of annotations we want to display on the map. So we can add these two properties to the view controller with these two lines:
```swift
private let mapView = MKMapView()
private var annotations: [Annotation] = []
private var caches = [Cache]()
```

Our `viewDidLoad` method is called when the view controller's view did load, as the name suggests. The first thing that needs to be done is to ensure that the [super class]() is set up, so we call `super.viewDidLoad()`. We can also set the background colour of the view of the view controller by setting the `backgroundColor` property of `self.view`, which is the view property of the view controller.

- The mapView, just like the table view, needs a [delegeate]() to manage it's annotations. The `MapViewController` will be the delegate for now. To do this, we can set the `delegate` property to itself.
- 

- Next we want to work on the map view
  - Let's create another "CocoaTouchClass" file, subclassing "UIViewController", and let's call it MapViewController.
  - It will have 2 properties:
    ```swift
    private let mapView = MKMapView()
    private var annotations: [Annotation] = []
    private var caches = [Cache]()
    ```
  - Now let's make our `viewDidLoad` function:
    - The first thing we always do in `viewDidLoad` is call `super.viewDidLoad()`
    - Then we want to set the background colour to address the previous issue we mentioned
  
    - Next we want to set up the map's size on the screen, we can do this by setting it equal to the view controller's `view`'s bounds. (Don't forget to also add it as a subview)
  
    - Next, we need a list of all of the caches. We can get this using our nifty `DataModelManager` that we created earlier. To access the shared insance just use `DataModelManager.sharedInstance`, then we can simply access the `caches` property of the shared instance to get all of the caches. 
    - Now we want to get a list of annotations based on this list of caches. In Swift we can do this using the `map` function, which is like a short cut for a for loop:
      ```swift
      annotations = caches.map { (id, cache) in
        Annotation(cache: cache)
      }
      ```
    - Next, we want to add all of our annotations to the map, which looks something like this. Easy peasy.
      ```swift
      self.mapView.addAnnotations(self.annotations)
      ``` 
    - Finally, let's make it go to the default location. Let's pretend we have a method called `goToDefaultLocation()` and call it here like this: `self.goToDefaultLocation()`
    
  - Now let's implement this method.
    - First, using the `self.annotations` list, find the average latitude and longitude of all our annotations, then create a new variable and make it an instance of `MKCoordinateRegion`. Now set the `center` of this object to the average location and set the `span` property to `0.02` in the `latitude` and `longitude` directions. (This is just the zoom of the region.)
    - Give this a try. If you get stuck, remember, Google is your friend. This is how I did it:
      ```swift
      func goToDefaultLocation() {
        let defaultScale = 0.02

          var newRegion = MKCoordinateRegion()

          // TODO, to consider
          // Get all of the latitudes and longitudes
          // This is the functional way... should the for-loop method be shown?
          let latitudes = self.annotations.map { $0.coordinate.latitude }
        let longitudes = self.annotations.map { $0.coordinate.longitude }

        newRegion.center.latitude = average(latitudes)
          newRegion.center.longitude = average(longitudes)

          newRegion.span.latitudeDelta = defaultScale
          newRegion.span.longitudeDelta = defaultScale

          self.mapView.setRegion(newRegion, animated: true)
      }
      ```
    - And to get the averages, I used an extension:
      ```swift
      // Break this off into an extension of [Double]
      extension MapViewController {
        func average(array: [Double]) -> Double { // TODO: Make as an extension of array
          var sum = 0.0
            for element in array {
              sum += element
            }
          return sum / Double(array.count)
        }

        func fancyAverage(array: [Double]) -> Double {
          return array.reduce(0, combine: +) / Double(array.count)
        }
      }
      ``` 
  - Sidenote: Since we are using a navigation view controller, the view will actually start underneath the navigation view which we can't access. To fix this, we can set it to opaque like this:
    ```swift
    override func viewWillAppear(animated: Bool) {
      super.viewWillAppear(animated)

        self.navigationController?.navigationBar.barStyle = .Black
        self.navigationController?.navigationBar.translucent = true
    }
    ```
  - The MapViewDelegate
    - Now we need a method to return the view for a specific annotation. I did this like this:
      ```swift
      extension MapViewController: MKMapViewDelegate {
        func mapView(mapView: MKMapView, viewForAnnotation annotation: MKAnnotation) -> MKAnnotationView? {
          var returnedAnnotationView: MKAnnotationView? = nil

            if (!annotation.isKindOfClass(MKUserLocation.self)) {
              returnedAnnotationView = Annotation.createViewAnnotationForMapView(self.mapView, annotation: annotation)

                returnedAnnotationView?.image = UIImage(named: "flag")
                let sfIconView = UIImageView(image: UIImage(named: "SFIcon"))
                returnedAnnotationView?.leftCalloutAccessoryView = sfIconView
            }
          return returnedAnnotationView
        }
      }
      ```
      
[Next](found.md)
