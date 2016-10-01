package grid;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericGrid<Type> implements Iterable<Type>{

	private Map<Point, Type> myGraph;
	private int mySize;

	public GenericGrid(){
		mySize = 0;
		myGraph = new HashMap<Point, Type>();
	}
	
	public GenericGrid(int n) {	
		this();
		for(int i = 0; i < mySize; i++)
			for(int j = 0; j < mySize; j++)
				myGraph.put( new Point(i, j), null);
	}
	
	public GenericGrid( GenericGrid<? extends Type> that ){
		this(that.mySize);
		
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				Point location = new Point(i, j);
				set(location, that.get(location));
			}
		}		
	}

	public int getSize(){
		return mySize;
	}
	
	public Type get(int x, int y){
		return myGraph.get( new Point(x, y) );
	}
	
	public Type get(Point p){
		return myGraph.get(p);
	}
	
	public void set(int x, int y, Type k){
		myGraph.put( new Point(x, y), k);
	}
	
	public void set(Point p, Type k){
		myGraph.put(p, k);
	}

	public boolean inBounds(int x, int y){
		boolean inX = 0 <= x && x < mySize;
		boolean inY = 0 <= y && y < mySize;
				
		return inX && inY;
	}
	
	@Override
	public Iterator<Type> iterator() {

		Iterator<Type> anonymousIterator = new Iterator<Type>(){
			private Iterator<Map.Entry<Point, Type>> iter = myGraph.entrySet().iterator();

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public Type next() {
				// TODO Auto-generated method stub
				return iter.next().getValue();
			}
		};

		return anonymousIterator;
	}
	
	
}
