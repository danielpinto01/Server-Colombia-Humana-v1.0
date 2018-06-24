package models;

public class Player {

	private String name;
	private int life;
	private int kills;
	private Area area;
	
	public Player(String name, Area area) {
		this.name = name;
		this.area = area;
		this.life = 100;
	}
	
	public void addKill() {
		kills++;
	}
	
	public void lessLife() {
		life-= 5;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLife() {
		return life;
	}
	
	public Area getArea() {
		return area;
	}
	
	public int getKills() {
		return kills;
	}

	@Override
	public String toString() {
//		return "Player [name=" + name + ", life=" + life + ", kills=" + kills + ", area=" + area + "]";
		return "Se estableció conexión con: " + name;
	}
}