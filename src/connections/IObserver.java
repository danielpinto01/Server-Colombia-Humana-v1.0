package connections;

public interface IObserver {

	void addPlayer(Connection connection);
	
	void removeConnection(Connection connection);
}