package models;

public class Enemy extends MyThread{
	
	private int positionInX;
	private int positionInY;
	
	private MoveEnemy moveEnemy;
	
	private int sizeWindowX;
	
	public Enemy() {
		super("Enemy", 100);
		this.positionInX = 950;
		this.positionInY = 1;
		moveEnemy = MoveEnemy.RIGHT;
		sizeWindowX = 900;
		start();
	}

	public int getPositionInX() {
		return positionInX;
	}

	public void setPositionInX(int positionInX) {
		this.positionInX = positionInX;
	}

	public int getPositionInY() {
		return positionInY;
	}

	public void setPositionInY(int positionInY) {
		this.positionInY = positionInY;
	}
	
	public void moveEnemy() {
		if (moveEnemy == MoveEnemy.RIGHT) {
			positionInX += 10;
		}else if (moveEnemy == MoveEnemy.LEFT) {
			positionInX -= 10;
		}
		
		if (positionInX >= sizeWindowX) {
			moveEnemy = MoveEnemy.LEFT; 
		}else if (positionInX <= 750) {
			moveEnemy = MoveEnemy.RIGHT;
		}
	}
	
	public int getSizeWindowX() {
		return sizeWindowX;
	}

	public void setSizeWindowX(int sizeWindowX) {
		this.sizeWindowX = sizeWindowX;
	}

	@Override
	public String toString() {
		return "Enemy [positionInX=" + positionInX + ", positionInY=" + positionInY + "]";
	}

	@Override
	public void execute() {
		moveEnemy();
	}
}