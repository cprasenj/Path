import java.util.*;
import java.io.*;

class startNotFoundError extends Exception {
    startNotFoundError(Throwable cause) { super(cause); }
}

public class Path{
	public Map<Start, Destination> map = new HashMap<Start, Destination>();
	private Set<String> startList = new HashSet<String>();
	private String path = "";
	private int cost = 0;

	private boolean isStartPresent(String place) {
		Object[] keyset = map.keySet().toArray();
		for(Object key : keyset)
			if(((Start)key).Place().equals(place))
				return true;
		return false;
	}

	public void insertPath(String start,String destination,int cost) {
		Object[] keyset = map.keySet().toArray();
		if(isStartPresent(start))
			for(Object key : keyset) {
				if(((Start)key).Place().equals(start))
					map.get((Start)key).insert(destination,cost);
		}
		else
			map.put(new Start(start), new Destination(destination,cost));
	}

	private String findNewStart(Start start) {
		Destination s = map.get(start);
		Object[] dList = (s.set).toArray();
		Object[] starts = startList.toArray();
		String newStart = null;
		Object[] list = map.keySet().toArray();
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

	private boolean isPresentInPathUnderConstruction(String place) {
		boolean isPresent = false;
		Object[] placeList = path.split("&&")[path.split("&&").length-1].split("->");
		for(Object p :placeList)
			if(((String)p).trim().equals(place))
				isPresent = true;
		return isPresent;
	}

	private boolean alternativeStart(String s) {
		String[] underConstructionPath = path.split("&&")[path.split("&&").length-1].split("->");
		boolean tORf = path.split("&&").length>1 && 
		path.split("&&")[path.split("&&").length-2].split("->").length>underConstructionPath.length; 
		if(tORf)
			if(!path.split("&&")[path.split("&&").length-2].split("->")[underConstructionPath.length].equals(s))
				return isPresentInPathUnderConstruction(s);
		return true;
	}

	private boolean isPresentInPath(String s) {
		boolean isPresent = false;
		char[] p = path.toCharArray();
		char[] comparator = s.toCharArray();
		for(int i = 0;i<p.length;i++) 
			if(p[i]==comparator[0]){
				for(int j = 0;j<comparator.length;j++) {
					if((i+j)<p.length && p[i+j]==comparator[j])
						isPresent = true;
					else
						isPresent = false;
				}
			if(isPresent)
				isPresent = alternativeStart(s);
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
		state =  hasPath(newStart,destination);
		return state;
	}
	
	public boolean pathFinder(String start,String destination) {
		boolean tORf = false;
		tORf = hasPath(start,destination);			
		if(tORf){
			path += "&& ";
			if(path.split("&&").length == 2 && !path.split("&& ")[0].equals(path.split("&&")[1])) 
				tORf = pathFinder(start,destination);
		}
		return path.length() == 0 ? false : true;
	}

	public boolean isThereAnyPath(String start,String destination) {
		Start s = new Start(start),tmp = null;
		Object[] set = map.keySet().toArray();
		boolean state = false;
		for(Object st : set)
			if(((Start)st).Place().equals(s.Place())) {
				tmp = (Start)st;
				state = true;
			}
		if(!state) return false; 
		if(!map.get(tmp).place(destination)) 
			return continueSearch(tmp,start,destination);
		if(map.get(tmp).place(destination))
			path+=start+"->"+destination;
		startList.add(destination);
		return map.get(tmp).place(destination);
	}

	public boolean hasPath(String start,String destination) {
		return (isThereAnyPath(start,destination)) ? true : isThereAnyPath(destination,start);
	}

	private String pathAlterNator() {
		String newPath = "";
		String[] paths = path.split("&&");
		for(int i = 0;i<paths.length-1;i++) {
			String[] spots = paths[i].split("->");
			for(int j = spots.length-1;j>0;j--){
				newPath+=spots[j]+"->";
			}
			newPath+=spots[0].trim()+"&&";
		}
		return newPath;
	}

	public String getPath(String start,String destination) {
		if(!isThereAnyPath(start,destination)) {
			return pathAlterNator();
		}
		return this.path;
	}

	public Map getCombinations() {
		return this.map;
	} 
}