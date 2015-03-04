import java.util.*;
import java.io.*;

public class Paths {
	private Map<String,String> map = new HashMap<String,String>();

	private static String fileReader(String fileName) {
		FileReader f = new FileReader();
		String content = null;
		try{
			content = f.FileReader(fileName);
		}
		catch(Exception e){}
		return content;
	}

	private static Path pathSetter(String fileName) {
		Path p = new Path();
		String[] contentArray = fileReader(fileName).split("\r\n");
		for(String s:contentArray) {
			String[] stDest = s.split(",");
			p.insertPath(stDest[0],stDest[1]);	
		}
		return p;
	}

	public static void main(String[] args) {
		boolean state = false;
		Path p = null;
		Map<Start, Destination> map = null;
		String[] countryCity = null;
		String result = "";
		if(args[0]==null) {
			System.out.println("No input");
			return;
		}
		if(args[0].equals("-f"))
			p = pathSetter(args[1]);
		String start = args[args.length-2];
		String destination = args[args.length-1];
		try{
			state = p.hasPath(start,destination);
		}
		catch(startNotFoundError e){
			System.out.println("City does not belong to list");
		}
		System.out.println(state);
		if(state){
			if(args.length == 4)
				System.out.println(p.getPath());
			else{
				countryCity = fileReader(args[3]).split("\r\n");
				String[] path = p.getPath().split("->");
				for(String st:path){
					for(String c : countryCity){
						if(c.split(",")[0].equals(st))
							result+=(st+"["+c.split(",")[1]+"] ");
					}
				} 
				System.out.println(result);
			}
		}
	}	
}
