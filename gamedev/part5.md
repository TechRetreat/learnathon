# Game Dev
## Part 5: Scrolling the camera
<a href="README.md#instructions">Back</a>

### Initializing the camera
Guess what? It's actually already there, and it's called `camera`! It has a property `camera.zoom`, which is 1 by default, and a property `camera.position`, which is a vector.

### Moving the camera
We don't want our levels to have to be constrained to the size of the canvas, so we want to make the camera follow the player. "Follow", in this case, means that the camera has to move to minimize the distance between its position and the player's position. The simplest way to do this is to set the camera's coordinates directly to the player's in `Player.prototype.recalculate`:

```js
camera.position.x = this.position.x;
camera.position.y = this.position.y;
```

Try this out. Visually, it's pretty jarring. The character stays in the middle of the canvas at all times and everything moves around it. We probably want to **ease the transition**. That means, as an example, the camera might only move halfway to the player in one frame, and then cut the distance in half again the next frame, and then again the next, so that the camera smoothly eases closer and closer to the player.

How would we do that? Let's look just at the horizontal component first. This is how we could calculate the distance between the camera and the player on the x axis:
```js
var xDifference = player.position.x - camera.position.x;
```

If we wanted the camera to jump to the player's x coordinate, rather than setting the camera's x coordinate directly, we could instead add the difference to it:
```js
camera.position.x += xDifference;
```

So, if we only wanted it to go halfway each frame, we simply need to divide the difference by two:
```js
camera.position.x += xDifference/2;
```

Try doing this for both the x and the y and see how it looks. It's better than before, but still moves pretty fast. You may want to experiment and divide by higher numbers to see how that feels.

### Fixing the score and points
The camera moves, but now the score seems to be fixed in space relative to the level when it should really be attached to the camera. This is because when we move the camera, all positions are automatically now **relative to the level** and not the camera. If we want to draw things using **absolute positioning** again (absolute referring to the canvas), we can simply turn off the camera for that part:

```js
draw() {
  // do stuff

  drawSprites();

  camera.off();

  // draw stuff in absolute positioning
  //
  // optionally turn the camera back on if you want
  // to draw things with relative positioning some more:
  // camera.on();
}
```

### What you should have so far
Here's the game up to this point, in case you fall behind: http://codepen.io/davepvm/pen/PNOyrW?editors=0010

### What next?
Now you get to make the game yours! From the main page, look at some of the extensions and next steps.

<a href="README.md#instructions">Back</a>
