# Minesweeper

***
## Introduction  
### The project makes the game *Minesweeper* using Java. It is almost the same as the traditional one except for the UI design and the lack of custom function.  

***
## Game Rule  
- First of all, choose the game level and then start the game.  
- Left-click to open the covered squares.  
- Right-click to put a flag on a covered square or remove the flag from the square. Right-click on an uncovered square to open its surrounding area, if the number of flags around the square is equal to the number on the square.  
- Click the mouse scroll to choose another level during anytime of the game.  
- Left-click on the icon in the middle on the top of the window to restart a game.  
- See the left icon on the top to know the number of left mines, and see the right icon to get the idea of the time used.(The time is counted in seconds.)  
- Win the game if all the non-mine squares are uncovered or mark all the mines with flags.  
- Lose the game if any one of the mines is uncovered.  

***
## Bugs and unfinished functions  
- The window cannot be shown completely when choosing the level of medium and hard. However, it will not influence too much of playing.  
- The function of custom is not finished, which means you cannot choose the *custom* button when choosing the levels.  

***
## Difficulties and solutions  
1. I have to see the stuffs in the window by zooming the window.  
* Add a repaint() method in the launch() method. Repaint every 40 ms.  

2. The window will flash between the bottome layer and the top layer if the two layers are drawn seperately on the window. Because the paint() method is being constantly called when running the program.  
* Create a new image and draw the two layers on it. Then draw the image on the window.  

3. I need to double click on the squares to open a square or put a flag on it.  
* It happens if I use mouseClicked(). So I changed the overridden method in the addMouseListener() from mouseClicked to mousePressed. Maybe it is because of the different types of mouses or the hardwares. I thought my computer is more sensitive to the pressing operation. And it worked as expected.  

4. The squares of the first left column and the top row of the minefiled will be uncovered if I left-click on the blank area near them.  
* It is because of the property of the division between int numbers. Check before calculating the position of a click. Make sure the click happens inside the minefield.  

5. The game needs to be able to restart and choose levels during anytime of the game.  
* Use the property of the *switch* command. Write the statements of restarting and choosing levels in the last case of *switch* command. Delete the *break* statements in the previous cases.   