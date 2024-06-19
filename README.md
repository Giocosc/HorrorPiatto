# Clone this repository
Use git to clone this repository into your computer.

```
git clone https://github.com/Giocosc/HorrorPiatto.git
```
## Intro

HorrorFlat (HP) is a minigame that started out to become a low-end horror game that during its development and during its moral growth decided to become something different. 

**The question is: What should you do next?**

HP is a growing minigame that has gone from a horror game to a "collecting" game. Entirely developed in java, without the use of special external libraries, it aspires to become a complete game with a semblance of horror a bit demented.

## Contents

*   [What is this?](#what-is-this)
*   [When should I play this?](#when-should-i-play-this)
*   [Getting started](#getting-started)
    *   [Requirements](#requirements)
    *   [Commands](#commands)
*   [Parts overview](#parts-overview)
    *   [Core Class](#core-class)
        *   [Game Panel](#game-panel)
        *   [Key Handler](#key-handler)
        *   [Asset Manager](#asset-manager)
        *   [Collision Manager](#collision-manager)
        *   [Sound Manager](#sound-manager)
        *   [UI Manager](#ui-manager)
    *   [Entities](#entities)
        *   [Entity Base](#entitybase)
        *   [Player](#player)
    *   [Object](#object)
        *   [Super Object](#super-object)
        *   [Objects](#objects)
    *   [Tile](#tile)
        *   [Tile Base](#tile-base)
        *   [Tile Manager](#tile-manager)
    *   [Resources](#resources)
        *   [Items](#items)
        *   [Map](#map)
        *   [Player](#player)
        *   [Sounds](#sounds)
        *   [Tiles](#tiles)
    *   [Common](#common)
        *   [Random](#random)
*   [External Reference](#external-reference)
*   [Conclusion](#conclusion)

## What is this?

questo è un progetto per l'esame di programmazione ad oggetti che consiste nello sviluppare un'applicazione in java di almeno 500 righe.

## Why should I play this?

Dovresti giocare a HP perchè è un gioco semplice ma avvincente con una grafica accattivante e spensierata. con l'accompagnamento di una musichetta simpatica e divertente giocare a HP potrà soddisfare e catturare i gamer piu esigenti.

## Getting Started

So how do you get this template to work for your project? It is easier than you think.

### Requirements

* voglia di divertirsi
* tempo

### Commands

per muoversi usare i soliti tasti W,A,S,D.

## Parts overview

*   **Core Class**
  Classes that make up the logical, low-level part of the game mechanics.
* **Entities**
  Classes that describe entities and characters within the game.
* **Object**
  Classes that describe items in the world of HP.
* **Tile**
  Classes that manage the creation of the game world.
* **Resources**
  Files and documents that contain all the game assets and the map.
* **Common**
  Commonly used classes to help and simplify code management. 

### Core Class
This folder contains all the core and basic entities for HP startup

#### Game Panel
This class extends JPanel and implements Runnable, allowing you to handle the game loop on a separate thread. Here is a brief description of its main features and functions:

1. Game Size and Scale:
   * Defines the tile size (16x16 pixels) and a scale (3), resulting in tiles of 48x48 pixels.
   * Establishes the screen size (768x576 pixels) and the size of the game world (50x50 tiles).

2. Game Components:
   * Asset Management: Use AssetManager to manage game assets.
   * Collision Management: Use CollisionManager to manage collisions between entities.
   * Sound Management: Use SoundManager to manage sound effects and music.
   * User Interface Management: Use UIManager to manage the game UI.
   * Tile Management: Use TileManager to manage and design game tiles.
   * Object Management: A SuperObject array to manage objects in the game (maximum 10).

3. Player Management:
   * The Player class represents the player, which is updated and drawn in the game loop.
   * Use KeyHandler to capture keyboard events.


4. Game Setup and Loop:
   * Game Setup: The setupGame method initializes objects and starts music.
   * Game Loop: The run method handles the main game loop, updating the state and redrawing the screen at 60 FPS.
   * Update: The update method updates the player's state.
   * Drawing: The paintComponent method draws the game components (tiles, objects, player, UI).

5. Management of Music and Sound Effects:
   * Methods for starting (playMusic), stopping (stopMusic) music and playing sound effects (playSoundEffect).

In summary, the GamePanel class integrates several components to create a 2D game environment, managing graphics, input, sounds and game logic through a continuous loop.

#### Key Handler
The KeyHandler class is a keyboard event handler in Java, implementing the KeyListener interface to respond to key press, release, and typing events. Here is a detailed description of its main features:

1. Status of the Buttons:
   * The class contains four Boolean variables (upPressed, downPressed, rightPressed, leftPressed) that represent whether the W, S, A, D keys are pressed for up, down, left, and right gestures, respectively.
2. Checking the Status of the Keys:
   * The anyPressedKey() method returns true if any of the movement keys are pressed, otherwise it returns false.
3. Keyboard Events:
   * keyTyped(KeyEvent e): Method inherited from KeyListener, left empty since there is no need to handle keystroke events in this context.
   * keyPressed(KeyEvent e): Called when a key is pressed. In this method:
   * The code of the pressed key is verified.
   * If the key is W, S, A, or D, the corresponding Boolean variable is set to true.
   * keyReleased(KeyEvent e): Called when a key is released. In this method:
   * The code of the released key is verified.
   * If the key is W, S, A, or D, the corresponding Boolean variable is set to false.

In summary:
KeyHandler detects and manages the pressing and releasing of the W, S, A, and D keys.
Boolean variables allow you to check the status of the keys at any time.
The anyPressedKey method provides a quick check whether one of the movement keys is pressed.
This class is essential for handling player movement or other actions that depend on keyboard input in the game.

#### Asset Manager
The AssetManager class deals with the management of game objects in the context of a Java game, connecting to the game panel (GamePanel). Here is a detailed description of its main features:

#### Collision Manager
The CollisionManager class manages collisions in the game, checking whether entities come into contact with tiles or solid objects. Here is a detailed description of its main features:

Main Methods
1. checkTile(EntityBase entity):
   Checks if the entity collides with a tile.
   Calculates the coordinates of the entity's edge (left, right, top, bottom) based on its position and collision area.
   Determines the tile columns and rows that the entity may come into contact with.
   Check the direction of the entity (up, down, left, right) and update the coordinates to account for movement.
   Tests whether tiles in new spaces are solid, setting collisionOn to true if there is a collision.

2. checkObject(EntityBase entity, boolean player):
   Checks if the entity collides with an object.
   Iterate through the array of objects in the game.
   Updates the collision areas of the entity and object based on their positions in the world.
   Adjusts the entity's collision area according to its direction of motion.
   Tests whether the collision areas of the entity and object intersect, setting collisionOn to true if there is a collision.
   If the entity is a player, it returns the index of the object with which the collision occurred.

#### Sound Manager
The SoundManager class manages the playback of sounds in the game, allowing you to load, play, repeat, and stop audio clips. Here is a detailed description of its main features:

**Fields**
    * Clip clip: A variable to store and control the current audio clip.
    * URL soundURL[]: An array of URLs to store paths to audio assets.

**Manufacturer***
    * **public SoundManager()**: Initialize the soundURL array with audio resource paths using the getResource method of the Class class. This method searches for audio files in your assets folder.

**Main Methods**
1. setFile(int i):
   Loads the sound clip specified by index i into the soundURL array.
   Uses AudioSystem to get an audio input stream from the URL resource and opens it as a clip.
   If an exception occurs during this process, it is caught but not handled (no error message is displayed).

2. play():
Starts playback of the loaded audio clip.

3. loop():
   Plays the audio clip in a continuous loop.

4. stop():
   Stop playing the audio clip.

5. isRunning():
   Returns true if the audio clip is playing, false otherwise.

#### UI Manager
The UIManager class manages the game's user interface, providing functions to display information such as the number of ingots collected, playing time, and temporary messages. Here is a detailed description of its main features:

**Fields**
* GamePanel gamePanel: Reference to the game panel, which contains the main information and logic of the game.
* static Font defaultFont, static Font fontArial55: Default fonts for displaying text.
* BufferedImage ingotImage: Image of the ingot to display in the interface.
* boolean gameFinished: Indicates whether the game is finished.
* boolean messageOn: Indicates whether a temporary message should be displayed.
* String message: Temporary message to display.
* int messageCounter: Counter for the lifetime of the temporary message.
* double playingTime: Elapsed playing time.
* DecimalFormat decimalFormat: Game time formatter.

**Main Methods**
1. showMessage(String text):
   * Set a temporary message to display.
   * Turn on message display and reset the counter.

2. draw(Graphics2D g2):
   * Draw user interface on game panel.
   * View the number of ingots collected and playing time.
   * If messageOn is active, displays the temporary message and increments the counter to deactivate it after a certain period.
   * If the game is finished (gameFinished), displays a victory message and stops the game thread.

### Entities
This folder contains all the game entities

#### Entity Base
The EntityBase class is a base class for representing an entity in the game, providing attributes and functionality common to all entity types. Here is a detailed description of its main components:

**Fields**
* Position and Speed
  * public int worldX: The X location of the entity in the game world.
  * public int worldY: The Y location of the entity in the game world.
  * public int speed: The speed of movement of the entity.

* Images for Animation
  * public BufferedImage go_down_1, go_left_1, go_down_3, go_down_2, go_left_2, go_left_3, go_right_1, go_right_2, go_right_3, go_up_1, go_up_2, go_up_3: Images for the various directions of movement (down, left, right, up) and for the different phases of movement animation.

* Direction and Animation
  * public String direction: The current direction of the entity ("up", "down", "left", "right").
  * public int spriteCounter: Counter to manage the updating of animation frames.
  * public int spriteNumber: Current frame number of the animation.
* Collisions
  * public Rectangle collisionArea: Collision area of ​​the entity, used to detect collisions with other objects or entities.
  * public boolean collisionOn: Indicates whether the entity is colliding with something.
  * public int solidAreaDefaultX, solidAreaDefaultY: Default collision area positions, used to reset the collision area after checking for collisions.

#### Player
The Player class extends the EntityBase class and represents the player in the game. This class manages the player's position, movement, animation, and interaction with game objects. Here is a detailed description of its main components:

**Fields**
1. **Key Object References**
   * GamePanel gamePanel: Reference to the game panel, which contains much of the information and methods needed to manage the game.
   * KeyHandler keyHandler: Reference to the key handler for detecting player inputs.

2. **Position on screen**
   * public final int screenX: The player's X position on the screen.
   * public final int screenY: The player's Y position on the screen.

3. **Inventory**
   * public int totalIngots: Total number of ingots collected by the player.

### Object
#### Super Object
This SuperObject class represents a generic object in the game. Here is a detailed description of its fields and main method:

**Fields**
    
1. Image and name
   * BufferedImage image: The image of the object.
   * String name: The name of the object.
2. Collision
   * boolean collision: Indicates whether the object is subject to collisions or not.
3. Location
   * int worldX, worldY: The world coordinates of the object.
4. Solid area
   * Rectangle solidArea: A rectangle representing the solid area of ​​the object.
   * int solidAreaDefaultX, solidAreaDefaultY: Default position of the solid area relative to the object coordinates.

**Description**

The SuperObject class is designed to represent objects in the game world. The draw method is responsible for drawing the object on the game panel. Here's how it works:
 * Screen position calculation: Calculate the screenX and screenY coordinates of the object relative to the player's position on the screen (gp.player.screenX and gp.player.screenY). This ensures that the object is drawn correctly in position relative to the player.
 * Visibility Check: Uses a series of conditions to check whether the object is visible on the screen:
 * Checks whether the object is inside a rectangular area around the player, considering the tile size (gp.tileSize). This ensures that only visible objects are drawn, improving rendering performance.
 * Drawing image: If the object is visible, draws the object's image (image) at the calculated coordinates (screenX, screenY) with the specified dimensions (gp.tileSize x gp.tileSize).

**Usage**
This class can be used to represent various types of objects in the game (such as ingots, chests, etc.) and provides a method to render them on the screen based on their position relative to the player, ensuring that only visible objects are drawn, thus optimizing rendering performance.

#### Objects
This folder contains all the objects we want to have in the game. they all inherit from SuperObject

### Tiles
#### Tile Base

This Tile class represents a basic type of tile in the context of a game or application that uses an image grid for the game map. Here is a detailed explanation of its fields:

##### Tile class fields
* BufferedImage image: This field contains the image associated with the tile. BufferedImage is a class in Java that represents a raster image, that is, an image made up of pixels. In practice, this field is used to store the tile image that will be displayed on the game map.
* boolean collision: This boolean field indicates whether the tile is subject to collisions or not. If collision is true, it means that the tile represents an area of ​​the map with which other objects or entities (such as the player) can collide, while if it is false, it means that there are no collision restrictions for that type of tile.

##### Using the Tile class
The Tile class is designed to be used within a tile management system, where each Tile represents a specific type of terrain or structure on the game map. Some usage examples include:
* Game map rendering: When rendering the game map, the various tiles are drawn based on their images. This allows you to view the map in a graphically detailed and varied way.
* Collision Handling: During game logic, the collision property of each Tile is used to determine whether the player or other entities can move on that tile or whether they should be blocked by collisions. This is crucial for creating a realistic and interactive gaming environment.

#### Tile Manager
This TileManager class handles the management of tiles within a game, handling the loading of tile images, the configuration of the game map via text file, and the drawing of tiles on the game screen. Here is a detailed explanation of how it works:

##### Description of fields and methods
* GamePanel gp: This field keeps track of which game panel the TileManager is associated with. The GamePanel contains information such as the width and height of the game screen, the maximum number of rows and columns in the game map (maxWorldRow and maxWorldCol), and other game information.
* Tile[] tile: An array of Tile objects representing the various types of tiles available in the game.
* int[][] mapTile: An array representing the game map, where each element contains a code indicating the type of tile present in that position.

##### TileManager Constructor(GamePanel gp)
In the TileManager constructor, the various fields are initialized and some initial operations are performed:
1. Initialization of tile[]: An array of Tiles with a maximum size of 100 is created.
2. MapTile initialization: The mapTile array is initialized with dimensions gp.maxWorldRow for the rows and gp.maxWorldCol for the columns. These dimensions are based on the game map dimensions specified in the GamePanel.
3. Loading tile images: The getTileImage() method is called to load all tile images from file.
4. Loading the game map: The loadMap(String fileName) method is called to load the map tile layout from a text file.
5. Adding random decorations: The addRandomDecoration() method is called to add random decorations such as bushes, flowers and rocks to the map.

##### addRandomDecoration() method
This method adds random decorations to the game map:

* Bushes: 150 bushes are added at random locations on the map. Bushes are represented by the tile with code 40.
* Flowers: 200 flowers are added to random locations on the map. The flowers are represented by the tile with code 47.
* Rocks: 50 rocks are added to random locations on the map. The rocks are represented by the tile with code 48.

##### getTileImage() method
This method loads all tile images from file:

Image files corresponding to each tile type used in the game are loaded using ImageIO.read().
Some tiles, such as walls and water, are also marked with collision = true to indicate that they are subject to collisions.

##### loadMap(String fileName) method
This method loads the map tile layout from a text file:

* A text file fileName is opened that contains the numerical arrangement of the tiles on the map.
* The numbers in the file are read and inserted into the mapTile array to determine which type of tile should be placed in each cell of the map.

##### draw(Graphics2D g2) method
This method draws tiles on the game screen:
* Iterate through each cell of the mapTile array.
* Determines the type of tile to draw using the numeric value present in mapTile.
* Calculate the screenX and screenY coordinates for each tile, considering the player's position in the game world (gp.player.worldX and gp.player.worldY).
* Draw the tile on the game screen using g2.drawImage() if the tile is visible on the screen.

### Resources
the resources folder contains all the files with which the game works:

#### Items
item contains all the images of the game items.

#### Map
map contains the game map in .txt format

#### Player
player contains all the frames of the player animation

#### Sounds
sound contains the sounds of the game

#### Tiles
tiles contains all the images of the tiles that are used in the map

### Common
common contains all utility classes. with the aim of improving the stylistic rendering of the code.
#### Random
random contains the method for generating a random number.
## External Reference

INTElliJ IDEA Community Edition - for development
Paint.Net for drawing tiles and characters
Google.com for the Hamlet-like doubts regarding development

## Conclusion
To conclude I hope to get 33

++***+++++++++++++++++++++++++++++++++++++=====+++****+++++++
**+++=========***++++======--===++++++++++==++++**++++=======
**++++++++++++***++++++++##**#######++++++==++++**+++++++++++
***********+++++++****%*---:::::::---*%+++++++++************+
**++++=====+++***++%---:::::::::::::::---%*+++++**++++======+
**++++++===+++***#%-:::::::::::::::::::::-%#++++**++++++===++
+++++++++++++++++#%:::::::::::::::::::::::%#+++++++++++++++++
+++++++++++++++#%*-:::::::::::::::::::::::-+%#+++++++++++++++
+++++++++++++++#%=:::::::::::::::::::::::::=%#+++++++++++++++
+++***++++++++@@%=:::::::::::::::::::::::::=%@@++++***+++++++
+++++++++++**@%@@+:::::::::::::::::::::::::+@@%@##*+++++++++=
***++=====+@@%#@@%-:::::::::::::::::::::::-#@@#%@@#+++=======
*********@@%%#@#:%@:::=@@@@:::::::@@@@=:::@@:*@#%%@@********+
*****++++%@###@#:%@-:::#@@@:::::::@@@%:::-@@:*@###@@*++++++++
***+++++@@%###%@@@@-:::=@@@:::::::@@@+:::-@@@@%###%@@++++==++
++++++++@%####@@@@@@:::-+++:::::::%@@#+++@@@@@@####%@++++++++
++++++@@%####@@@%@@@%%%*=::::-===%%%@@@@@@@@@@@@####%@*++++++
++++++@@%####@%%%*==%%%%%%%==*%%%%@@@@@@@@@@@@@@####%@#++++++
++++++@@###%@@@@@@%====-:::::-====%%%@@@@@@@@@@@@%###@*++++++
++++++%%###%@@@@@@@@@@%*=========%@@@@@@@@@@@@@@@%###%*++++++
++++++####%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%+**####++=====
++++++###%@@@@@@@@%=@@@@@@@@-:::-@@@@@@@@@#+---*@@%%##+++++++
**++++###%@@@@@@@#==%%@@@@@*-----*@@@@@@@@@@@@@@@%%%##*+****+
==++++%%#@@@@@@%#*+%%%@@@@@#=====*@@@@@@@@@@%%%@@@@@#%*****+=
==++++%%#@@@@@%*==+%@@@@@@@@%%%%%%@@%@@@@+@@@@@@@@@@#%*****+=
++***+@@%@@@@@@@@@@%@@+#@**#@@=-:-==@@@**++@@@@@@@@@%@#++++++
**+++=@@@@@=+@@@*@@@@@+++@@@@@@@**@@@@++++==+%@@#*@@@@*======
**+++===@@@===***++++======--@@@::**++++++==++++**@@@+=======
***********+++++++********++++++++++++++++++++++************+
**++++=====+++++++*****+++====++++++++***+++++++**++++======+
**++++=====+++***+++++++++====++++++++***+++++++**++++======+