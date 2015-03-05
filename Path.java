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
		if(map.get(s)!=null)
			map.get(s).insert(destination);
		else
			map.put(new Start(start), new Destination(destination));
	}

	private String findNewStart(Start start) {
		Destination s = map.get(start);
		Object[] dList = (s.set).toArray();
		Object[] starts = startList.toArray();
		Object[] list = map.keySet().toArray();
		Start begin = null;
		for(Object d:dList){
			for(Object st:starts){
				if(((String)d).equals((String)st)==false)
					for(Object c:list){
						if((((Start)c).Place()).equals((String)d)) {
							if(dList.length == 1)
								return (String)d;
							if(!isPresentInPath((String)d))
								return (String)d;
						}
					}
			}
		}
		return "noStart";
	}

	private boolean isPresentInPath(String s) {
		boolean isPresent = false;
		char[] p = path.toCharArray();
		char[] comparator = s.toCharArray();
		for(int i = 0;i<p.length;i++) {
			if(p[i]==comparator[0]){
				for(int j = 0;j<comparator.length;j++) {
					if((i+j)<p.length && p[i+j]==comparator[j])
						isPresent = true;
					else
						isPresent = false;
				}
			}
			if(isPresent) return true;
		}
		return isPresent;
	}

	private boolean continueSearch(Start tmp,String start,String destination) {
		this.path+=start+"->";
		startList.add(start);
		String newStart = findNewStart(tmp);
		boolean state = false;
		if(newStart == "noStart") return false;
		startList.add(newStart);
		try{
			state =  hasPath(newStart,destination);
		}
		catch(startNotFoundError e){}
		return state;
	}
	public boolean pathFinder(String start,String destination) {
		boolean tORf = false;
		try{
			tORf = hasPath(start,destination);			
		}		
		catch(startNotFoundError e){
			throw new Error(e);
		}
		if(tORf){
			path += "&&";
			// try{
				tORf = pathFinder(start,destination);
			// }
			// catch(startNotFoundError e){}
		}
		return path.length() == 0 ? false : true;
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
		if(!map.get(tmp).place(destination)) 
			return continueSearch(tmp,start,destination);
		if(map.get(tmp).place(destination))
			path+=start+"->"+destination;
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