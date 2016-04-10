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

### Setting up the views


### Sizing the views

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

[< Found Items](6-FoundItems.md) - [Data Manger >](8-DataManager.md)
