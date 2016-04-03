# Game Dev
## Part 4: Adding points and lives
<a href="README.md#instructions">Back</a>

### Resetting the game
Right now, if you fall off the screen, that's it - you have to reload the page in order to continue testing anything. We want the level setup to reset when you fall off the screen. If the setup was in a function, we could just call it again, but right now that all happens in `setup`, which we don't want to call manually because it's a special function to p5.js. Additionally, we use `setup` to define classes. We don't need to redefine classes every time the player dies. To solve this, we can assign a function in `setup` to set up a level and then call it again whenever we need. This includes calling it once immediately after we define it to set up the game initially.

To reset the game when the player falls offscreen, we need to define `Player.prototype.recalculate` since it needs to do some more work than what `PhysicsObject.prototype.recalculate` already does. In our definition for the `Player`, we still want it to run `PhysicsObject`'s `recalculate` instead of completely defining a new one. We can do this by manually running it as the first line of our new definition with `PhysicsObject.prototype.recalculate.call(this)`. Then we just need to check if `this.position.y` for the character is greater than the screen height, and if so, call `reset()`.

We also want to get rid of all the existing sprites on the screen since we will be making everything over again in `reset`. An easy way to do this is loop through each element in `allSprites` and call `remove()` on it. This method is built in to p5play.js and will automatically remove the sprite from any groups it may belong to.

Here's how we want the new setup to look:
```js
// var solids, physics, ...
var reset;

function setup() {
  // define classes
  // Player = function ...
  Player.prototype.recalculate = function() {
    PhysicsObject.prototype.recalculate.call(this);

    if (this.position.y > maxHeight) { // or however else you want to do this check
      reset();
    }
  };

  reset = function() {
    allSprites.forEach(function(sprite) {
      sprite.remove();
    }); // remove things from the screen

    // create the player, ground, etc
  };
  reset(); // Call the function to restart the level
}
```

While we're at it, in your game, you might have other objects that can fall offscreen. We don't care about them once they're gone, so in the definition of `PhysicsObject.prototype.recalculate`, is would make sense to call the sprite's `remove` method. 
```
if (this.position.y > maxHeight) {
  this.remove();
}
```

### Creating lives
Keeping track of lives is a pretty small change now. Declare a variable called `lives` and in `setup` (but not inside `reset`!) set it equal to however many lives you want to start with. Then, in the player's `recalculate` function, before calling `reset`, set `lives` equal to its old value minus one.

We want to display this information on the screen. There are a few different ways to do this, such as drawing hearts that disappear as you lose lives. Another simpler way is to write text on the screen telling you how many lives you have. (Drawing hearts could be a fun extension to do later!)

In p5.js, text isn't represented by a sprite, it just draws directly on the screen. This means that we will have to write the number of lives onto the screen after we have finished rendering everything else at the bottom of `draw` so that nothing gets drawn on top of it, covering it up.

When drawing directly on the screen, you have to set up the "pen" before doing the drawing. In the case of text, we need to set the font size, fill color, and text alignment before calling `text(words, x, y)`.

A simple lives counter might look like this:
```js
function draw() {
  // ...main game logic
  drawSprites();

  textSize(14);
  fill(0,0,0);
  textAlign(LEFT, TOP); // This makes the x and y function as the top left corner of the text
  text("Lives: " + lives, 10, 10);
}
```

If you're interested, you can specify a width and height after the x and y of the text and use `CENTER` as a text align parameter. More info on that can be found in the <a href="http://p5js.org/reference/#/p5/text">p5.js docs.</a>

This works, but doesn't limit our lives at all. If you keep falling off, the life counter goes to zero and then into the negatives. To prevent this, let's make a simple Game Over screen.

