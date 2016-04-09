## Hello, world!
- Now that we're all set up. Let's get started.
1. App startup
  - Remember how our application will start in "AppDelegate.swift"? Let's take a look where exactly that happens.
  - Open the "AppDelegate.swift" file and find the `func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool` function.
    - Although it has a long name it's job is very simple. When the program starts, it gets called, then it does what it needs to get the app setup and finally returns `true` if everything went alright.
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

