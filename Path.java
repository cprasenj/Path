import java.util.*;
import java.io.*;

class startNotFoundError extends Exception {
    startNotFoundError(Throwable cause) { super(cause); }
}

public class Path{
	private Map<Start, Destination> map = new HashMap<Start, Destination>();
	private Set<String> startList = new HashSet<String>();
	private String path = "";
	public void insertPath(String start,String destination) {
		Start s = new Start(start);
		if(map.get(s)!=null){
			map.get(s).insert(destination);
		}
		else
			map.put(new Start(start), new Destination(destination));
	}

	private String findNewStart(Start start) {
		Destination s = map.get(start);
		Object[] dList = (s.set).toArray();
		Object[] starts = startList.toArray();
		for(Object d:dList){
			for(Object st:starts){
				if(((String)d).equals((String)st)==false)
					return (String)d;
			}
		}
		return "noStart";
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
		if(state == false) 
			throw new Error("Start not found");			
		if(state == true && map.get(tmp).place(destination) == false) {
			startList.add(start);
			this.path+=start+"->";
			String newStart = findNewStart(tmp);
			if(newStart == "noStart") return false;
			startList.add(newStart);
			return hasPath(newStart,destination);
		}
		if(map.get(tmp).place(destination)){
			path+=start+"->"+destination;
		}
		startList.add(destination);
		return map.get(tmp).place(destination);
	}

	public String getPath() {
		return this.path; 
	}
	public Map getCombinations() {
		return this.map;
	} 
}