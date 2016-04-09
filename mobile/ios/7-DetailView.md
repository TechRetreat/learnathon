# Make the detail page
### Make the detail view controller
We're going to make a quick view controller that will allow us to display information about the cache, such as its name, description and location. It will also allow us to "find" a cache.

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
