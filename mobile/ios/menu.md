### Main Menu
[Home](scavenger.md)
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

[Next ->](models.md)