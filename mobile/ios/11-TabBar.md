# Tab Bar
[Home](README.md)
[< Map](10-Map.md)

Now we're going to connect the "Found List" portion of our application with our "Map View". One way to do this is to is with a tab bar.

We're going to have a tab bar at the bottom of our screen where we can toggle between the list of items that we found and the map. To do this we're going to use a `UITabBarController`. This is a special type of [view controller]() that comes with the iOS [SDK](). It is what we call a [container view controller](). This type of view controller is responsible for holding and managing other view controllers. Another example of a container view controller that we've already used is the `UINavigationController`. The way we set the tabs is by providing the `UITabBarController` and list of view controllers.

In this case we want the TabBar to hold the 2 `UINavigationController`s that we made. The first holds the "Found List", and the second contains the `MapViewController`. Since we want to create these on startup, we will put this code directly in our `AppDelegate` file.

[< Map](10-Map.md)
