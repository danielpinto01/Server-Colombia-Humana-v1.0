package connections;

public interface IObserver {
	
	void update(int idObservable, String message);
	
	void addConnection(Connection connection);

}
