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
			p.insertPath(stDest[1],stDest[0]);	
		}
		return p;
	}

	public static String addCountry(String fileName,Path p) {
		String result = "";
		String[] countryCity = null;
		countryCity = fileReader(fileName).split("\r\n");
		String[] path = p.getPath().split("->");
		for(String st:path){
			for(String c : countryCity){
				if(c.split(",")[0].equals(st))
					result+=(st+"["+c.split(",")[1]+"] ");
			}
		}
		return result;	
	}

	public static void main(String[] args) {
		boolean state = false;
		if(args[0]==null) {
			System.out.println("No input");
			return;
		}
		Path p = pathSetter(args[1]);
		try{
			state = p.hasPath(args[args.length-2],args[args.length-1]);
		}
		catch(startNotFoundError e){
			System.out.println("City does not belong to list");
		}
		System.out.println(state);
		if(state){
			if(args.length == 4)
				System.out.println(p.getPath());
			else 
				System.out.println(addCountry(args[3],p));
		}
	}	
}
