# Game Dev
## Part 1: Drawing on the Screen
<a href="README.md#instructions">Back</a>

### How do game visuals work?

In games and movies, it looks like the things on the screen on the moving. The movement you see is actually just a bunch of images with small changes played back in rapid succession, and then your brain interprets this as movement.

In general, a minimum **frame rate** of 18 frames per second is required for images to appear to be moving to the human brain. Games tend to run at higher rates of 30 or 60 frames per second because reaction time can be central to gameplay, making it important to have more detailed motion. This means that 60 times per second, the computer will run a program to recalculate the new positions of objects, draw an image, and display it on the screen.

### Setting up our game's visuals

In your Javascript editor, you should see the following lines of code to start with:

```js
function setup() {
}

function draw() {
}
```

In p5.js, everything happens inside these two functions. `setup()` is called once at the beginning to make the canvas and define sprites you want to be on the screen initially. `draw()` is called every frame of the game to update the state of the objects in the game and the pixels on the canvas.

Inside `setup`, we can define the size of the canvas with `createCanvas(width, height)`. A decent starting size might be something like 500 pixels by 400 pixels. (Note: if you have a retina display, one "pixel" might be more than one physical pixel in reality because your browser keeps the size of a "pixel" consistent across devices.)

So, how do we add some objects into the game?

Objects in the game are called **Sprites**. You make them like this:
```js
var myObject = createSprite(x, y, width, height);
```

To specify where on the screen an object will be rendered, we refer to its location in x and y coordinates, where `x == 0` on the left side of the canvas, `x == width` on the right, `y == 0` at the top, and `y == height` at the bottom. For those familiar with Cartesian planes from math class, this isn't quite what you're used to, since the y values get more positive as you go down instead of the getting more negative. The x and y coordinates specified when creating a sprite will be the center of the sprite.

In `draw`, we can call `drawSprites()` to render the sprites we made to the screen.

Let's start by adding some ground and a character we will play as onto the screen. Modify your code to look approximately like this:

```diff
+var player;
+var ground;
 function setup() {
+  createCanvas(500, 400);
+  player = createSprite(300, 200, 30, 30);
+  ground = createSprite(200, 300, 250, 30);
 }
 
 function draw() {
+  drawSprites();
 }
```

When you press **Run** in the toolbar on Codepen, you should see your sprites render!
<img src="screenshots/1-sprites.png" />

You'll notice that every time you hit Run, the colours change. p5.js picks random colours for you if none are specified, so let's specify our own. The canvas can be coloured with `background(red, green, blue)` and sprite colours can be changed with `sprite.shapeColor = color(red, green, blue)`. The parameters `red`, `green`, `blue` are integers from 0 to 255 that specify how much light of each component colour should be mixed to create the overall colour. You might find <a href="http://www.colorpicker.com" target="_blank">colorpicker.com</a> useful for finding RGB values to put in these. Your code should look something like this now:

```diff
 var player;
 var ground;
 function setup() {
   createCanvas(500, 400);
   player = createSprite(300, 200, 30, 30);
+  player.shapeColor = color(255, 0, 0);
   ground = createSprite(200, 300, 250, 30);
   ground.shapeColor = color(80, 180, 100);
 }

 function draw() {
+  background(255, 220, 180);
   drawSprites();
 }
```

### Interactivity and motion

Next, let's add some interactivity and movement.

We initialized each sprite with a location, and this location is accessible through `sprite.position.x` and `sprite.position.y`. If we wanted to make the sprite move every frame, we could update the coordinates of the sprite each frame with something like `sprite.position.x += 5;`, but there is a better way.

We have access to a velocity vector for each sprite either by setting `sprite.velocity = createVector(x, y)` or through `sprite.velocity.x` and `sprite.velocity.y`. The velocity vector is automatically added to the sprite's position vector every frame. In `setup`, try telling our game that the player has an initial velocity:

```js
player.velocity = createVector(0, 3);
```

When this code runs, you should see the player begin moving down! You can rerun your code or reload the page (after saving!) to reset the game's state. Let's make this be triggered by a keypress instead of all the time. Inside the `draw` function, you have access to two kinds of key press listeners:

- `keyDown(key)`: This will return true if the key is currently down on a given frame.
- `keyWentDown(key)`: This will only return true if the key went down on the current frame, and will be false if the key is down but has been held from a previous frame.

Let's make our character move when arrow keys are pressed by setting the horizontal component of its velocity. When the up arrow goes down, we can set a negative value for the vertical velocity to simulate jumping.

```diff
 function draw() {
   background(255, 220, 180);
+  
+  if (keyDown('RIGHT_ARROW')) {
+    player.velocity.x = 4;
+  } else if (keyDown('LEFT_ARROW')) {
+    player.velocity.x = -4;
+  } else {
+    player.velocity.x = 0;
+  }
+  
+  if (keyWentDown('UP_ARROW')) {
+    player.velocity.y = -6;
+  }
+  
   drawSprites();
 }
```

Now you should be able to move left and right, and fly up in the air when hitting the up key. There's no gravity yet, we're going to do that next! You can rerun the javascript or save and reload the page to reset the game.

<a href="https://github.com/molleindustria/p5.play/blob/965e2fc5af0335f0abd6ca362ca70dc205c8f40c/lib/p5.play.js#L502-L593" target="_blank">Here is a list of possible values for the `key` parameter</a> in case you want to attach events to different kinds of key presses.

### What you should have so far
Here's a link to a Codepen project completed up to this step: http://codepen.io/davepvm/pen/NNWyrV?&editors=0010

<a href="part2.md">Part 2: Gravity and collision detection</a>

<a href="README.md#instructions">Back</a>
