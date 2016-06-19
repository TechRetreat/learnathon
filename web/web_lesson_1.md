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

This is also a good time to install PostgreSQL.
Install Flask-Migrate with pip install Flask-Migrate

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

<Mention localhost>

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

## Heroku

So far, your website can only be viewed on your computer. So much for "web"site. :( Now that it's a Flask app, we can put our app on __Heroku__.

Heroku is a Platform as a Service (PaaS) - aka it provides hosting for web apps.

Normally, you need to purchase a `domain` and [elaborate].

We also had another option before, which is Github Pages. This is free but it's only for `static` sites and not web applications. It's a good option to keep in mind though, eg. for a personal website!

Anyways (and most importantly), Heroku provides free hosting (for up to five web apps, and the websites can't be running all the time, but hey, free hosting).

Here's the link to create a Heroku account ().

<TODO: find a Heroku guide>

Once you've created a new app in the Heroku dashboard, you should select the app, navigate over to the __Deploy__ tab, and choose __Github__ as the __Deployment method__.

From there, you can type in your Github repository name into the input field and connect your new Heroku app to the repository.

If you now go to Settings, you can find the url for your app.

## Databases

A database stores information, although how it stores data varies.

Just like there are many programming languages, there are a couple `database management systems`. Some examples are MySQL, SQLite, PostgreSQL, MongoDB, Oracle.

There are three (two?) main camps: `relational` databases, `object-oriented` databases, and `NoSQL`. [TODO: fact check!]

Relational databases are really the main type of database. Virtually all relational database management systems (`RDBMSs`) use `SQL` as their language, which is why so many of the examples given had 'SQL' in their name. <https://en.wikipedia.org/wiki/Relational_database whoops, even copied the exact phrasing>
(As you can guess from the lack of 'SQL' in the name, MongoDB isn't an RDBMS.)

We'll be using PostgreSQL, which is actually in between a `object-oriented` DBMS and a `relational` DBMS. (It's an `object-relational` DBMS, or, `ORDBMS`.)

A relational database stores the data in `tables` of `rows` and `columns`:
- every column is a field
- every row is another data entry

An object-oriented database stores data as `objects`.

<TODO: Object-relational database>

`SQL` stands for `Structured Query Language`, because you do two things with a database: store information in a database, and `query` the database to get its information.

A SQL query looks something like the following:

```sql
SELECT * AS older_customers FROM customer_table WHERE age > 20
```
> this means select all "customers" (technically these are data entries in the database and not actual people) over the age of 20, and call this selection of customers "older_customers".

Note: the web app won't use SQL because there's a Python package called SQLAlchemy that can interface with the database and lets us execute SQL queries in Python. But the basic idea of selecting data from database tables with conditions is relevant.

### Databases with Heroku

Heroku lets you "provision" your web app with a database, but it only supports PostgreSQL for Flask. <IIRC>

To provision your app, follow the instructions at (Heroku guide)

###### If you haven't got PostgreSQL installed properly yet
No worries: the Heroku database is on the Heroku servers, and the only reason for installing PostgreSQL locally (on your computer) is so that you can play with a local database.

All Python installations come with SQLite, another database management system. You could play with that instead, but in the web application it's better to use the same database management system that Heroku's using or you'd run into messes when pushing to the Heroku servers.

[commands to create a local SQLite database for demonstration purposes]

###### If you do have PostgreSQL installed and working

[commands to create a local PostgreSQL database for demonstration purposes]
```sql
psql
\d
\c
\l
\?
\q
select * from table_name; <-- need the semicolon!
```

## Connect to the database from a Flask web application

Underneath `app = Flask(__name__)` in `app.py`, <TODO: keep everything in app.py?> add the following:

```python
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = os.environ.get('DATABASE_URL',
 'postgresql://localhost/techretreattemp')
db = SQLAlchemy(app)
```

This `configures` the `SQLALCHEMY_DATABASE_URI` variable by setting it to the `path` of your database. Aka it tells Flask where the database it should use is.
If `DATABASE_URL` isn't set, it'll fall back to the default. In the code above, this default is 'postgresql://localhost/techretreattemp' - the database stored locally on your computer.

When you run the web app locally with `python app.py`, Flask uses the default local database.
When Heroku runs the web app, `DATABASE_URL` is set to the database that Heroku provides so Flask uses that one.

## Storing comments

Since we're using an `object-relational DBMS`, we work with objects.
First, define what a Comments object is by creating a `class`.

We'll say a Comment has:
-

<In object-oriented programming, TODO>

In `app.py`:
import SQLAlchemy with

```python
from flask.ext.sqlalchemy import SQLAlchemy
```

```python
class Comment(db.Model):
    __tablename__ = 'comments'

    id = db.Column(db.Integer, primary_key=True)
    poster = db.Column(db.String())
    comment = db.Column(db.String())

    def __init__(self, poster, comment):
        self.poster = poster
        self.comment = comment

    def __repr__(self):
        return '<id {}>' .format(self.id)
```

### Database Migrations
Database migrations are changes to your database that are packaged and recorded down so that you can undo and redo database changes. [check this description...]

We'll add a Python script that allows us to manage the migrations. [do they even know what a Python script is?]
```python
import os
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_script import Manager
from flask_migrate import Migrate, MigrateCommand
from app import app, db

app.config['SQLALCHEMY_DATABASE_URI'] = os.environ.get('DATABASE_URL',
 'postgresql://localhost/techretreattemp')

migrate = Migrate(app, db)

manager = Manager(app)
manager.add_command('db', MigrateCommand)

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(128))

if __name__ == '__main__':
    manager.run()
```

We'll also add the file `migrations/env.py` [insert file]
What does all this code mean?
[description]
But in short, I got it from the official documentation example: https://flask-migrate.readthedocs.org/en/latest/
Moral of the story: documentation is your best friend. Stack-Overflow is your second.

##### .gitignore & More Command Line!

Git tracks files, including a lot of automatically generated files that aren't supposed to be kept track of. To exclude those, your repository has an invisible `.gitignore` file.
You can edit it in the terminal/command line:

> vim .gitignore

- this will open up a primordial text editor inside your terminal/command line window
Here are the commands: (good luck)



or you can just
> atom .gitignore

to open it in Atom. (atom <3)
Make sure you navigated to the right directory in the terminal/command line [ / shell?? ] before you type these. To check where you are, you can use the command
> pwd

We won't need all the files in the `venv` folder, since those are all the python requirements [libraries?] downloaded by running ` ` <insert command> and Heroku will run that command itself to create its own `venv` folder.

# Comments!

This is the lifecycle of a comment:

Alice types a comment

This is the `form`:
________________
<sub>name: <sub>

<sub>comment:<sub>

              <sub>Submit<sub>
________________

She submits it.

--> The request goes to our server
with all the information we specified:
- name
- comment

<New!> Now we're going to save it permanently in our database.

# Objects

We're saving a comment.
In our words, a comment is:
a _string_ (the name)
and another _string_ (the comment)



# __Model__, View, Controller

Fields:
- id
- poster
- comment

Mandatory methods:
__init__

__repr__

## HTTP Requests
We create a new `route` called `store_comment`.
The HTTP method for this is `POST`, because we're passing information.
To get this information, we can find it in
```py
request.form['poster'] # the poster
request.form['comment'] # the comment
```
Now we create a `Comment`, passing the poster and comment which will call the special `__init__` method into action.
To add it to the database, we call
```py
db.session.add(<name of new Comment>)
db.session.commit() # submits the comment to the database
```
lmao I did this already

### Security

### Updating requirements.txt

[Add gunicorn as well]

### Remote migration

## Showing comments

To show the comments, we need to update the HTML in `templates/project/1` to show all the comments we have so far.
However, the HTML file doesn't have the comments. We can get all the comments using python in our route method in `app.py`, and then pass them to the HTML with the `render_template` method.

```py
comments = Comment.query.all()
return render_template("/project/"+id+".html", comments=comments)
```

In the HTML file, we want to add all the comments. This needs a `for loop`.
HTML isn't a programming language - it just defines the structure and content of a website - but we can `embed` [programming ] in it using Flask's `templating engine`.

```html
{% for comment in comments %}
<div class="comment"><img src="{{ url_for('static',filename='heart-green.svg') }}" class="like"><div class="poster">{{comment.poster}}</div><div class="comment-text">{{comment.comment}}</div></div>
{% endfor %}
```

## Make the like button work!
When a user __clicks__ the button # jQuery & javascript
make the number inside it go up by one # jQuery & javascript - DOM manipulation
When a user __hovers__ over the button # CSS
change the button image to the filled in button image # CSS

## Add an about page to spruce up the website
