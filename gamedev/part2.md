# Game Dev
## Part 2: Gravity and collision detection
<a href="README.md#instructions">Back</a>

### Creating gravity

If you remember from physics class, on the earth, gravity accelerates all objects towards the center of the planet at the same rate (approximately 9.81 meters per second squared, depending on your altitude and location.) If you look at the units, this means that every second, the velocity of an object under Earth's gravity will increase by 9.81 meters per second.

In general, `velocity := acceleration * time` (where `:=` means "is defined as"; this is not valid Javascript.) To simulate this, in our game, we're going to define a gravity vector, and every frame, we are going to add this vector to the player's velocity vector. Our gravity vector shouldn't affect the horizontal movement of the character, so its x component should be 0, and its y component should be positive to cause the player to move downwards. Start with a small y value such as 1 and play around until it feels right.

Adding to the velocity vector of a sprite can be done with `sprite.velocity.add(anotherVector);`.

### Checking for collisions

In the game's current state, there is nothing actually stopping the player from simply falling through the ground. Every frame, after calculating new positions and before accepting user input, we will run a **collision detection check**. Seeing if two sprites are touching is simple. In our game setup, we will make two **sprite groups**, one for objects that have physics and can move around, and one for solid objects that can be collided with. Then in the draw loop, we can use a group's `overlap` function:

```js
// In setup:
group1 = new Group();
group1.add(someSprite);

// In draw loop:
group1.overlap(group2, function(a, b) {
  // In this function, "a" is the first sprite, and "b" is the second
  // that are overlapping as opposed to the entire group. The function
  // will get run for every set of overlapping sprites and won't be
  // called for sets that don't overlap.
})
```

The hard part is that simply knowing that there is an overlap isn't enough. We want to know specifically what parts are overlapping. Here's why:
- There is a difference in our game's logic between touching a wall and touching the ground. In each case, a different component of the player's velocity vector needs to change.
- Because we move the player in frames, in one frame the player might not be touching anything, and in the next frame the character might be *inside* the ground and will need to be moved out.

<img src="screenshots/2-overlap.png" />

*Part of collision detection is pushing objects out of solid objects.*

There are multiple ways to do this, but we are going to see if individual points are touching instead of just the whole rectangle. We'll check a few points on each side of the rectangle using `sprite.overlapPoint(x, y)`:

<img src="screenshots/3-points.png" />

This isn't perfect, but it's reasonably efficient and works well. I haven't placed as many collision points on the top as on the other sides because it isn't as likely that there will be a collision on the top, and if there is, one point in the middle usually is good enough, so might as well save a few calculations.

So, to keep track of who's touching whom, before we check collisions, we'll make an object on each sprite to store what sides the player is being touched on, and we will update it inside the collision detection function.

```js
// Set initial touching state
group1.forEach(function(sprite) {
  sprite.touching = {left: false, right: false, top: false, bottom: false};
})

// Check collisions and update touching state
group1.overlap(group2, function(a, b) {
  // if b is touching a on a's left:
  //   b.touching.left = true;
  //   a.touching.right = true;
  //   Also move a out of b so they are no longer overlapping
});

// Check user interaction, and now we can check if sprite.touching.bottom
// is true or something like that

```

Now for the hard part, actually checking if there is a collision. To check if there is a collision below the character, this is the basic logic we want to implement:
```
if (if b is touching a's bottom left OR b is touching a's bottom center OR b is touching a's bottom right) {
  move a up so that a's bottom is the same as b's top

  a.touching.bottom = true;
  b.touching.top = true;
}
if ( ... ) {
  ...
}
```

Note that I haven't used `else if`s here. This is because there could be a collision on multiple sides at once. It's also important to note that the order of the if statements matters. Let's say the character jumps so that its top is colliding with the bottom of a block. Because the corner is also inside the block, if we check for side-to-side collisions before collisions with the top, the character will be pushed all the way to the side of the block it is colliding with, because that is what we do to resolve collisions on sides. It's another reason why we only have one collision point on the top: if we had multiple, and we checked for collisions on the top before collisions on the side, the character might get pushed down when it really should have been pushed to the side. An alternate approach you can try if you want to extend this further is to check collisions on the middle points first and then the cornets after to avoid this problem.

Given that the x/y coordinate of a sprite is **in its center**, so to the bottom of a sprite is `sprite.y+sprite.height/2`, here's what this looks like in actual Javascript:
```js
if (
    b.overlapPoint(a.position.x, a.position.y+a.height/2)
     || b.overlapPoint(a.position.x-a.width*0.3, a.position.y+a.height/2)
     || b.overlapPoint(a.position.x+a.width*0.3, a.position.y+a.height/2)
    ) {
  a.position.y = b.position.y - b.height/2 - a.height/2;
  a.touching.bottom = true;
  b.touching.top = true;
}
```

To test collisions on other sides than just the bottom, you may want to make more ground sprites. As long as you add them all to the `solids` sprite group, the code will still work.

When you run this now, the character will stop falling when it hits the ground... for a moment. Then it will fall through. This is because the velocity continues to increase even as we push the character out of the block. To fix this, we want to add some additional logic after the collision detection.

### Responding to collisions

When colliding with something, we will want the character's velocity to be affected. After we've run through the collision detection loop, we want to run through the following logic:

- If the character is touching the ground, we want to reset its vertical velocity to the vertical component of gravity
  - It might seem logical at first to set the vertical velocity equal to zero. Due to the ordering in which we run our calculations, that would make the character alternate between being on the ground one frame, off the next, on the next, off the next... etc. Simply setting it to the y component of gravity resolves this.
- If the character is touching its head, set the vertical component to zero so that it will begin to fall the next frame
- If the character is touching on either side, set the horizontal components to zero.
- Change the key listener for jumping to make the character jump not only if the up key has been pressed, but also only when the character is on the ground.

## What you should have so far

At this point, you should be able to move your character around and stand on platforms! In case you fall behind: http://codepen.io/davepvm/pen/MyWQQe?editors=0010

<a href="part3.md">Part 3: Different kinds of blocks using inheritance</a>

<a href="README.md#instructions">Back</a>
