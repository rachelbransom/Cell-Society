package simulation;

class Actor {
	
	private Enum myState;
	
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
	
	void incrementEnergy(){
		myEnergy++;
	}
	
	void decrementEnergy(){
		myEnergy--;
	}
	
	void incrementAge(){
		myAge++;
	}
	
	void decrementAge(){
		myAge--;
	}
	
	int getAge(){
		return myAge;
	}
	
	int getEnergy(){
		return myEnergy;
	}
	
	Enum getState(){
		return myState;
	}
	
	void changeState(Enum state){
		myState = state;
	}
	
}
