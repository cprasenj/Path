import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class PathManagerTest{
	@Test
	public void inputParser_gives_pathFileName_from_options() {
		PathManager mgr = new PathManager();
		String[] optionList = {"-f","one.txt","-c","two.txt","Bangalore","Kolkata"};
		Map allOptions = mgr.inputParser(optionList);
		assertEquals(allOptions.get("pathFile"),"one.txt");
		assertEquals(allOptions.get("cityFile"),"two.txt");
		assertEquals(allOptions.get("start"),"Bangalore");
		assertEquals(allOptions.get("destination"),"Kolkata");
	} 

	@Test
	public void inputParser_gives_pathFileName_from_options_and_sets_cityFile_to_null() {
		PathManager mgr = new PathManager();
		String[] optionList = {"-f","one.txt","Bangalore","Kolkata"};
		Map allOptions = mgr.inputParser(optionList);
		assertEquals(allOptions.get("pathFile"),"one.txt");
		assertEquals(allOptions.get("cityFile"),null);
		assertEquals(allOptions.get("start"),"Bangalore");
		assertEquals(allOptions.get("destination"),"Kolkata");
	}

	@Test
	public void inputParser_gives_pathFileName_from_options_for_any_order() {
		PathManager mgr = new PathManager();
		String[] optionList = {"-c","two.txt","-f","one.txt","Bangalore","Kolkata"};
		Map allOptions = mgr.inputParser(optionList);
		assertEquals(allOptions.get("pathFile"),"one.txt");
		assertEquals(allOptions.get("cityFile"),"two.txt");
		assertEquals(allOptions.get("start"),"Bangalore");
		assertEquals(allOptions.get("destination"),"Kolkata");
	}

	@Test
	public void inputParser_gives_pathFileName_from_options_for_any_order_of_start_and_end() {
		PathManager mgr = new PathManager();
		String[] optionList = {"Bangalore","-c","two.txt","-f","one.txt","Kolkata"};
		Map allOptions = mgr.inputParser(optionList);
		assertEquals(allOptions.get("pathFile"),"one.txt");
		assertEquals(allOptions.get("cityFile"),"two.txt");
		assertEquals(allOptions.get("start"),"Bangalore");
		assertEquals(allOptions.get("destination"),"Kolkata");
	} 

}