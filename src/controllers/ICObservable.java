package controllers;

public interface ICObservable {

	void addObserver(ICObserver icObserver);
	
	void removeObserver();
}