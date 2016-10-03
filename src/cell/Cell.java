package cell;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Cell {
		
	private Collection<Cell> myNeighbors;
	private Point myLocation;
	private List<Actor> myActors;
	private Floor<Double> myFloor;
	
	public Cell(){
		myActors = new ArrayList<Actor>(1);
		myActors.add(new Actor());
		
		myLocation = new Point();
		myNeighbors = new HashSet<Cell>();
		myFloor = new Floor<Double>();
	}
	
	public Cell(int x, int y){
		this();
		myLocation.setLocation(x, y);	
		}
	
	public Cell(int x, int y, Actor act){
		this(x, y);
		setActor( new Actor(act) );
	}
	
	public Cell(Cell that){
		this();
		setActor( new Actor(that.getActor()) );
		myLocation.setLocation(that.myLocation);
		
		myNeighbors = new ArrayList<Cell>();
		myNeighbors.addAll(that.myNeighbors);
		
		myFloor = new Floor<Double>( that.myFloor );
	}
	
	public Actor getActor(){
		return myActors.get(0);
	}
	
	public Collection<Actor> getActors(){
		return myActors;
	}
	
	public void setActor(Actor act){
		myActors.set(0, act);
	}
	
	public void setActors( Collection<Actor> actors){
		myActors = new ArrayList<Actor>();
		for (Actor actor : actors) {
			myActors.add( new Actor( actor ));
		}
	}
	
	public Collection<Cell> getNeighbors(){
		return myNeighbors;
	}
	
	public void connectTo(Cell that){
		this.myNeighbors.add(that);
		that.myNeighbors.add(this);
	}
	
	public Point getLocation(){
		return (Point) myLocation.clone();
	}
	
	public Floor<Double> getFloor(){
		return myFloor;
	}
	
	public int numberNeighborsWithState(Enum state){
		int count = 0;
		
		for(Cell c: myNeighbors){
			if(c.getActor().isState(state)) count++;
		}
		
		return count;
	}
	
	public Collection<Cell> getNeighborsWithState(Enum state){
		Collection<Cell> neighborsCopy = new ArrayList<Cell>();
		
		for(Cell neighbor: myNeighbors)
			if(neighbor.getActor().isState(state)) neighborsCopy.add(neighbor);
		
		return neighborsCopy;
	}
	
}
