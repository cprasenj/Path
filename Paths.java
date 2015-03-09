import java.util.*;
import java.io.*;

public class Paths {
	private Map<String,String> map = new HashMap<String,String>();
	private static List<String> paths = new ArrayList<String>();
	private static List<Integer> costs = new ArrayList<Integer>();

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
			p.insertPath(stDest[0],stDest[1],Integer.parseInt(stDest[2]));
			p.insertPath(stDest[1],stDest[0],Integer.parseInt(stDest[2]));
		}
		return p;
	}

	public static void addCountry(String fileName,Path p,String start,String destination) {
		String result = "";
		String[] countryCity = null;
		countryCity = fileReader(fileName).split("\r\n");
		String[] path = p.getPath(start,destination).split("&&");
		for(int j = 0;j<path.length-1;j++) {
			String[] places = path[j].split("->");
			for(int i=0;i<places.length;i++){
				for(String c : countryCity){
					if(c.split(",")[0].equals(places[i]) || (" "+c.split(",")[0]).equals(places[i])){
						result+=(places[i]+"["+c.split(",")[1]+"] ");
					}
				}
			}
			System.out.println(result.trim() + calculateCost(path[j],p));	
			result = "";
		}
	}

	public static int calculateCost(String path,Path p) {
		int cost = 0;
		String[] places = path.split("->");
		Object[] startList = p.map.keySet().toArray();
		int limit = places.length;
		for(int i = 1;i<limit;i++) {
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
		if(args[0]==null || !Arrays.asList(args).contains("-f")) {
			System.out.println("Improper input");
			return;
		}
		Path p = pathSetter(args[1]);
		state = p.pathFinder(args[args.length-2],args[args.length-1]);
		System.out.println(state);
		if(state){
			if(args.length == 4){
				pathList = p.getPath(args[args.length-2],args[args.length-1]).split("&&");
				for(int i = 0;i<pathList.length;i++) {
					paths.add(pathList[i]+" : "+calculateCost(pathList[i].trim(),p));
					costs.add(calculateCost(pathList[i].trim(),p));
				}
				Object[] sortedCosts = costs.toArray();
				Arrays.sort(sortedCosts);
				for(int j = 0;j<sortedCosts.length;j++){
					for(String pt:paths){
						if(Integer.parseInt(pt.split(" : ")[1]) == (int)sortedCosts[j])
							System.out.println(pt.trim());
					}
				}
			}
			else {
				addCountry(args[3],p,args[args.length-2],args[args.length-1]);
			}
		}
	}	
}
