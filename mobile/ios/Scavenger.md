#Scavenger

## Create the project
1. Make a new project ("File" -> "New" -> "Project")
2. Select "iOS" -> "Single View Application". We'll start off with the most basic starting point. Starting off from scratch.
3. This of a name for your app! The "Product Name" field will be the name of your app. I named the sample on "Scavenger".
4. The organization field just specifies the author of the app, you can put your name there.
5. For the organizatoin identifier, try to make it unique. For example you can do "com.firstName.lastName".
6. Make sure to choose "Swift" as the programming language
7. Select "iPhone" for the devices filed
8. Make sure the "Use Core Data" field is unchecked. This will add some template code that we don't want for this app.
10. After clicking next you can choose where to save your project on your computer.
11. Make sure "Create Git repository on My Mac" is selected. This will let us use source control.
12. Click "Create"!

## Let's look around!
  - Congrats! You've created the foundation for your first mobile application!
  - Now let's take a look at the panel on the left. This is the navigator. It'll show you all of the files in your project.

### The files
- "AppDelegate.swift"
  - This is where your program starts, whenever the app is launched iOS will start running some of the code in here.
- "ViewController.swift"
  - This is a basic "ViewController". We'll get into more details later, but we'll want to make our own.
- "Main.storyboard"
  - Xcode lets you create the UI of application using some visual elements. But a lot of the time, it can actually be limiting, so we're going to make our application entirely through code.
- "Assets.xcassets"
  - This is where all of the images and other media of the app is stored. This includes the app icon.
- "LaunchScreen.storyboard"
  - This is the screen that shows while the app is loading
- "Info.plist"
  - This is where you can specify various information about the application, like the permissions it needs and other things like that. We won't be spending much/any time in here.
  
### Cleaning up
- Since we want to start from scratch, we can delete a few files:
  1. Delete the "ViewController.swift" file. (Select "Move to trash")
  2. Delete the "Main.storyboard" file. (Select "Move to trash")
  3. After deleting the "Main.storyboard" file, go to the top item on the navigator (it should be the name of your app, with a blue icon beside it)
  4. Look for the field labeled "Main Interface" and delete the text in the text box beside it.
  
