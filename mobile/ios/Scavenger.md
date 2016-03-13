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
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
      return 1 // we will only have 1 section in this table.
    }
    
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
