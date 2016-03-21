# Game Dev
## Part 3: Different kinds of blocks using inheritance
<a href="README.md#instructions">Back</a>

### Intro to classes and objects

A lot of our code so far has been involving the character sprite and ground sprites directly. However, there's a lot that's similar between them, and there's a lot that will be similar if we decide to create other kinds of blocks too, such as a ground block that bounces you extra high. All these different kinds of objects in the game can be considered parts of general categories of game objects, similar to how a square is a more specific kind of rectangle, which is a more specific kind of polygon, and there are other specific types of polygons such as triangles.

In programming we refer to these categories as **classes**, and a more specific version of a category is referred to as a **subclass or child class** of the **parent or super class.**

In Javascript, classes are implemented using **functions and prototypes.** A **class function** is used to initialize an instance of a class, and a **class prototype** defines what methods instances of the class have. A **base class** refers to the top-level category that other classes are all children of at some level. The polygon example might look like this:

```js
// Polygon will be the base class
var Polygon = function(width, height) {
  this.width = width;
  this.height = height;
};
Polygon.prototype.getArea = function() {
  return 0; // As an arbitrary Polygon, we don't actually have enough info to know the area
};

// Make a Rectangle, which is a child of Polygon
var Rectangle = function(width, height) {
  // This code calls the Polygon constructor with parameters `width` and `height`
  // but sets it up on `this` instead of making a new object
  Polygon.call(this, width, height);
};
// Make Rectangle a subclass of Polygon by inheriting Polygon's prototype
Rectangle.prototype = Object.create(Polygon.prototype);
Rectangle.prototype.constructor = Rectangle; // This makes `somerect instanceof Rectangle === true`
// Then, redefine the `getArea` method to be Rectangle-specific
Rectangle.prototype.getArea = function() {
  return width*height;
}

// Make a Triangle, which is a child of Polygon
var Triangle = function(width, height) {
  Polygon.call(this, width, height);
}
Triangle.prototype = Object.create(Polygon.prototype);
Triangle.prototype.constructor = Triangle;
Triangle.prototype.area = function() {
  return width*height/2;
}

// Make a Square, which is a child of Rectangle
var Square = function(length) {
  // Pass `length` into Rectangle's constructor as both width and height
  Rectangle.call(this, length, length);
}
Square.prototype = Object.create(Rectangle.prototype);
Square.prototype.constructor = Square;
// We don't need to redefine the area method since the one from Rectangle works for Square too



// Making a new square
var s = new Square(5);
console.log(s instanceof Square); // Logs `true`
console.log(s.getArea()); // Logs `25`
```

If you want to learn more about how prototypes work and what all this `.call` stuff is, <a href='http://tobyho.com/2010/11/22/javascript-constructors-and/'>here's a great artical about it.</a>

### Refactoring our game to use classes

When programming, a restructuring of code to make it easier to add new features (or cleaner, easier to use, or different in general) is called a **refactor.** We're going to do one of those now by making classes for our game objects instead of treating each one as its own unique case. All of our game objects were originally made with `createSprite`, which returns an instance of the p5.js `Sprite` class, so it makes sense to use that as our base class. Because `Sprite` is from p5.js, we can only access it with in `setup` or `draw`, so like other game elements, we have to declare it outside both, but define it in `setup`.

There's a little bit of extra work that `createSprite( ... )` does behind the scenes to make it more useful than `new Sprite( ... )`: it manages depth for you. Internally, it stores a group of all sprites, so when you call `createSprite`, it calls `new Sprite(x+width/2, y+width/2)` to make its coordinate be in the center, adds it to `allSprites`, then sets `sprite.depth = allSprites.maxDepth()+1`. We want to build this in to our base class so that we don't have to worry about depth on our own.

There are a few methods that we want all game objects to have, but let specific types provide their own definitions for:

