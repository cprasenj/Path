import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class StartTest {
	@Test
	public void start_holds_a_place_name_and_returns_if_asked() {
        Start s = new Start("Kolkata");
        assertEquals(s.Place(), "Kolkata");
    }
}
