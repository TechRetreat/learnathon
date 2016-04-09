# Gettings Started

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
