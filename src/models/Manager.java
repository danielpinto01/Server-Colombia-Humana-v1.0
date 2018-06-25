package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Manager extends MyThread{
	
	private ArrayList<Bees> bees;
	
	public Manager() {
		super("", 200);
		bees = new ArrayList<>();
	}

	@Override
	public void execute() {
		removeBees();
	}
	
	public void timerBees() {
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bees.add(new Bees());
			}
		});
		timer.start();
	}
	
	private void removeBees() {
		for (int i = 0; i < bees.size(); i++) {
			if (bees.get(i).isStop()) {
				bees.remove(i);
			}
		}
	}
	
	public ArrayList<Bees> getBees() {
		return bees;
	}
	
}