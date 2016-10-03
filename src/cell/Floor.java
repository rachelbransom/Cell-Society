package cell;

import java.util.ArrayList;
import java.util.List;

public class Floor<Type> {

	private List<Type> contents;
	
	Floor(){
		contents = new ArrayList<Type>();
	}
	
	Floor(Floor<Type> f){
		contents = new ArrayList<Type>(f.contents);
	}
	
	public List<Type> contents(){
		return contents;
	}
	
	public void setContent(Type obj){
		if(contents.size() == 0){
			contents.add(obj);
			return;
		}
		contents.set(0, obj);
	}
	
	public Type getContent(){
		return contents.get(0);
	}
}