Declare a function called `gameOver`, and under our definition for `reset`, we'll define what we want to happen when `gameOver` is called. We'll want to create a button for you to press to start the game over again. p5.js provides us with a `createButton(buttonText)` function to use to make an HTML `<button>` element. This will seem a little weird at first since the rest of our game so far has been about drawing pixels onto a canvas, and now we're adding in HTML elements. Here's what's different between our existing model using sprites and buttons created with `createButton` (or other functions from p5.dom):

- The HTML element created sits on top of the canvas, so sprites can't be drawn on top of the HTML element.
- The position of elements is set with `element.position(x, y)` instead of assigning a vector. The position can be retrieved with calling `.position()` with no arguments (note that it's a method and not a property, so you need the brackets!) and returns an object `{x: something, y: something}` instead of an instance of the Vector class.
- The element won't be part of `allSprites`, so we have to keep track of it ourselves by assigning a variable to it. It must be forcibly removed from the screen with `element.remove()`.
- Elements only respond to **events** instead of doing calculations every frame. This means we have to pass functions as arguments to event listener methods on elements. For example: `mybutton.mouseClicked(function() { console.log('I was clicked!'); })`
- Elements are not styled using pixel-level styling like on the canvas, they use CSS attributes set by `element.style(attribute, value)`. CSS is a whole other can of worms. The <a href='https://developer.mozilla.org/en-US/docs/Web/CSS/Reference'>Mozilla Developer Network CSS Reference</a> is a good source if you want to learn more in general, but for small things like styling buttons, feel free to ask a mentor.

So, with all that in mind, here's what our function looks like:
```js
gameOver = function() {
  allSprites.forEach(function(sprite) {
    sprite.remove();
  }); // remove things from the screen

  var button = createButton('Try again');
  button.position(x, y); // the position is up to you
  button.mouseClicked(function() {
    lives = 5;
    reset();
    button.remove();
  });
};
```

Then, in `Player.prototype.recalculate`, after decrementing `lives`, call `gameOver` if there are no lives left, and `reset` otherwise.

You may also want to change the background and write text to the screen or something if the game over screen is present. Remember, the canvas is still calling `draw` every frame, even if there are no sprites to draw. We can take advantage of this and wrap everything in the `draw` function in a big if statement:
```js
function draw() {
  if (lives <= 0) {
    // maybe make the background black and write 'game over'
  } else {
    // put everything else in here
  }
}
```

### Creating points

Similar to creating lives, we can declare a `score` variable and initialize it to 0 in `setup`. We also will want to declare a variable `coins`, which we'll initialize to a `new Group()` to hold all of the coins on the screen. We'll then make a new subclass of `Block` called `Coin`, and in its constructor, we'll add the new instance to the `score` group.

```js
Coin = function(x, y) {
  Block.call(this, x+5, y+5, 20, 20);
  this.shapeColor = color(255, 130, 0);
  coins.add(this);
}
```

Then, in `Player`'s `recalculate` function, we can loop through the coins and check if any are being touched. If they are, we want to:
- remove it from the screen with `coin.remove()`
- increment the score

An important note about using a `forEach` loop in a class function: `this` gets reassigned to `window` inside the callback unless you pass in a value for `this` as another function argument. Here's what I mean:
```
SomeClass = function() {
  this.thing = "something";

  [1, 2, 3].forEach(function(i) {
    console.log(this.thing); // undefined
  });

  [1, 2, 3].forEach(function(i) {
    console.log(this.thing); // "something"
  }, this); // <--- because we passed in a value for `this`
}
```

(As an aside: this isn't a common pattern in Javascript, so not every function needs `this` passed in in this way. The fact that `this` can be reassigned and that there isn't as much consistency as there could be is one of the biggest problems people tend to have with Javascript.)

Then in the `reset` function, we can clear out the `coins` group, and reset the score to zero.

### What you should have so far
Here's the game up to this point, in case you fall behind: http://codepen.io/davepvm/pen/MyoXox?editors=0010

<a href="part5.md">Part 5: Scrolling the camera</a>

<a href="README.md#instructions">Back</a>
