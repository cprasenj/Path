import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class DestinationTeast {
	@Test
	public void place_will_tell_if_a_given_place_is_present_in_destination_or_not() {
		Destination d = new Destination("Kolkata");
		d.insert("Dubai");
		d.insert("Bangalore");
		assertTrue(d.place("Bangalore"));
		assertFalse(d.place("London"));		
	}	
}