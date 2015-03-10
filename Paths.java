import java.util.*;
import java.io.*;

public class Paths {
	public static void main(String[] args) {
		if(args[0]==null || !Arrays.asList(args).contains("-f")) {
			System.out.println("Improper input");
			return;
		}
		PathManager mgr = new PathManager();
		mgr.workAccordingToOption(args);
	}
}
