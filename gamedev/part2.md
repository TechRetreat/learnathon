# Game Dev
## Part 2: Gravity and collision detection
<a href="README.md#instructions">Back</a>

### Creating gravity

If you remember from physics class, on the earth, gravity accelerates all objects towards the center of the planet at the same rate (approximately 9.81 meters per second squared, depending on your altitude and location.) If you look at the units, this means that every second, the velocity of an object under Earth's gravity will increase by 9.81 meters per second.

In our game, we're going to define a gravity vector, and every frame, we are going to add this vector to the player's velocity vector.

### Checking for collisions
