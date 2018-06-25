package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Enemy extends MyThread{
	
	private int positionInX;
	private int positionInY;
	
	private MoveEnemy moveEnemy;
	
	private int sizeWindowX;
	
	public Enemy() {
		super("Enemy", 100);
		this.positionInX = 20;
		this.positionInY = 20;
		moveEnemy = MoveEnemy.RIGHT;
		sizeWindowX = 1000;
//		timerShot();
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
		}else if (positionInX <= 0) {
			moveEnemy = MoveEnemy.RIGHT;
		}
	}
	
	public int getSizeWindowX() {
		return sizeWindowX;
	}

	public void setSizeWindowX(int sizeWindowX) {
		this.sizeWindowX = sizeWindowX;
	}
	
//	public void timerShot() {
//		Timer timer = new Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				shots.add(new Shot(positionInX, positionInY));
//			}
//		});
//		timer.start();
//	}
	
//	public void moveShot() {
//		try {
//			for (Shot shot : shots) {
//				if (shot.getPositionInY() < 700) {
//					shot.setPositionInY(shot.getPositionInY()+10);
//					
//				}else {
//					shots.remove(shot);
//				}
////				System.out.println(shot.getPositionInY());
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		
//	}
//
//	public ArrayList<Shot> getShots() {
//		return shots;
//	}

	@Override
	public String toString() {
		return "Enemy [positionInX=" + positionInX + ", positionInY=" + positionInY + "]";
	}

	@Override
	public void execute() {
		moveEnemy();
//		moveShot();
	}
}