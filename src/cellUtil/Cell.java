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
		this.myActor = new Actor(that.myActor);
		this.myLocation = new Point(that.myLocation);
		this.myNeighbors = new ArrayList<Cell>(that.myNeighbors);
	}
	
	public Actor getActor(){
		Actor out = new Actor(myActor);
		return out;
	}
	
	public Collection<Cell> getNeighbors(){
		
		Collection<Cell> out = new ArrayList<>();
		out.addAll(myNeighbors);
		
		return out;
	}
	
	public Point getLocation(){
		return (Point) myLocation.clone();
	}
	
	
}
