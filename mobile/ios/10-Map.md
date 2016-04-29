# Map
[Home](README.md)

[< Annotation Object](9-AnnotationObject.md) - [Tab Bar >](11-TabBar.md)

Now, we're going to get started on building the map portion of the application. To do this, we're going to use a [framework]() called [MapKit](). A framework is a package of useful methods and classes. In the case of MapKit, it provides some classes and methods to interface with maps. One of the view's we're going to use is `MKMapView`, which is a view that automatically provides a map for use to use. As we create a new screen, we're going to create a new [View Controller](). Let's make a new "CocoaTouchClass" file, and make it a subclass of "UIViewController".
The standard for naming view controllers is to have the class name end in "ViewController". So you can name this class "MapViewController" or something of the kind.
View controllers can have [private properties](), properties that only that view controller can see. Some properties that this `MapViewController` may have is the map view it will display, and a list of annotations we want to display on the map. So we can add these three properties to the view controller with these three lines:
```swift
private let mapView = MKMapView()
private var annotations: [Annotation] = []
private var caches = [Cache]()
```

## `viewDidLoad`
  - Our `viewDidLoad` method is called when the view controller's view did load, as the name suggests. It's important to note that it will only be called the first time it is loaded, not every time it appears. The first thing that needs to be done is to ensure that the [super class]() is set up, so we call `super.viewDidLoad()`. We can also set the background colour of the view of the view controller by setting the `backgroundColor` property of `self.view`, which is the view property of the view controller.

  - The `mapView`, just like the table view, needs a [delegeate]() to manage it's annotations. The `MapViewController` will be the delegate for now. To do this, we can set the `delegate` property to itself (`self`).
  - Now let's add the `mapView` as a [subview]() to the view controller's view.
  - If we run the application, we still won't see a map on this page. If we set up a break point at the end of `viewDidLoad` and see what's going on. It turns out that the frame of the `mapView` is not set. So let's set the `frame` property of the `mapView` to the `frame` of the view controller's view. This can be done like this: `self.mapView.frame = self.view.frame`.
  - Running again, we can see that now we have a nice map! Now rotate the device. If you're on a simulator, checkout the "hardware" menu for rotation options. The map doesn't seem to be resizing. This is because the frame of the map is the same to what we originally set it. There are many ways to control the layout of views on the screen, but the easiest way to do it right now is to use the `viewDidLayoutSubviews` method. This method is called whenever the screen size changes, so in this case, when the device is rotated.

## `viewDidLayoutSubviews`
  - Let's try moving the code we wrote to set the frame of the mapView into a method called `viewDidLayoutSubviews`. Since this is a method that comes part of `UIViewController`, we're going to have to override it, as well as to make sure that we call the `super` class' implementation of the method. So this would look something like this:
    ```swift
    override func viewDidLayoutSubviews() {
      super.viewDidLayoutSubview()

      // Code to set the mapView's frame
    }
    ```
  - Next, we need a list of all of the caches. We can get this using our nifty `DataModelManager` that we created earlier. To access the shared instance just use `DataModelManager.sharedInstance`, then we can simply access the `caches` property of the shared instance to get all of the caches.
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


  - Now let's implement this method that will take the user to the default location.
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
    - Now we need a method to return the view for a specific annotation.
    - Some goals that we want to get out of this is to:
      - Have a green flag show on the map for the found caches and a red flag show up for caches that have not been found yet.
      - We want to be able to tap on the flag to have a name and description of the cache
      - A button to go see the details of the cache (we will set up the button, but link to the details later)
    - Let's start with an extension which implements `MKMapViewDelegate`
      ```swift
      extension MapViewController: MKMapViewDelegate {
        func mapView(mapView: MKMapView, viewForAnnotation annotation: MKAnnotation) -> MKAnnotationView? {

        }
      }
      ```
    - This method will be automatically called by the `mapView` for every annotation, including the annotation to indicates the user's location. So we can detect if the annotation is the user, and return `nil` in that case like:
     ```swift
     if annotation.isKindOfClass(MKUserLocation.self) { return nil }
     ```
    - Then using the `createViewAnnotationForMapView` method we implemented in the previous module, we can set up a `returnedAnnotationView` constant and assign it to this.
    - Now, we can set up two images, which you can download [here]() and [here]() and drag it into the "Assets" folder in the project. To get these images we can use the following code:
      ```swift
      let notFoundFlag = UIImage(named: "flag")
      let foundFlag = UIImage(named: "foundFlag")
      ```

    - We can safely ensure that the annotation passed in is of the `Annotation` type we defined (as they all should be). This check can be done like this:
    ```swift
    if let annot = annotation as? Annotation {
      //...
    }
    ```
    - We can setup an info button with `UIButton(type: .DetailDisclosure)` and assign it to a constant.
    - `annot` will have an [optional]() cache property. Try and write some code to assign the appropriate flag to `returnedAnnotationView.image` depending on whether or not the cache was found.
    - Also we will set the `rightCalloutAccessoryView` of the `returnedAnnotationView` to the detail button we created.

[Previous](9-AnnotationObject.md)
