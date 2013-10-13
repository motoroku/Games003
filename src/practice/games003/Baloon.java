package practice.games003;

import android.graphics.Color;

public class Baloon {

	float xPosition;
	float yPosition;
	static int SIZE = 25;
	int color = Color.RED;
	private static int JUMP_LENGTH = 50;
	private static int MOVE_LENGTH = 20;

	public Baloon(float x, float y) {
		xPosition = x;
		yPosition = y;
	}

	public void move(float x) {
		if (x < xPosition - SIZE) {
			xPosition -= MOVE_LENGTH;
		} else if (xPosition + SIZE < x) {
			xPosition += MOVE_LENGTH;
		}
		if (yPosition > 0) {
			yPosition -= JUMP_LENGTH;
		}
	}

	public void up(float limitHeight) {
		if (yPosition > limitHeight) {
			yPosition -= JUMP_LENGTH;
		}
	}

	public void down(float stageHeight) {
		if (yPosition < stageHeight - SIZE * 2) {
			yPosition += JUMP_LENGTH;
		}
	}

	public void left(float x) {
		if (x < xPosition - SIZE) {
			xPosition -= MOVE_LENGTH;
		}
	}

	public void right(float x) {
		if (xPosition + SIZE < x) {
			xPosition += MOVE_LENGTH;
		}
	}
}
