import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class DestinationTeast {
	private Destination dest(String place) {
		Destination d = new Destination(place);
		d.insert("Dubai");
		d.insert("Bangalore");
		return d;
	}

	@Test
	public void place_will_tell_if_a_given_place_is_present_in_destination_or_not() {
		Destination d = this.dest("Kolkata");
		assertTrue(d.place("Bangalore"));
		assertFalse(d.place("London"));		
	}

	@Test
	public void getPlace_gives_the_destination_if_the_string_is_given(){
		Destination d = this.dest("Kolkata");
		assertEquals(d.getPlace("Kolkata"),"Kolkata");
		assertEquals(d.getPlace("Patiala"),null);
	}


}