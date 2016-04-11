# iOS
[Home](README.md)
[< Found Items](6-FoundItems.md) - [Data Manger >](8-DataManager.md)
## Make the detail page
### Make the detail view controller
We're going to make a quick view controller that will allow us to display information about the cache, such as its name, description and location. It will also allow us to "find" a cache.

Let's create anothe file called "CacheDetailViewController.swift", which as you may have guessed by the name inherits from `UIViewController`.

Let's start off with defining the properties of this view controller. What we want this view controller to do is to display the details of a cache object, and also allow ability to find the cache. So we're going to need:
- A cache object (of type `cache`)
- 4 `UILabel`s, for the title, description, difficulty, and location.
- A button to indicate that the item was found.

### Initializers
We want our initializer to take in the cache object that it describes. So our initializer would look something like this:

```swift
init(cache: Cache) {
  self.cache = cache
  super.init(nibName: nil, bundle: nil)
}

required init?(coder aDecoder: NSCoder) {
  fatalError("init(coder:) has not been implemented")
}
```

//TODO: explain initializers in swift

### Setting up the views
Since we'll have quite a lot of views to set up on this page, it would be best if we break down the setting up of each view into its own function.

I made a function to set all of these properties separately:
- The title
  - Font color
  - Font size
  - The text
- The description
  - Font color
  - Font size
  - The text
- The location label
  - Font color
  - Font size
  - The text
- The found button
  - The title
    - Font color
    - Font size
    - Title string
  - The background color
  - The action. We can hook it up to a method like we did in our [hello world]() example

These methods would also be a great time to add them as [subviews]() to our main view controller's [view]()
Let's go ahead and try setting the frame of these views inside these methods.

Make sure to call all of them from `viewDidLoad`.

### Make the cells tapable
  - Next, let's make the cells do something when we tap them
  - Let's go back to `func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath)`.
      - This returns the `indexPath` the same way as in the `cellForRowAtIndexPath` method.
      - Right now, if you select a row it gets grayed out and stays grayed out. We can change that by animating a deselection when it's selected using: `tableView.deselectRowAtIndexPath(indexPath, animated: true)`
      - What we want is to show a new [view controller]() whenver a cell is pressed. 
      - We can create one of our nifty `CacheDetailViewControllers` using the initializer that takes in a cache. Let's put that view controller into a constant called `vc`. Now we can push that view controller like so:
```swift
self.navigationController?.pushViewController(vc, animated: true)
```

### Sizing the views
Now let's run our application, it should look like below:
//TODO: Add gif

That looks all find and dandy, but what happens when we rotate our phone? Uh-oh, that's not what we want. Since we set our view only when the view loaded our view's don't change when our screen size changes. There are many ways to format views in iOS. We're going to do it one way that is probably the easiest to implement. Other methods if you want to look into them are using [autolayout]() or [Visual Formatting Language (VFL)](). The way we're going to do it, is going to be by using a `viewWillLayoutSubviews` method. When the frame of a [view controller]()'s [view]() changes, it calls this method. This gives us the opportunity to resize our subviews. Let's move all of the code that sets the frame for our views in this method. And since `UIViewController` implements this method, we need to remember to `override` it and call `super`'s implementation:

```swift
override func viewDidLayoutSubviews() {
  super.viewDidLayoutSubviews()

  // Set subviews frames here
}
```

Let's run our code again! Nice, now the views resize properly.

[< Found Items](6-FoundItems.md) - [Data Manger >](8-DataManager.md)
