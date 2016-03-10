#Scavenger

## Create the project
1. Go to "File" -> "New" -> "Project"
2. Select "iOS" -> "Single View Application"
3. Name your app! The "Product Name" field will be the name of your app.
4. For organization, you can just put your name.
5. For the organizatoin identifier, try to make it unique. For example you can do "com.firstName.lastName"
6. Choose "Swift" as the programming language
7. Select "iPhone" for the devices filed
8. Make sure the "Use Core Data" field is unchecked
9. Click next
10. Choose where to save your project on your computer.
11. Make sure "Create Git repository on My Mac" is selected
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


## Git moving
- Q: What is git?
- A: Good question! Git is a peice of software that does [version control](#). Which is just a way of easily maintaining backups of your work!


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


    
1. In "MenuViewController.swift" you can delete the `didReceiveMemoryWarning` function and the comments after it. It should look like:
```swift
import UIKit

class MenuViewController: UIViewController {

      override func viewDidLoad() {
          super.viewDidLoad()

          // Do any additional setup after loading the view.
      }

}
```

2. Add a label
