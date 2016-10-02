package cell;

import java.util.ArrayList;
import java.util.List;

public class Floor<Type> {

	private List<Type> contents;
	
	Floor(){
		contents = new ArrayList<Type>(1);
	}
	
	public List<Type> contents(){
		return contents;
	}
	
	public void setContent(Type obj){
		contents.set(0, obj);
	}
	
	public Type getContent(){
		return contents.get(0);
	}
}
