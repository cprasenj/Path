import java.util.Map;
import java.util.HashMap;

class Start {
	private String place;
	Start(String name) {
		this.place = name;
	}

	public boolean equals(Object other) {
		return place == ((Start) other).Place();
	}

	public int hashCode() {
		return this.place.hashCode();
	}

	public String Place(){
		return this.place;
	}
}