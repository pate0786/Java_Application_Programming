All the five challenges have been incorporated in the project:

Challenge 1: Splash Screen Progress Bar
	On splash screen, added a progress bar. You can see 5 messages below the progress bar changing every 2 seconds

Challenge 2: Better User Feedback 
	On the blue text area, we will have information about the player who has the current turn. Also that player will be highlighted in red color in score zone.
	
Challenge 3: Better input 
	Now, you can directly click on any cell of the board to make a turn on that cell. For this, I have added mouse listener on each cell for doing so. On mouseReleased method, you can see the code related to this. Now our controller class implements MouseListener too along with ActionListener.

Challenge 4: AI Opponent 
	For implementing AI palyer of this game, I have created a new class named AIModel. AI logic used is very simple here, it just simply make a turn on the first cell where a valid move is possible startin from the top left corner of the board.
	
Challenge 5: Saving and Loading
	Name of the file used for saving and loading purpose is game.txt 
	The format of data is quite simple, it just stores the 8x8 board values like:
10000000
01200000
00201000
00211200
00011000
00001000
00000000
00000000

Where 0 represents an empty cell, 1 represents BLACK and 2 represents WHITE.
Initialy I thought to save another integer at 9th line representing the player number who has the current turn, but later I realized that as 2nd player is AI and it makes it turns real fast, so whenever someone saves the game, it will be player 1 turn only, so not saving any other value than the board itself.
If you remove the game.txt file or change it's content to any invalid data, then program will gracefullly handle it and show a message saying "Loading failed. Either game file is missing or corrupted!".

	