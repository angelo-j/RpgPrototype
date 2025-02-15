# RpgPrototype

**Reusable skeleton that should work for almost any Java game!**

Tired of dealing with build issues and debugging, monotonous project structuring, and the myriad mind-numbing error messages you get every time you want to start a new game project?
I was too, so here you go.
As long as you clone the repository correctly and have Java set up on your system, this should run out of the box.

This project includes sample functioning (though very simple) systems:

- **Main Menu**: The starting screen where the player can start a new game, continue an existing game, access options, and quit.
- **Save Selection Screen**: Lets the player choose a save slot to load or create a new game. Handles save file management.
- **Character Creation Screen**: Allows the player to create and customize their character, including selecting a faction and naming the character.
- **Options Menu**: Provides settings for volume control (for sounds like menu clicks and music), fullscreen/windowed mode toggle, and other settings.
- **SaveManager**: Handles saving and loading the player's game progress, ensuring the state of the game is preserved.
- **SoundManager**: Manages the game’s audio (background music, sound effects), including volume and mute functionality.
- **World Map**: Provides a view of the entire game world, with locations that the player can fast-travel to.
- **Fast Travel**: Allows the player to quickly move between pre-defined locations within the world.
- **Game World** with pre-configured camera settings: A grid-based game world that dynamically updates the view based on the player's position, with obstacles and free space.
- **Player Movement**: The player can navigate the game world using keyboard input (WASD for movement).
- **Random Map Generation**: Creates a random world map each time the game starts, making each playthrough different and dynamic.

All you have to do now is hunt down any resources you want to use and write your game!

---

### **Music Attribution**
- "The Complex" by Kevin MacLeod ([incompetech.com](https://incompetech.com))
- Licensed under [Creative Commons: By Attribution 4.0 License](http://creativecommons.org/licenses/by/4.0/)

---

### **Sound Attribution**
- "Button click" by AndrewEathan
- License: [Creative Commons 0](https://freesound.org/s/538727/)
