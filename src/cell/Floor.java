package cell;

import java.util.ArrayList;
import java.util.List;

public class Floor<Type> {

	private List<Type> contents;
	
	Floor(){
		contents = new ArrayList<Type>();
	}
	
	public List<Type> contents(){
		return contents;
	}
}
