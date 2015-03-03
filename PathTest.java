import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class PathTest {
	private Path path() {
		Path p = new Path();
		p.insertPath("Bangalore","Singapore");
		p.insertPath("Singapore","Seoul");
		p.insertPath("Singapore","Dubai");
		p.insertPath("Seoul","Beijing");
		p.insertPath("Beijing","Tokyo");
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
		try{
			assertEquals(p.hasPath("Bangalore","Singapore"),true);
			assertEquals(p.getPath(),"Bangalore->Singapore");
		}
		catch(startNotFoundError err){}
	}
}




  