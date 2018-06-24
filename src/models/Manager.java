package models;

public class Manager {
	
	private Enemy enemy;
	
	public Manager() {
		enemy = new Enemy();
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public String toString() {
		return "Manager [enemy=" + enemy + "]";
	}
}