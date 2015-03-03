import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

public class FileReaderTest{
	@Test
	public void readsFromfileAndGivesString() {
		FileReader f = new FileReader();
		String expected = "Sayli Kadam",actual = null;
		try{
			actual = f.FileReader("abc.txt");
		}
		catch(Exception e){}
		assertEquals(expected.equals(actual),true);
	}

	@Test
	public void readsFromfileAndGivesStoredString() {
		FileReader f = new FileReader();
		String expected = "Pooja Rani\r\n",actual = null;
		try{
			actual = f.FileReader("rani.txt");
		}
		catch(Exception e){}
		assertEquals(expected.equals(actual),true);
	}
} 