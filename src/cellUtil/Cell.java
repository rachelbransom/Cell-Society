package cellUtil;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
		
		this.myActor = new Actor( that.myActor );
		this.myLocation = new Point(that.myLocation);
		
		this.myNeighbors = new ArrayList<Cell>();
		this.myNeighbors.addAll(that.myNeighbors);
	}
	
	public Actor getActor(){
		return myActor;
	}
	
	public void setActor(Actor act){
		myActor = act;
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