- `recalculate()`: This will be called to let each object recalculate its position each frame (for example, add gravity to its velocity)
- `collideWith(target)`: This will be called in the collision detection loop after we have found a collision and moved the objects apart, allowing us to do something afterwards such as reset velocity
- `interact()`: This will be called every frame after everything else, allowing each object to listen for user interaction such as keyboard events and react to them

To start you off, this is what the base class might look like:

```js
var Block;
// ...

function setup() {
  // Start each block with a position and size
  Block = function(x, y, w, h) {
    Sprite.call(this, x+w/2, y+h/2, w, h);
    this.depth = allSprites.maxDepth()+1; // We don't have to define allSprites anywhere, p5.js makes it
    allSprites.add(this);
  }
  Block.prototype = Object.create(Sprite.prototype); // Inherit all of Sprite's methods
  // These methods are all empty for now. Subclasses will actually do stuff.
  Block.prototype.recalculate = function() {  };
  Block.prototype.collideWith = function(target) {  };
  Block.prototype.interact = function() {  };

  // ...
}

```

We'll want to make a `PhysicsObject` class which a `Player` class will extend instead of directly making a `Player` class in case we want other things to be able to fall to the ground and have physics. It might have a definition that looks like this:

```js
PhysicsObject = function(x, y, w, h) {
  Block.call(this, x, y, width, height);
  this.gravity = createVector(0, 1); // If we make gravity owned by each object instead of global,
                                     // we can edit it per object if we want for some cool effects.
                                     // Up to you.

  solids.add(this); // Add the new object to the solids sprite group
  physics.add(this); // Add the new object to the physics sprite group
}
PhysicsObject.prototype = Object.create(Block.prototype);
PhysicsObject.prototype.constructor = PhysicsObject;
PhysicsObject.prototype.recalculate = function() {
  this.velocity.add(this.gravity);
};
PhysicsObject.prototype.collideWith = function(target) {
  if (this.touching.bottom) {
    this.velocity.y = this.gravity.y;
  }
  // ...and the rest of the post-collision code goes here,
  // but we can use `this` and `target`
};
// We don't need to define `interact` because a generic
// PhysicsObject has no interaction with the player
```

Now, try making the rest of the classes yourself:
- `Player = function(x, y)`
  - It should pass a width and height to the parent constructor automatically
  - Set `this.shapeColor` in the constructor
  - The only thing it needs to redefine from `PhysicsObject` is the `interact` method to control jumping and horizontal movement
- `Ground = function(x, y, w, h)`
  - Set a color here too
  - It should add its sprite to the solids sprite group
- `SuperJump = function(x, y, w, h)`
  - Set a different color than the default for ground so we can see it's special
  - In `collideWith`, change `target.velocity.y` to make it bounce up
    - You might need to change `collideWith` for `PhysicsObject` to not reset its y velocity if `target instanceof SuperJump`

### The new draw loop
If you recall from the first part, this is what our draw loop is composed of:
```js
function draw() {
  // Calculate new positions of objects

  // Accept user input (keypresses, etc)

  // Redraw screen
}
```

Our old code can now be replaced to something a lot simpler:
```js
function draw() {
  allSprites.forEach(function(sprite) {
    sprite.recalculate();
    // reset `sprite.touching` here too
  });

  // Do collision detection between physics and solids as usual,
  // but you can now call `a.collideWith(b)` and `b.collideWith(a)`
  // when there has been a collision to perform the follow-up logic

  drawSprites();
}
```

### Defining the level
After the class definitions in `setup`, we need to define the starting locations for objects differently now that we have classes. Since classes automatically add themselves to the necessary sprite groups, all we need to do is this:

```
function setup() {
  // define classes

  new Player(200, 300);
  new Ground(150, 400, 300, 30);
  // etc

  // If you want to store a reference to something, you can always use
  //   theplayer = new Player( ... );
}
```

### What you should have so far
Here's the game up to this point, in case you fall behind: http://codepen.io/davepvm/pen/NNWyrV?&editors=0010

<a href="part4.md">Part 4: Adding points and lives</a>

<a href="README.md#instructions">Back</a>
