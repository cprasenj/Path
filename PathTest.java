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
		p.insertPath("Singapore","Bangalore",500);
		p.insertPath("Dubai","Singapore",500);
		p.insertPath("Seoul","Singapore",500);
		p.insertPath("Beijing","Seoul",500);
		p.insertPath("Tokyo","Beijing",500);
		p.insertPath("Seoul","Dubai",500);
		return p;
	}

	@Test
	public void there_is_path_between_Bangalore_and_tokyo(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Bangalore","Tokyo"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Singapore_and_Bangalore(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Singapore","Bangalore"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Beijing_and_Seoul(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Beijing","Seoul"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Seoul_and_tokyo(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Seoul","Tokyo"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Beijing_and_Tokyo(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Beijing","Tokyo"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Bangalore_and_Beijing(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Bangalore","Beijing"),true);
		}
		catch(startNotFoundError err){}
	}


	@Test
	public void there_is_path_between_Bangalore_and_Dubai(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Bangalore","Dubai"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test(expected = java.lang.Error.class)
	public void startNotFoundError() {
		Path p = path();
		boolean ex = false;
		try{
    		p.hasPath("Kolkata","Tokyo");
		}
		catch(startNotFoundError err) {
			ex = true;
		}
		assertTrue(ex);
	}

	@Test
	public void there_is_path_between_singapore_Seol(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Singapore","Seoul"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_singapore_Dubai(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Singapore","Dubai"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void there_is_path_between_Bangalore_Singapore(){
		Path p = path();
		try{
			assertEquals(p.hasPath("Bangalore","Singapore"),true);
		}
		catch(startNotFoundError err){}
	}

	@Test
	public void getPath_gives_between_path_Bangalore_Singapore(){
		Path p = path();
		assertEquals(p.pathFinder("Bangalore","Singapore"),true);
		assertEquals(p.getPath().split("&&")[0],"Bangalore->Singapore");
	}

	@Test
	public void getPath_gives_between_path_Bangalore_Tokyo(){
		Path p = path();
		assertEquals(p.pathFinder("Bangalore","Tokyo"),true);
		String[] s = p.getPath().split("&&");
		assertEquals(s[0],"Bangalore->Singapore->Seoul->Beijing->Tokyo");
		assertEquals(s[1]," Bangalore->Singapore->Dubai->Seoul->Beijing->Tokyo");
	}

	@Test
	public void getPath_gives_between_path_Tokyo_Bangalore(){
		Path p = path();
		assertEquals(p.pathFinder("Tokyo","Bangalore"),true);
		String[] s = p.getPath().split("&&");
		assertEquals(s[0],"Tokyo->Beijing->Seoul->Singapore->Bangalore");
		// assertEquals(s[1],"Tokyo->Beijing->Seoul->Dubai->Singapore->Bangalore");
	}
}




  