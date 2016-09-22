package cellUtil;

import javafx.scene.paint.Color;

public class Actor {
	
	private Enum myState;
	private Color myColor;
	
	private int myEnergy;
	private int myAge;
	
	Actor(){
		myState = null;
		myEnergy = 0;
		myAge = 0;
	}
	
	Actor(Enum state){
		myState = state;
		myEnergy = 0;
		myAge = 0;
	}
	
	Actor(Enum state, int energy, int age){
		myState = state;
		myEnergy = energy;
		myAge = age;
	}
	
	Actor(Actor that){
		this.myState = that.myState;
		this.myEnergy = that.myEnergy;
		this.myAge = that.myAge;
		
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
	
	public void changeState(Enum state){
		myState = state;
	}

	public Color getColor() {
		return myColor;
	}

	public void setColor(Color myColor) {
		this.myColor = myColor;
	}
	
}
