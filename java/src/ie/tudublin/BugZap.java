package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet {
	public void settings() {
		size(500, 500);
	}


	public void setup() {
		reset();
	}


	// creating fields and setting default values
	float playerX, playerY;
	float playerSpeed = 5;
	float playerWidth = 40;
	float halfPlayerWidth = playerWidth / 2;

	float bugX, bugY, bugWidth = 100;
	float halfBugWidth = bugWidth / 2;

	int score = 0;


	void reset() {
		resetBug();
		playerX = width / 2;
		playerY = height - 50;
	}


	void resetBug() {
		bugX = random(halfBugWidth, width - halfBugWidth);
		bugY = 50;
	}


	// drawing the player
	void drawPlayer(float x, float y, float w) {
		// set colour of player
		stroke(255);
		float playerHeight = w / 2;

		// making the bug by adjusting the size/length of multiple lines
		line(x - halfPlayerWidth, y + playerHeight, x + halfPlayerWidth, y + playerHeight);
		line(x - halfPlayerWidth, y + playerHeight, x - halfPlayerWidth, y + playerHeight * 0.5f);
		line(x + halfPlayerWidth, y + playerHeight, x + halfPlayerWidth, y + playerHeight * 0.5f);

		line(x - halfPlayerWidth, y + playerHeight * 0.5f, x - (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f);
		line(x + halfPlayerWidth, y + playerHeight * 0.5f, x + (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f);

		line(x - (halfPlayerWidth * 0.8f), y + playerHeight * 0.3f, x + (halfPlayerWidth * 0.8f),
				y + playerHeight * 0.3f);

		line(x, y, x, y + playerHeight * 0.3f);

	}


	// key pressed adjusts what way the layer moves or shoots laser
	public void keyPressed() {
		
		if (keyCode == LEFT) {
			if (playerX > halfPlayerWidth) {
				playerX -= playerSpeed;
			}
		}
		if (keyCode == RIGHT) {
			if (playerX < width - halfPlayerWidth) {
				playerX += playerSpeed;
			}
		}
		if (keyCode == ' ')
		{
			if (playerX > bugX - halfBugWidth && playerX < bugX + halfBugWidth)
			{
				line(playerX, playerY, playerX, bugY);
				score ++;
				resetBug();
			}
			else
			{
				line(playerX, playerY, playerX, 0);
			}
		}
	}


	// draw the bug
	void drawBug(float x, float y) {
		// set colour
		stroke(255);

		// draw body of bug
		float saucerHeight = bugWidth * 0.7f;
		line(x, y - saucerHeight, x - halfBugWidth, y);
		line(x, y - saucerHeight, x + halfBugWidth, y);
		line(x - halfBugWidth, y, x + halfBugWidth, y);
		
		// feet of bug
		float feet = bugWidth * 0.1f;
		line(x - feet, y, x - halfBugWidth, y + halfBugWidth);
		line(x + feet, y, x + halfBugWidth, y + halfBugWidth);

		feet = bugWidth * 0.3f;
		line(x - feet, y, x - halfBugWidth, y + halfBugWidth);
		line(x + feet, y, x + halfBugWidth, y + halfBugWidth);

		// draw eyes of bug
		float eyes = bugWidth * 0.1f;
		line(x - eyes, y - eyes, x - eyes, y - eyes * 2f);
		line(x + eyes, y - eyes, x + eyes, y - eyes * 2f);

	}


	// moves bug around screen
	void moveBug() {

		if ((frameCount % 2) == 0) 
		{
			// use rabdom function to adjust the amount it moves on screen
			bugX += random(-5, 5); 

			if (bugX < halfBugWidth) 
			{
				bugX = halfBugWidth;
			}
			if (bugX > width - halfBugWidth) 
			{
				bugX = width - halfBugWidth;
			}

			bugY += 2;
		}
	}

	int gameMode = 0;

	public void draw() {
		background(0);
		fill(255);

		// text("BUGZAP!", width / 2, height / 2);
		// text("PRESS SPACE TO PLAY", width / 2, (height / 2));

		// display players score
		text("Score: " + score, 20, 20);
		if (gameMode == 0)
		{
			fill(255);
			drawPlayer(playerX, playerY, playerWidth);
			drawBug(bugX, bugY);
			moveBug();
			
		}
		else
		{
			textAlign(CENTER, CENTER);
			text("Score: " + score, width / 2, (height / 2) - 20);
			text("GAME OVER!!!", width / 2, height / 2);
			text("PRESS SPACE TO PLAY", width / 2, (height / 2) + 20);
		}

		// if bug hits player - game over
		if (bugY > playerY)
		{
			gameMode = 1;
		}

	}
}
