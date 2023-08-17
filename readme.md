# A-star pathfinding algorithm visualization**

### DEMO
![diagonal-vs-non-diagonal](/assets/habiby.gif)

[A*](https://en.wikipedia.org/wiki/A*_search_algorithm) is a graph traversal and path search algorithm, 
which is often used in many fields of computer science due to its completeness, 
optimality, and optimal efficiency. 

This project is a visualization of the A* algorithm, implemented in java.

## Basic Controls
You must create a map to start the pathfinding. The start node is blue, end node is red and the walls are black. 

To create nodes:
  - Start: hold 's' + left click
  - End: hold 'e' + left click
  - Wall: left click
  
To delete nodes:
  - same as creation, except right click!

### Diagonal
The algorithm supports both diagonal and non diagonal pathfinding. 

Simply check the "diagonal" box at the bottom left of the screen.

### Zoom
You can (kind of) zoom in and out. I wouldn't really advise it. It does not zoom into your mouse, only towards the top left corner, and making the map too big will crash the program. This needs some work. However, If you zoom in far enough you can view each nodes information. The top left is the "F cost", bottom left is "G cost" and bottom right is "H cost". I will work on properly implementing a zoom feature soon.

### Speed
You can change the speed of the algorithm by moving the slider at the bottom right of the screen. The speed is in milliseconds.
