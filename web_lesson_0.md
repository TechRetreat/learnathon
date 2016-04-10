## Hello, World (Wide Web)

Long, long ago, when the great Tim-Berners Lee brought us the WWW,

Clients and servers

Model
View
Controller

And that brings us to...

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

Javascript is a programming language that's recently taken the developer world by storm (You may have seen Angular JS, Node JS, React JS, Meteor JS, Ember, Backbone, ... the list goes on).

<TODO: insert more about Javascript>

Here, we're going to use Javascript to make a clickable button for adding new projects.
Create a third file called "scripts.js".


### Manipulating the DOM

Of course, the world isn't powered by many code monkeys modifying the HTML every time someone adds a new comment or post.

<More about the Document Object Model>

Computer scientists and programmers are fanciful people. The Document Object Model is one example of a `tree` of `nodes`. A node can be a `root`. Otherwise, it has ancestors, and specifically, `parents`. A node can also have `children`, unless it's a `leaf`.

You can modify the DOM tree `dynamically` with the web's favourite language - Javascript - using methods named after the terms we just described, like `.appendChild`. [edit]

`.createElement`
`.appendChild`
`.getElementById`
`.getElementsByClass`
etc.

Remember this keyword, `dynamic`, especially when searching StackOverflow.

### JQuery

Adding new elements was messy. There's a more convenient way to do all this.

## Fleshing out the website

Let's start filling in the content.

__Task 2:__ Turn each of the project divs into a clickable link to an HTML template titled `project_<project number>.html` (eg. "project_1.html").

__Task 2.5:__ the Navbar and About page

__Task 3:__ Your turn now: Fill in `project_1.html`!

## The Comment Thread

Every good post has a place for its audience to discuss the post. [edit: rephrase]

Whether it's a contact form so loving employers can drop you a line or a submission form for content, HTML has an element just for forms: <form></form>. [edit: rephrase]

Example usage:
```html
<form class="" id="comment-form" action="" method="post">
  <label for="name">Name</label>
  <br>
  <input type="text" name="name" id="name" value=""/>
  <br>
  <label for="comment">Comment</label>
  <br>
  <textarea name="comment" id="textarea" rows="8" cols="40">comment here</textarea>
  <!-- <input type="text" name="comment" value=""/> -->
  <input type="submit" name="submit-comment" id="submit-comment" value="Comment"/>
</form>
```
(edit: maybe don't give the exact code)

__Task 4:__ Now edit the `script.js` file. Manipulate the DOM so that Internet users can add comments to your site dynamically.
To do this, you'll want to write the functions `addComment`, and `bind` the `submit` action for the `#comment-form` to code that calls `addComment`.
You can get the values stored in the HTML form's `fields` with `<object>.val()`.
Remember that JQuery provides convenient selectors, so if your HTML input element has the id `#foo`, you can get its value with `$("#foo").val()`.

### Good form (no pun intended)

Here's an example final implementation:
```javascript
$("#comment-form").on("submit", function(e) {
  e.preventDefault();
  var name = $("#name").val();
  $("#name").val("");
  var comment = $("#textarea").val();
  $("#textarea").val("comment here");
  if(validate(name) && validate(comment))
    addComment(name, comment);
});
```

Notice the additional function `validate`. This is important!
Source: relevant xkcd comic. (little bobby tables)

Try this: put the below input into the comment field (you can just leave the name field blank)
```javascript
TODO!
fill_me_in()
```

Real-life production code should never allow this, or else users could ~~take advantage of this security flaw to~~ run malicious scripts.

### Animations and Effects with HTML and CSS and Javascript and JQuery



## Sorry

When you reload your page, all the comments disappear. All the posts you added disappear. Not even Ctrl+S can _save_ you.

Unfortunately, this is the end for My First Website 2.0.

What we're missing is a `database`. This is a persistent storage that lives elsewhere. Every time someone creates a new comment or post, this information is stored in the database and loaded up again when a user loads the website.

Web apps really have three layers:

- the presentation layer
- the

[edit below section]
You could use PHP, which is a `server-side` `scripting` language. But goal #2 of this learnathon is to get you started with web development in 2016, and introduce you to web frameworks.

And anyway,

This is why developers use `web frameworks`, like Django, Ruby on Rails, Flask,

## Recap

HTML
CSS
Javascript

## Onto Flask!
