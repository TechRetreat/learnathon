#Scavenger

## Create the project
  1. Go to `File` -> `New` -> `Project`
  2. Select `iOS` -> `Single View Application`
  3. Name your app! The `Product Name` field will be the name of your app.
  4. For organization, you can just put your name.
  5. For the organizatoin identifier, try to make it unique. For example you can do `com.firstName.lastName`
  6. Choose `Swift` as the programming language
  7. Select `iPhone` for the devices filed
  8. Make sure the "Use Core Data" field is unchecked
  9. Click next
  10. Choose where to save your project on your computer.
  11. Make sure "Create Git repository on My Mac" is selected
  12. Click "Create"!

## Let's look around!
  - Congrats! You've created the foundation for your first mobile application!
  - Now let's take a look at the panel on the left. This is the navigator. It'll show you all of the files in your project.

  ### The files
    - `AppDelegate.swift`
      - This is where your program starts, whenever the app is launched iOS will start running some of the code in here.
    - `ViewController.swift`
      - This is a basic "ViewController". We'll get into more details later, but we'll want to make our own.
    - `Main.storyboard`
      - Xcode lets you create the UI of application using some visual elements. But a lot of the time, it can actually be limiting, so we're going to make our application entirely through code.
    - `Assets.xcassets`
      - This is where all of the images and other media of the app is stored. This includes the app icon.
    - `LaunchScreen.storyboard`
      - This is the screen that shows while the app is loading
    - `Info.plist`
      - This is where you can specify various information about the application, like the permissions it needs and other things like that. We won't be spending much/any time in here.
  
  ### Cleaning up
    - Since we want to start from scratch, we can delete a few files:
      1. Delete the `ViewController.swift` file.
      2. Delete the `Main.storyboard` file.
      3. After deleting the `Main.storyboard` file, go to the top item on the navigator (it should be the name of your app, with a blue icon beside it)
      4. Look for the field labeled "Main Interface" and delete the text in the text box beside it.


## Git moving
  - Q: What is git?
  - A: Good question! Git is a peice of software that does [version control](#). Which is just a way of easily maintaining backups of your work!


## Hello, world!
  - Now that we're all set up. Let's