### Bear necessities (The Jungle Book shoutout *hoot*)
- The backbones of the applications will be the [View Controllers](#). They will control everything on the screen.
1. Navigate to "File" -> "New" -> "File"
2. Go to iOS, and choose "Cocoa Touch Class"
3. Click "Next"
4. Name your [class](#) "MenuViewController"
5. Make it a [subclass](#) of "UIViewController"
6. Make sure "Also create XIB file" is *unchecked* and the language is "Swift"
7. Click "Next"
8. Click "Create"
  
### Check up
- Let's make sure everything is set up properly.
  1. In the top left, click the drop down and select your phone.
  2. Hit the run button (play button in the top left), or you can hit "Command + R"
- If the app doesn't run, try and get a mentor to help you

## Hello, world!
- Now that we're all set up. Let's get started.
1. App startup
  - Remember how our application will start in "AppDelegate.swift"? Let's take a look where exactly that happens.
  - Open the "AppDelegate.swift" file and find the `func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool` function.
    - Although it has a long name it's job is very simple. When the program starts it gets called, then it does what it needs to get the app started and finally returns `true` if everything went alright.
    - The first thing we want to do is create a window, so let's add the line: `self.window = UIWindow(frame: UIScreen.mainScreen().bounds)`
      - Let's break this down. The app delegate has a [property](#) called window, which is the window for the application. So we need to set it to a new `UIWindow` object. When we create the `UIWindow` we specify the size of the window as the size of the main screen.
    - Next let's make a function that looks like this:
      ```swift
      func start() {
        let menu = MenuViewController()
        let navController = UINavigationController(rootViewController: menu)

        self.window!.rootViewController = navController
      }
      ```
      - This function [instatiates]() our menu [view controller]()
      - Then creates a [navigation controller](), with our menu as the base
      - And the sets the root of the window to the [navigation controller]()
    - Now let's call it. Under the line where we [initialize]() the window, call the start method we created using the code: `self.start()`
2. View Controllers
  - "[Views]()" are elements on the screen that users intereact with (e.g. Buttons, cells, labels, etc...)
  - "[View controllers]()" as the name suggests, controls the contents of the view. It acts as the brain of the application and tells the views what to do.
  - The "MenuViewController" will control the menu. It will manage the views and control what happens when the user interacts with them.
  - By default, view controllers have a lot of code that we don't need. In "MenuViewController.swift" you can delete the `didReceiveMemoryWarning` function and the comments after it. It should look like:
```swift
import UIKit

class MenuViewController: UIViewController {

  override func viewDidLoad() {
      super.viewDidLoad()

      // Do any additional setup after loading the view.
  }

}
```
  - [`ViewController`]()s have a [property]() called `view`. It can be accessed via `self.view` This is the [root view]() of the view controller.
    - Let's set the background colour of the view controller's view by setting its `backgroundColor` property to a colour. You can create a colour by using one of the `UIColor` [constructors]().
  - Let's start by adding a label. We will add it in the [`viewDidLoad`]() method
    - To add a label, we want to add the label as a property of the view controller. We add properties at the top of the class declaration, so in this case, right before [`viewDidLoad`]().
    - Since we want it to stay a label always, we can make it a constant. And also, since only this class needs to know about the label, we can also make it private. This means that we can add the property with the line: `private let helloLabel = UILabel()`
    - Now that `helloLabel` is a [property]() of the [view controller]() we can go ahead and access it in [`viewDidLoad`]() with `self.helloLabel`.
      - Then we want to set the `text`, `font`, and `textColor` properties of the label. For the font, you can set it like `self.helloLabel.font = UIFont.systemFontOfSize(20)`. Give the other 2 properties a shot! (hint: colours are [object]()s too! You can create the colour red [object]() using `UIColor.redColor()`.
      - We also want to set the `frame` of the [view](). For now, we will just set it to the `bounds` of `MenuViewController`'s view.
      - Finally, we want to add the label as a [`subview`]() of the `MenuViewController`'s view.
3. Run!
  - When you run the application, you should see some text displayed on the screen.
4. Tidying up.
  - If you havn't already, make sure to commit your code ("Source Control" -> "Commit")
  - Now we want to make room for our scavenger app, so let's comment out all of the code that has to do our label.

## Scavenger
### Main Menu
We're well on our way to making our geocaching app! The first thing that we're going to do is make the screen below:
**INSERT PIC OF MENU HERE**

Before we start coding, let's see what's going on here. What we have is a [view]() called a [UITableVIew](), which is just a group of cells. You see this in many iOS applications.
1. Add a table view
  - We're going to follow the same steps as we did to add a label. Not we want to create a `private`, *constant* item named "tableView", which is an [instance]() of `UITableView`. Give it a try!
  - In `viewDidLoad`, we need to set a few properties of the `tableView`. Start off by setting the `rowHeight` and `frame` of the `tableView`, just like you did for the `helloLabel`. I set my row height to 100. This is measured in [points]()
  - Now let's remember what a [view]() does. All it knows to do is display information and if someone is trying to interact with it. But it doesn't know **what** to do if someone intereacts with it. That's where the view controller comes it. The [view]() has a [`delegate`]() property. The [view]() then forwards its actions to let the delegate deal with it.
  - The `MenuViewController` will act as the `tableView`'s [delegate](). To specify this, set the `tableView`'s `delegate` property to `self`. `self` is referencing the `MenuViewController`.
  - The `tableView` also needs a way to know what the data to put in the table. It does this similarly to the `delegate`. It does it with a `dataSource` property. Go ahead and also set its `dataSource` property to `self`.
  - The last thing we need to specify is what type of cells the table is going to use. For this app we're going to use the default cell, but we still need to tell the `tableView` that we want to use the default cell. So we're going to add the line `tableView.registerClass(UITableViewCell.self, forCellReuseIdentifier: "menuCellIdentifier")`. This lets the `tableView` do some cool optimizations for large tables, which you can read about [here]() if you're interested.
  - Now  if you try running the app, you'll see some errors. This is because we told the `tableView` that we can be its `delegate` and `dataSource`, but it doesn't believe us. The only way for the `tableView` to know if we can actually live up to that claim is if we declare that we implement the `UITableViewDelegate` and `UITableViewDataSource` [protocols]().
  - A [protocol]() is simply a list of methods that we need to implement. It can have required methods, which we *must* implement, and optional ones too.
  - To keep the code clean, we're going to implement the delegate and the data source in extensions of the class.
  - **Delegate methods**
     - For the delegate, make an extension that delcares it implements the `UITableViewDelegate` as such:
       ```swift
      // this line means that MenuViewController says it implements the UITableViewDelegate protocol
      extension MenuViewController: UITableViewDelegate { 
          // this function is called when a use taps on a cell
          func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) { 
            // TODO: We will implement this function later 
          }
      }
       ```
  - **Data Source methods**
    - Make another extension to `MenuViewController`, this time declaring that you implement `UITableViewDataSource`
    ```swift
    // Since we only have one section, we can just return how many rows we want
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int { 
      return 1 // TODO: return the number of menu items. Display 1 for now so we can see the table view
    }
    
    // This function returns the cell we want to go at a certain row
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell { 
      // Remember when we registered this identifier for the tableView. This is where it comes in.
      // Make sure the two idenfiers are the same. In this case, "menuCellIdentifier"
      let cell = tableView.dequeueReusableCellWithIdentifier("menuCellIdentifier", forIndexPath: indexPath)
      
      cell.backgroundColor = UIColor.lightGrayColor() // set the colour to light grey for now

      return cell
    }
    ```
2. Manage the menu items.
  - We're going to start off with 4 menu items: Map, Found Locations, Nearest Locations, and Settings
  - To model this, we're going to define an [array]() of menu items.
  - Make an array with the names of the menu items as a property of the view controller, just like the `tableView`.
  - Now let's go back to the `func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int`
  - We want to return how many menu items we have, so we can return the length of the array we defined
  
  - Next we want to set the content of each cell to display the name of the menu item.
  - Let's take a look at `func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath)`:
    - This method gets called for every cell that is visible, with a different `indexPath`
    - We can know which row it is being called for using the `indexPath.row` property. That will be set to 0 for the first row, 1 for the second and so on
    - The cell has a textLabel property. You can set the text of this textLabel with `cell.textLabel?.text = "Your text here"`
    - Use the `menus` array, and what you know about arrays to set the title of the cell to the name in `menus`
    - Also, the cell has a property called the `accessoryType`. Try setting it to `UITableViewCellAccessoryType.DisclosureIndicator` and see what happens.
  
  - Next, let's make the cells do something when we tap them
  - Let's go back to `func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath)`.
      - This returns the `indexPath` the same way as in the `cellForRowAtIndexPath` method.
      - Right now, if you select a row it gets grayed out and stays grayed out. We can change that by animating a deselection when it's selected using: `tableView.deselectRowAtIndexPath(indexPath, animated: true)`
      - What we want is to show a new [view controller]() whenver a cell is pressed. So, let's create those first. Create 4 new files called:
        - `MapViewController`
        - `FoundCachesListViewController`
        - `ClosestCachesViewController`
        - `SettingsViewController`
      - We can create an array with an instance of all of them like this: `[MapViewController(), FoundCachesListViewController(), ... ]` (fill in the `...` with the other two controllers). This goes right under where we created the array with the menu strings.
      - Then using the `indexPath` select the right view controller and put it into a constant. Let's call it `VC`.
      - To display that view controller, we can display it like this: `self.navigationController?.pushViewController(VC, animated: true)`

3. Creating the Models
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
    
4. The Map View
  - Next we want to work on the map view
  - Let's create another "CocoaTouchClass" file, subclassing "UIViewController", and let's call it MapViewController
  - It will have 2 properties:
    ```swift
      private let mapView = MKMapView() // A map view, which comes built in
      private var annotations: [Annotation] = [] // The array of annotiation we're going to display
    ```
  - Now let's make our `viewDidLoad` function:
    - The first thing we always do in `viewDidLoad` is call `super.viewDidLoad()`
    - Then we want to set the background colour to address the previous issue we mentioned
    - Next we want to set up the map's size on the screen, we can do this by setting it equal to the view controller's `view`'s bounds. (Don't forget to also add it as a subview)
    - The mapView, just like the table view, needs a [delegeate]() to manage it's annotations. The `MapViewController` will be the delegate for now.
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
