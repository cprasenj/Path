import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class PathTest {
	private Path path() {
		Path p = new Path();
		p.insertPath("Bangalore","Singapore",500);
		p.insertPath("Singapore","Dubai",500);
		p.insertPath("Singapore","Seoul",500);
		p.insertPath("Seoul","Beijing",500);
		p.insertPath("Beijing","Tokyo",500);
		p.insertPath("Dubai","Seoul",500);
		return p;
	}

	@Test
	public void there_is_path_between_Bangalore_and_tokyo(){
		Path p = path();
		assertEquals(p.hasPath("Bangalore","Tokyo"),true);
	}

	@Test
	public void there_is_path_between_Singapore_and_Bangalore(){
		Path p = path();
		assertEquals(p.hasPath("Singapore","Bangalore"),true);
	}
    
	@Test
	public void there_is_path_between_Beijing_and_Seoul(){
		Path p = path();
		assertEquals(p.hasPath("Beijing","Seoul"),true);
	}

	@Test
	public void there_is_path_between_Seoul_and_tokyo(){
		Path p = path();
		assertEquals(p.hasPath("Seoul","Tokyo"),true);
	}

	@Test
	public void there_is_path_between_Beijing_and_Tokyo(){
		Path p = path();
		assertEquals(p.hasPath("Beijing","Tokyo"),true);
	}

	@Test
	public void there_is_path_between_Bangalore_and_Beijing(){
		Path p = path();
		assertEquals(p.hasPath("Bangalore","Beijing"),true);
	}

	@Test
	public void there_is_path_between_Bangalore_and_Dubai(){
		Path p = path();
		assertEquals(p.hasPath("Bangalore","Dubai"),true);
	}

	@Test
	public void there_is_path_between_singapore_Seol(){
		Path p = path();
		assertEquals(p.hasPath("Singapore","Seoul"),true);
	}

	@Test
	public void there_is_path_between_Kolkata_Seol(){
		Path p = path();
		assertEquals(p.hasPath("Kolkata","Seoul"),false);
	}

	@Test
	public void there_is_path_between_Seol_Kolkata(){
		Path p = path();
		assertEquals(p.hasPath("Seoul","Kolkata"),false);
	}

	@Test
	public void there_is_path_between_London_Kolkata(){
		Path p = path();
		assertEquals(p.hasPath("London","Kolkata"),false);
	}

	@Test
	public void there_is_path_between_singapore_Dubai(){
		Path p = path();
		assertEquals(p.hasPath("Singapore","Dubai"),true);
	}

	@Test
	public void there_is_path_between_Bangalore_Singapore(){
		Path p = path();
		assertEquals(p.hasPath("Bangalore","Singapore"),true);
	}

	@Test
	public void getPath_gives_between_path_Bangalore_Singapore(){
		Path p = path();
		assertEquals(p.pathFinder("Bangalore","Singapore"),true);
		assertEquals(p.getPath("Bangalore","Singapore").split("&&")[0],"Bangalore->Singapore");
	}

	@Test
	public void getPath_gives_between_path_Bangalore_Tokyo(){
		Path p = path();
		assertEquals(p.pathFinder("Bangalore","Tokyo"),true);
		String[] s = p.getPath("Bangalore","Tokyo").split("&&");
		assertEquals(s[0],"Bangalore->Singapore->Seoul->Beijing->Tokyo");
		assertEquals(s[1]," Bangalore->Singapore->Dubai->Seoul->Beijing->Tokyo");
	}

	@Test
	public void getPath_gives_between_path_Tokyo_Bangalore(){
		Path p = path();
		assertEquals(p.pathFinder("Tokyo","Bangalore"),true);
		String[] s = p.getPath("Tokyo","Bangalore").split("&&");
		assertEquals(s[0],"Tokyo->Beijing->Seoul->Singapore->Bangalore");
		assertEquals(s[1],"Tokyo->Beijing->Seoul->Dubai->Singapore->Bangalore");
	}
}




  