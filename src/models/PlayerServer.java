package models;

public class PlayerServer {
	
	private String namePlayer;
	private String characterPlayer;
	private int positionInX;
	private int positionInY;
	
	private int lifePlayer;

	public PlayerServer() {
		
	}

	public PlayerServer(String namePlayer, String characterPlayer, int positionX, int positionY, int lifePlayer) {
		this.namePlayer = namePlayer;
		this.characterPlayer = characterPlayer;
		this.positionInX = positionX;
		this.positionInY = positionY;
		this.lifePlayer = 100;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

	public String getCharacterPlayer() {
		return characterPlayer;
	}

	public void setCharacterPlayer(String characterPlayer) {
		this.characterPlayer = characterPlayer;
	}

	public int getPositionInX() {
		return positionInX;
	}

	public int getPositionInY() {
		return positionInY;
	}

	public int getLifePlayer() {
		return lifePlayer;
	}

	public void setLifePlayer(int lifePlayer) {
		this.lifePlayer = lifePlayer;
	}

	@Override
	public String toString() {
		return "PlayerServer [namePlayer=" + namePlayer + ", characterPlayer=" + characterPlayer + ", positionInX="
				+ positionInX + ", positionInY=" + positionInY + ", lifePlayer=" + lifePlayer + "]";
	}
}