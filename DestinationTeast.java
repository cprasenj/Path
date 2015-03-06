import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class DestinationTeast {
	private Destination dest(String place,Integer cost) {
		Destination d = new Destination(place,cost);
		d.insert("Dubai",500);
		d.insert("Bangalore",500);
		return d;
	}

	@Test
	public void place_will_tell_if_a_given_place_is_present_in_destination_or_not() {
		Destination d = this.dest("Kolkata",500);
		assertTrue(d.place("Bangalore"));
		assertFalse(d.place("London"));		
	}

	@Test
	public void getPlace_gives_the_destination_if_the_string_is_given(){
		Destination d = this.dest("Kolkata",500);
		assertEquals(d.getPlace("Kolkata"),"Kolkata");
		assertEquals(d.getPlace("Patiala"),null);
	}


}