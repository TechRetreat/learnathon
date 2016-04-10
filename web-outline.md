## Your First Website

Create a folder on your computer for all your files to go inside.
In Atom, create a new untitled file (File > New File) and save it as "index.html" in the folder you just created.

Inside `index.html`, type:

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>My First Tab Name</title>
</head>
<body>
  <h1>My First Website Heading</h1>
  <p>My first paragraph</p>
</body>
</html>
```

Now go to Documents (Windows) / Finder (Mac) > "Open `index.html` in Chrome".

You should see your very first website!

## Welcome to HTML
HTML, or Hypertext Markup Language, defines the basic structure and content of every website.

### &lt;h2&gt;Elements, Tags, and Attributes&lt;/h2&gt;

To define the structure of a website, HTML uses tags:
```html
<some-tag>stuff that goes inside</some-tag>
```
This is an HTML element.

HTML follows grammar rules, which in programming we call `syntax`.
Tags pretty much always occur in pairs: an opening tag followed by a closing tag.
We'll see shortly that tags can be "nested" inside one another to contain each other.

Aside from the tags shown here, we also have `div`. A `div` element is used as an all-purpose container.
Let's list a couple projects!
Add the following below the line for your first paragraph:
```html
<div class="project">
  <h2>Project 1</h2>
  <p>My First Website</p>
</div>
<div class="project">
  <h2>Project 2</h2>
  <p>My Second Website</p>
</div>
<div class="project">
  <h2>Project 3</h2>
  <p>My Third Website</p>
</div>
```
Notice the new `class="project"`.

HTML elements can have `attributes` to describe them. Each attribute consists of a `name` and a `value`.
The `class` attribute lets us group related elements together so we can do the same things on all of them.

## CSS Beauty Makeover

Create a new file "File > New File" and name it "styles.css". Save it in the same folder as "index.html".

Inside the new file, put:
```css
body {
  font-family: "Helvetica";
}

h1 {
  color: blue;
  font-size: 40pt;
}

.project {
  background-color: orange;
}
```

### Language #2: CSS
Cascading Style Sheets

Now let's get into the real makeover.

### Selectors
Replace the previous code with this:

```css

```

`class, id, tag`

Change up the text in your HTML elements to fit the website, too.

Let's add a background image!
Set the background image for `body` using
```
  background-image: url("http://kriswhitewrites.com/wp-content/uploads/2013/06/landscape-mountains-snow-sky.jpg");
```
Now make all the font white. (Hint: use the "color" attribute.)

## Adding interactivity with Javascript (not Java!)

Javascript is a programming language that's recently taken the developer world by storm. Here, we're going to use it to make a button for adding new projects.
Create a third file called "scripts.js".
