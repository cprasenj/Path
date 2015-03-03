import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Destination {
	public Set<String> set = new HashSet<String>();
	Destination(String name) {
		set.add(name);
	}

	public void insert(String s) {
		set.add(s);
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