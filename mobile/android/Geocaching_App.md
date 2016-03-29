# Building the GeoCaching App
## What are we making
Now that we have an app compiling and running, lets start customizing it to make our Geocaching app. In our app we will have three screens shown below, a list of caches the user has found, a map showing caches at their locations, and a settings page.
TODO: add pics

## Whats here now
If you open up the project tab on the left you will be able to view all the files in the project, for the most part we will be worried about files in java folder which will be your code, and files in the res (resources) folder which will define how your views are laid out and a few other things like colors, images, and text. To start we will look at the Fragment and Activity that we start with, and the xml layouts that go with them.

### MainActivity.java
Activities in Android are entry points into the app that let you show something on the screen, and interact with the system. If you open up MainActivity under the java/yourApplicationName folder you will see the following.
``` java
package com.example.jzukewich.geocaching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```
The first line, `package com...` is where our file is located in the project, we wont worry about this. Our imports are Android built in classes that we need, like when we used `import java.util.Arraylist;` before. In `onCreate`, which you might notice Overrides the method from `MainActivity`'s parent class `AppCompatActivity`, we call `AppCompatActivity`s onCreate and the method `setContentView` that sets the screen to show `R.layout.activity_main`, our activity's view. `R` means resources, so we can find this file in `res/layout/activity_main`, lets go take a look.

### activity_main.xml
Welcome to XML, this is how we usually define our Views in Android. XML allows us to set values like width, height, text, and a bunch more depending what kind of View we are describing. In this case, our Activity's View is very simple, all it is displaying is one line of text, which says "Hello World!".
``` xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.jzukewich.geocaching.MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />
</RelativeLayout>
```
`<RelativeLayout` is defining what kind of element we are describing, in this case a `RelativeLayout`. Layouts in Android are Views that hold one or more views as 'children', and position them on the screen. This `RelativeLayout` will have one child, our "Hello World!" text.
`xmlns:android="http://schemas.android.com/apk/res/android"` and `xmlns:tools="http://schemas.android.com/tools"` define namespaces, you won't need to know what these mean, just have them at the beginning of each file.
`android:layout_width="match_parent"` and `android:layout_height="match_parent"` set the width and height of the fragment to match the container or parents width and height, which will be the full screen.
`android:paddingBottom="@dimen/activity_vertical_margin"` and the following three lines set the 'padding' of the view, which is extra empty space around the sides. `"@dimen/activity_vertical_margin"` references a value defined in a file called dimens.xml, we wont be using dimens.xml anywhere else so dont worry about this.
`tools:context="com.example.jzukewich.geocaching.MainActivity"` gives this layout an associated java file, don't worry about this either.

The second element is our `TextView` inside the `RelativeLayout`, we call the `TextView` a child of the `RelativeLayout`. All this means is that our `RelativeLayout` contains the `TextView` and can define its position and size (somewhat). The `TextView`, besides defining its width and height also gives a value for its text to display, `@string/hello_world`. Any time we see `@something` in XML it means the value is defined in another file, open up res/values/strings.xml.

