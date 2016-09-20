package logic;

class Actor {
	
	private Enum myState;
	
	private int myEnergy;
	private int myAge;
	
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
