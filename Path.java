import java.util.*;
import java.io.*;

class startNotFoundError extends Exception {
    startNotFoundError(Throwable cause) { super(cause); }
}

public class Path{
	private Map<Start, Destination> map = new HashMap<Start, Destination>();
	public void insertPath(String start,String destination) {
		Start s = new Start(start);
		if(map.get(s)!=null){
			map.get(s).insert(destination);
		}
		else
			map.put(new Start(start), new Destination(destination));
	}

	public boolean hasPath(String start,String destination)throws startNotFoundError {
		Start s = new Start(start),tmp = null;
		Object[] set = map.keySet().toArray();
		boolean state = false; 
		for(Object st : set) 
			if(((Start)st).Place().equals(s.Place())) {
				tmp = (Start)st;
				state = true;
			}
		if(state == false) {
			throw new Error("Start not found");			
		}
		return map.get(tmp).place(destination);
	} 
}