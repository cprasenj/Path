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
		p.insertPath("Bangalore","Singapore");
		p.insertPath("Singapore","Dubai");
		p.insertPath("Singapore","Seoul");
		p.insertPath("Seoul","Beijing");
		p.insertPath("Beijing","Tokyo");
		p.insertPath("Dubai","Seoul");
		// for(String s:contentArray) {
		// 	String[] stDest = s.split(",");
		// 	p.insertPath(stDest[0],stDest[1]);
		// 	// p.insertPath(stDest[1],stDest[0]);	
		// }
		return p;
	}

	public static void addCountry(String fileName,Path p) {
		String result = "";
		String[] countryCity = null;
		countryCity = fileReader(fileName).split("\r\n");
		String[] path = p.getPath().split("&&");
		for(String way :path) {
			String[] places = way.split("->");
			for(int i=0;i<places.length-2;i++){
				for(String c : countryCity){
					if(c.split(",")[0].equals(places[i]))
						result+=(places[i]+"["+c.split(",")[1]+"] ");
				}
			}
			System.out.println(result);
			result = "";
		}
	}

	public static void main(String[] args) {
		boolean state = false;
		String[] pathList = null;
		if(args[0]==null) {
			System.out.println("No input");
			return;
		}
		Path p = pathSetter(args[1]);
		state = p.pathFinder(args[args.length-2],args[args.length-1]);
		System.out.println(state);
		if(state){
			if(args.length == 4){
				pathList = p.getPath().split("&&");
				for(String s : pathList)
					System.out.println(s);
			}
			else 
				addCountry(args[3],p);
		}
	}	
}
