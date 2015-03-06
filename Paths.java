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
		p.insertPath("Bangalore","Singapore",500);
		p.insertPath("Singapore","Dubai",500);
		p.insertPath("Singapore","Seoul",500);
		p.insertPath("Seoul","Beijing",500);
		p.insertPath("Beijing","Tokyo",500);
		p.insertPath("Dubai","Seoul",500);
		// for(String s:contentArray) {
		// 	String[] stDest = s.split(",");
		// 	p.insertPath(stDest[0],stDest[1]);
		// }
		return p;
	}

	public static void addCountry(String fileName,Path p) {
		String result = "";
		String[] countryCity = null;
		countryCity = fileReader(fileName).split("\r\n");
		String[] path = p.getPath().split("&&");
		for(int j = 0;j<path.length-1;j++) {
			String[] places = path[j].split("->");
			for(int i=0;i<places.length;i++){
				for(String c : countryCity){
					if(c.split(",")[0].equals(places[i]) || (" "+c.split(",")[0]).equals(places[i])){
						result+=(places[i]+"["+c.split(",")[1]+"] ");
					}
				}
			}
			System.out.println(result);	
			result = "";
		}
	}

	public static int calculateCost(String path,Path p) {
		int cost = 0;
		String[] places = path.split("->");
		Object[] startList = p.map.keySet().toArray();
		int limit = places.length;
		for(int i = 1;i<=limit;i++) {
			for(Object o:startList){
				Map<String,Integer> destinationCostList = p.map.get((Start)o).costAndDestination;
				Object[] dts = destinationCostList.keySet().toArray();
				for(Object ob:dts)
					if(places[i-1].equals(((Start)o).Place()) && places[i].equals((String)ob)){
						cost+=p.map.get((Start)o).costAndDestination.get((String)ob);
				}
			}
		}
		return cost;
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
				for(int i = 0;i<pathList.length-1 ;i++) {
					System.out.println((pathList[i]+"   "+calculateCost(pathList[i].trim(),p)).trim());
				}
			}
			else {
				addCountry(args[3],p);
			}
		}
	}	
}
