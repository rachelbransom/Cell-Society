package cellUtil;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class Cell {
		
	private Collection<Cell> myNeighbors;
	
	private Point myLocation;
	
	private Actor myActor;
	
	public Cell(){
		this.myActor = new Actor();
		this.myLocation = new Point();
		this.myNeighbors = new ArrayList<Cell>();
	}
	
	public Cell(int x, int y){
		this.myActor = new Actor();
		this.myLocation = new Point(x, y);
		this.myNeighbors = new ArrayList<Cell>();
	}
	
	public Cell(Cell that){
		newActorWithState( that.myActor.getState() );
		
		this.myLocation = new Point(that.myLocation);
		
		this.myNeighbors = new ArrayList<Cell>();
		this.myNeighbors.addAll(that.myNeighbors);
	}
	
	public Actor getActor(){
		return myActor;
	}
	
	public void newActorWithState(Enum state){
		this.myActor = new Actor(state);
	}
	
	public Collection<Cell> getNeighbors(){
		return myNeighbors;
	}
	
	public Point getLocation(){
		return (Point) myLocation.clone();
	}
	
	
}