## Adding Views in XML
### Add a Button
Now that we know what we have in our starting project, lets try adding something. Underneath your `TextView` lets add a `Button`.
``` xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!" />

<Button
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Button Text" />
```
If we click 'Design' at the bottom of the file we can preview what this will look like, notice that the button appeared on top of the text, thats because in a `RelativeLayout` every child needs to define its position relative to something else. Lets have our `Button` appear below the text, to do this we first need to give our `TextView` an Id, add `android:id="@+id/text_view"` at the start of the `TextView`.
``` xml
<TextView
    android:id="@+id/my_text_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!" />
```
Adding this Id allows our `Button` to use the `TextView`, we can now add `android:layout_below="@+id/text_view"` to our `Button`. Lets also give our `Button` an Id the same way we did the `TextView`
``` xml
<Button
    android:id="@+id/my_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_below="@+id/my_text_view"
    android:text="Button Text" />
```
Lets make sure this works by going back to the 'Design' tab (if it doesn't ask a mentor for help)

### Strings
You might have noticed that Android Studio has highlighted `android:text="Test Button"`, if you hover over the text, it says you should use an @string reference instead of a hardcoded string. What this means is that we should write "Test Button" elsewhere, in a file called `strings.xml` and reference it here. If you look at the 'Project' tab on the left, underneath the `layout` folder there will be one called `values`, open up the file called `strings.xml` inside. You should see
``` xml
<resources>
    <string name="app_name">Geocaching</string>
</resources>
```
This file is where we will put all the text that our app uses, which will make it easier to make changes later on, as well as having a few other benifits we wont talk about today. Each String one has a name which us used to access it, and a value. For example `<string name="hello_world">Hello world!</string>` has the name `hello_world` and value "Hello World!". Lets define another String called `button_title` to put on our `Button`. Add `<string name="button_title">Click Me</string>` on a new line under `<string name="app_name">Geocaching</string>`.

If we go back to `activity_main.xml` we can now replace `"Button Text"` with `"@string/button_title"` which will fill in the text from our strings file, "Click Me". Check again to make sure this worked.

## Button Clicks
### Finding the Button in Java
Now we have our button, lets make it do something. To do this we'll need to write some java code to go with our button, the first thing we need to do is get a reference to the button in our `MainActivity`. To do this we use a method built in to `Activity` called findViewById, which returns a `View` object of whatever type the element is. Let's add `Button clickMeButton;` as a variable above the `onCreate` method, and add `clickMeButton = (Button) findViewById(R.id.my_button);` under the call to `setContentView`. What this does is give our MainActivity a variable called `clickMeButton` of type `Button`, and assign it to the object returned by `findViewById`, which will be our `Button` we made earlier as `my_button` is the Id we gave it. The `(Button)` before `findViewById` is called a cast, `findViewById` returns a `View` object, meaning anything that extends the `View` class. By casting it we are specifying what type of `View` this is, in our case a `Button`. Notice that `Button` is red everywhere, this is because we didn't import the class. If you click on `Button` and press (TODO: get Windows Shortcut) or `alt+return` on a Mac, it should automatically import the class for you.

### OnClickListener
Now we have a reference to our `Button`, lets make it do something when we click it. To do something when a `Button` is clicked we give the `Button` an object that imlements a class called `OnClickListener` with one function `onClick(View v)`. Add the following under where you assign `clickMeButton`
```java
clickMeButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // This method will get called when the Button is clicked
        Log.i("Geocaching", "button Clicked");
    }
});
```
What we are doing here is creating an `OnClickListener` and defining what is does when clicked, then passing that object to our `Button`. When the `Button` is clicked this objects `onClick` is called and calls `Log.i("Geocaching", "Button Clicked");`. `Log` is a bit like Androids version of `System.out.println`, you give it a Tag (Geocaching) and some text to output (Button Clicked). The Tag lets you search for certain things that get outputted.

### Changing TextView
Lets change the text in our textView when the button gets clicked, to do that well first need to get a reference to our `TextView`. We do this the same as we did our `Button` earlier. Add a variable of type `TextView` to the class and get the object by the id `my_text_view`, make sure to cast it to `TextView`. Now in the `OnClickListener` after Logging the click, add `yourTextView.setText("Button has been clicked");`.

### Formatting Text
Maybe we want the user to know how many times they clicked the button, lets start by adding a variable to count the clicks. Add `private int buttonClicks = 0;` with your other variables, when the button is clicked, increment `buttonClicks` by one (`buttonClicks++;`). Now we want to set our `TextView` to something like 'Clicked _ times', to do this we'll use specials Strings. In strings.xml define a new string with some name and a value of `Clicked %d times`, the `%d` is a number placeholder that will get replaced by our times clicked. In `MainActivity`, to use this String we will need access to our apps resourses, we get this using `getResources()` which returns a `Resources` object. We can use this object to call `getString(R.string.my_string_name, buttonClicks)`, buttonClicks will replace the `%d` in the string. To chain these functions together we write `String clicks = getResources().getString(R.string.button_clicks, buttonClicks);`. Now that we have our `String`, set it as the text in your `TextView` the same way as before.

## Fragments
### Creating a Fragment
Right now our `Activity` which will run our whole app holds our view. This is a problem because as you saw before we want to show a few different screens in our app. We solve this problem by using `Fragments`, which are screens that our `Activity` can show, and switch between.

Lets start by creating a new Java file called `FoundCachesFragment.java` (this `Fragment` will become our found caches list). Right click on com.yourpackagename under java and select new -> Java Class, enter `FoundCachesFragment` for the name. The first thing were going to do is make our class extend Fragment so add `extends Fragment` after the class name. We also need to import the `Fragment` class so add `import android.support.v4.app.Fragment;` below the package name. You might notice were using `android.support.v4.app.Fragment` instead of `android.app.Fragment`, `support.v4` is a Library that lets you run things on older devices, dont worry much about this as were doing it to avoid some complex stuff later on.

Now that we have our `Fragment`, lets build a layout for it to use, create a new Layout resource File under `layout` called `fragment_found_caches` (the same way you did the class) and replace the generated LinearLayout with the contents of activity_main. Then remove the line `tools:context="com.example.jzukewich.geocaching.MainActivity"` from the new file.

Going back to our `FoundCachesFragment`, lets make it display the file we just made. `Fragment`s have a built in function called `onCreateView` where you can set what it should show, lets start by Overriding it. Add the method below to `FoundCachesFragment`
``` java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return null;
}
```
Now whenever our `Fragment` gets shown, this method will be called and some `View` will be returned to display. Right now we return `null` which basically means nothing. Two of the parameters pased to the `onCreateView` function are a `LayoutInflater` and a `ViewGroup`. The first we will use to create a `View` out of our layout file (or 'inflate' the layout), the other is what we will attach our newly created view to.
``` java
View rootView = inflater.inflate(R.layout.fragment_found_caches, container, false);
return rootView;
```
What this does is get our `fragment_found_caches` layout, and use the `LayoutInflater` called `inflater` to create a `View` called `rootView` from the layout. We then return `rootView` to be displayed on the screen.

### Showing our Fragment
Now that we have a `Fragment` that will display our layout, lets get our `Activity` to use it. We'll start by opening up `activity_main.xml` and replacing everything in that file with
``` xml
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/fragment"
    android:name="com.yourpackagename.FoundCachesFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```
Our `Activity`'s layout now contains a `<fragment` element, which we will use to display our `FoundCachesFragment`. Lets build and run our app and make sure it works (Problems? Most times Android Studio fails to build something a 'Messages' tab pops up at the bottom and tells you whats wrong, feel free to ask for help if it doesnt make sense)

### Moving the counter
Although this works, the logic for our `Button` click counter makes more sense to have in our `Fragment`. Move over the variables and `Button` logic from `MainActivity` to `FoundCachesFragment`, getting the `Views` will happen in between inflating `rootView` and returning it. The calls to `findViewById` have to be switched to `rootView.findViewById` as we are looking in the `Fragment`s view for these elements.