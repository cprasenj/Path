import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Destination {
	public Set<String> set = new HashSet<String>();
	public Map<String,Integer> costAndDestination = new HashMap<String,Integer>();
	Destination(String name,Integer cost) {
		set.add(name);
		costAndDestination.put(name,cost);
	}

	public void insert(String s,Integer c) {
		set.add(s);
		costAndDestination.put(s,c);
	}

	public boolean place(String destination) {
		boolean state = false;
		for(String st : set) {
			if(st.equals(destination)){
				state = true;
			}
		}
		return state;
	}
	public String getPlace(String destination) {
		for(String st : set) {
			if(st.equals(destination)){
				return st;
			}
		}
		return null;
	}
}