package cell;

import java.util.ArrayList;
import java.util.List;

public class Actor{
	
	private Enum myState;
	private List<Integer> myEnergies;
	private int myAge;
	
	public Actor(){
		myState = null;
		myEnergies = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			myEnergies.add(0);
		}
		myAge = 0;
	}
	
	public Actor(Enum state){
		this();
		myState = state;
	}
	
	public Actor(Enum state, int energy, int age){
		this(state);
		myEnergies.set(0, energy);
		myAge = 0;
	}

	public Actor(Actor that){
		this.myState  = that.myState ;
		myEnergies = new ArrayList<>(that.myEnergies);
		myAge = that.myAge;
	}
	
	public boolean isState(Enum state){
		return myState.equals(state);
	}
	
	public void changeState(Enum state){
		myState = state;
	}
	
	public void incrementEnergy(){
		myEnergies.set(0, myEnergies.get(0) + 1);
	}
	
	public void setEnergy(int in){
		myEnergies.set(0, in);
	}
	
	public void decrementEnergy(){
		myEnergies.set(0, myEnergies.get(0) - 1);
	}
	
	public List<Integer> energies(){
		return myEnergies;
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
		return myEnergies.get(0);
	}
	
	public Enum getState(){
		return myState;
	}
	
}
