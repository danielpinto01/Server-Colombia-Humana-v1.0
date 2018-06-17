package models;

public class PlayerServer {
	
	private String namePlayer;
	private String characterPlayer;
	private int positionInX;
	private int positionInY;
	private static final int MOVE_UNITS = 50;

	public PlayerServer() {
		
	}

	public PlayerServer(String namePlayer, String characterPlayer, int positionX, int positionY) {
		this.namePlayer = namePlayer;
		this.characterPlayer = characterPlayer;
		this.positionInX = positionX;
		this.positionInY = positionY;
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

	@Override
	public String toString() {
		return "NamePlayer: " + namePlayer + ", CharacterPlayer:" + characterPlayer + ", PositionInX:"
				+ positionInX + ", PositionInY:" + positionInY;
	}

}
