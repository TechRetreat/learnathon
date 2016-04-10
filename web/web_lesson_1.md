## Your First Flask Website

Flask is a web framework that lets you write web apps in Python. We still use HTML, CSS, and Javascript, but a lot of the rest of the work is done in Python and there are now Python tools to simplify web development, like the Python templating engine Jinja2.

#### Installs
To avoid interfering with your __local__ Python setup, it would be a good idea to install `virtualenv` (short for "virtual environment"). This is so that everything we mess around with relating to Python is contained inside of today's project.

Once you've downloaded `virtualenv`, run the following command to create a new virtual environment in your current directory:

###### Windows
<command here>

###### Macs

###### Linux

Now to install Flask. You can install it [here](). <TODO: additional instructions>

### Initial commit
<when to introduce Github?>

Flask requires some basic setup files.

_app.py_:
```python
from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello():
  return "Hello World!"

if __name__ == '__main__':
  app.run()
```

_requirements.txt_:
```text
Flask==0.10.1
gunicorn==19.4.5
```

_runtime.txt_:
```
python-3.4.2
```

__Protip__: Notice that the following file doesn't have any extension.
You can create files through your command prompt / terminal with the command `touch <filename>.<extension>`

eg.
`touch Procfile`

You can also edit files in the `command line` with one of the pre-installed editors like `emacs`, `vim`.

eg.

```
vim Procfile
I
web: gunicorn app:app
Esc
X
```

You can see the contents of the file now with `cat Procfile`.


_Procfile_:
```
web: gunicorn app:app
```

To see this website in your browser, run
`python app.py` in the terminal.

This tells python to run the file `app.py`.
Now the last two lines in `app.py` come into play, because `app.py` is the main file running right now, so `app.run()` is called and the app is run.

##### Checkpoint!

Save your progress so far with
```
git init
git add .
git commit -m "Initial commit"
```

## The Move to Flask

It's time to translate our previous website to a Flask app.

Copy over all the files we had before.
If you rerun the website now (TODO: can refresh?), you won't see any changes because Flask doesn't know about the files you just added. They're just sitting in the same directory.
To tell Flask to `serve` the `index.html` file and other files, you'll need to work with Flask's routes.

### Routing

All of this happens in `app.py`.
The [TODO] function can return text, like `return "Hello world!"` earlier. It can also return an HTML template, using the package `render_template`.

Include `render_template` by changing the first line of `app.py` to:
```python
from flask import Flask, render_template
```

Here's a route:

```python
@app.route('/<url>')
def hello():
  return render_template("<filename>.html")
```

__Task 1__: Route the url '/' to index.html, and route the url '/about' to about.html. [TODO: methods]

(Ruby on Rails uses routing, too.)

### Templating Engines

Now instead of `hardcoding` the url links in your HTML templates, you use the `url_for()` method to get the right url.
This way, certain [TODO: specify] changes you make in the future won't break anything, because `url_for()` will just return the new url.

`url_for` works by going off of a standard structure for your app. All of the HTML templates go in a folder called templates.

Here's what a basic project structure looks like:

```
/yourapplication
    /yourapplication.py
    /static
        /style.css
    /templates
        layout.html
        index.html
        login.html
        ...
```
source: <http://flask.pocoo.org/docs/0.10/patterns/packages/>

Images, JS (Javascript) files, and CSS stylesheets are `static`: the files themselves don't change when the website is run.
HTML goes under templates, since it lays out the basic structure. It's then served to the `client` (a user's local computer), then modified to bring it to what it should look like, and then possibly further modified as users interact with the website.

### Rerun of the client server model

### Back to routing

Jinja2, the templating engine that comes with Flask, lets you write some useful Python code.
Any Python code inside an HTML template is specified with double curly brackets `{{<your code here>}}`.
Now you can call `url_for()`.

Here's an example of what the link to `scripts.js` should now look like:
```html
<script src="{{ url_for('static',filename='script.js') }}" type="text/javascript"></script>
```

Try rerunning your website with `python app.py`. The `console` will give you hints if things go wrong: a 404 file not found (TODO: get details on what console output looks like) means your url might not have been updated properly. The console will also tell you where the error occurred so you can find it.

###### Testing - Houston, we have a problem

In general, software development is accompanied with testing. Eg. `unit tests` will check that one part is working properly, while `regression tests` make sure that any new changes you made didn't break the old stuff.
For an example of what implementing
There's even `test driven development (TDD)`, where you write the test cases first and then write the minimal code to pass the tests.
Testing is great and makes you look less like a mad coder that hacks maniacally through the night on 50 cups of caffeine whose project is secretly ridden with bugs under the hood. [edit phrasing]

##### Images, Glyphicons, and SVGs

<text>

[TODO: use mouseenter]
```javascript
$(document).on("mouseenter", "img", function(event){
   $( this ).attr("src", "/static/heart-green-filled.svg");
}).on("mouseleave", "img", function(event){
   $( this ).attr("src", "/static/heart-green.svg");
});
```

## Fancy Routing and Templating

You can organize all your HTML templates by putting the project templates into their own subdirectory, so the project structure now looks like this:

```
/yourapplication
    /static
        heart.svg
        heart-filled.svg
        script.js
        styles.css
    /templates
        /project
          1.html
          2.html
        index.html
  app.py
  Procfile
  requirements.txt
  runtime.txt
```

Now for the slick part: dynamic routing.

```python
@app.route('/project/<id>', methods=['GET', 'POST'])
def asdf(id):
    # return "/project/_"+id+".html"
    comments = Comment.query.all()
    return render_template("/project/"+id+".html", comments=comments)
```
<TODO: can require id to be a number> <TODO: retitle asdf method>

#### AJAX, the HTTP REST Protocol, GET, POST, and more
<all the acronyms>
The comment form no longer works.
You might have seen this message in the console when trying to submit a comment:

```html
get output message
```

When a user submits a form, this POSTs the information to your website in the form of a POST request.
The default method allowed when routing is [TODO].

To fix this, specify the methods in a route like so:

```python
@app.route('/my_url', methods=['GET', 'POST'])
```
