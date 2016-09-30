package cellUtil;

import javafx.scene.paint.Color;

public class Actor {
	
	private Enum myState;
	private int myEnergy;
	private int myAge;
	
	public Actor(){
		myState = null;
		myEnergy = 0;
		myAge = 0;
	}
	
	public Actor(Enum state){
		myState = state;
		myEnergy = 0;
		myAge = 0;
	}
	
	public Actor(Enum state, int energy, int age){
		myState = state;
		myEnergy = energy;
		myAge = 0;
	}

	public Actor(Actor that){
		this.myState  = that.myState ;
		myEnergy = that.myEnergy;
		myAge = that.myAge;
	}
	
	public boolean isState(Enum state){
		return myState.equals(state);
	}
	
	public void changeState(Enum state){
		myState = state;
	}
	
	public void incrementEnergy(){
		myEnergy++;
	}
	
	public void decrementEnergy(){
		myEnergy--;
	}
	
	public void incrementAge(){
		myAge++;
	}
	
	public void decrementAge(){
		myAge--;
	}
	
	public int getAge(){
		return myAge;
	}
	
	public int getEnergy(){
		return myEnergy;
	}
	
	public Enum getState(){
		return myState;
	}
	
}
