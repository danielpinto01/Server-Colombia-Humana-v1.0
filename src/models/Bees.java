package models;

public class Bees extends MyThread {

	public static int count = 0;
	private int id;
	private int type;
	private int x;
	private int y;
	private int size;
	private int life;
	
	public Bees() {
		super("", 100);
		this.type = (int) (Math.random() * 3 + 1);
		life = type;
		id = count++;
		x = 800;
		setSize();
		start();
	}
	
	private void setSize() {
		if (type == 3) {
			size = 50;
		} else if (type == 1) {
			size = 20;
		} else {
			size = 35;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getType() {
		return type;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void shock() {
		life--;
		if (life == 0) {
			stop();
		}
	}

	@Override
	public void execute() {
		y += (int) (Math.random() * 50);
		x -= (int) (Math.random() * 50);
		if (900 <= y) {
			stop();
		}else if (y == 200) {
			y = (int) (Math.random() * 30);
		}else if (x <= 5) {
			x = 5;
			y += 10;
		}
	}
}