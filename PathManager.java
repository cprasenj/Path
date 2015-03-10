import java.util.*;
import java.io.*;

public class PathManager{
	private List<Integer> costs = new ArrayList<Integer>();
	private List<String> pathAndCost = new ArrayList<String>();

	private String fileReader(String fileName) {
		FileReader f = new FileReader();
		String content = null;
		try{
			content = f.FileReader(fileName);
		}
		catch(Exception e){}
		return content;
	}

	private Path pathSetter(String content) {
		Path p = new Path();
		String[] contentArray = content.split("\r\n");
		for(String s:contentArray) {
			String[] stDest = s.split(",");
			p.insertPath(stDest[0],stDest[1],Integer.parseInt(stDest[2]));
			p.insertPath(stDest[1],stDest[0],Integer.parseInt(stDest[2]));
		}
		return p;
	}

	public Map inputParser(String[] args) {
		Map<String,String> options = new HashMap<String,String>();
		int startIndex = -1,destinationIndex = -1;
		int pathFileIndex = Arrays.asList(args).indexOf("-f")+1;
		int cityFileIndex = (Arrays.asList(args).indexOf("-c")>=0) ? Arrays.asList(args).indexOf("-c")+1 : -1; 
		options.put("pathFile",args[pathFileIndex]);
		if(cityFileIndex >= 0)
			options.put("cityFile",args[cityFileIndex]);
		else
			options.put("cityFile",null);
		for(int i = 0;i<args.length;i++){
			if(i!=pathFileIndex && i!=pathFileIndex-1 && i!=cityFileIndex && i!=cityFileIndex-1) {
				if(startIndex < 0)
					startIndex = i;
				else
					destinationIndex = i;				
			}
		} 
		options.put("start",args[startIndex]);
		options.put("destination",args[destinationIndex]);
		return options;
	}

	private void calCulateCost(String[] pathList,Path p) {
		int cost = 0;
		String[] spots = null;
		Object[] allStarts = p.map.keySet().toArray();
		for(int j = 0;j < pathList.length-1;j++){
			spots = pathList[j].split("->");
			int limit = spots.length;
			for(int i = 1;i<limit;i++) {
				for(Object o:allStarts){
					Map<String,Integer> destinationCostList = p.map.get((Start)o).costAndDestination;
					Object[] dts = destinationCostList.keySet().toArray();
					for(Object ob:dts)
						if(spots[i-1].equals(((Start)o).Place()) && spots[i].equals((String)ob)){
							cost+=p.map.get((Start)o).costAndDestination.get((String)ob);
					}
				}
			}
			pathAndCost.add(pathList[j].trim()+" : "+cost);
			costs.add(cost);
			cost = 0;	
		}
	}

	private void addCountryName(String[] countryAndCity) {
		Object[] pathsAndCost = pathAndCost.toArray();
		List<String> pathAndCostWithCountry = new ArrayList<String>();
		for(int i = 0;i<pathsAndCost.length;i++){
			String path = "";
			String[] cities = (((String)pathsAndCost[i]).split(" : "))[0].split("->");
			for(int j = 0;j<cities.length-1;j++){
				for(String s:countryAndCity){
					if(s.split(",")[0].equals(cities[j])) {
						path += cities[j]+"["+s.split(",")[1]+"]"+"->";
					}
				}
			}
			for (String s:countryAndCity) {
				if(cities[cities.length-1].equals(s.split(",")[0]))
					path += cities[cities.length-1]+"["+s.split(",")[1]+"]";
			}
			path += " : "+((String)pathsAndCost[i]).split(" : ")[1];
			pathAndCostWithCountry.add(path);
		}
		this.pathAndCost = pathAndCostWithCountry;		
	}

	private void printPathAccordingToCost() {
		Object[] sortedCosts = costs.toArray();
		Arrays.sort(sortedCosts);
		for(int j = 0;j<sortedCosts.length;j++){
			for(String pt:pathAndCost){
				if(Integer.parseInt(pt.split(" : ")[1]) == (Integer)sortedCosts[j] && (Integer)sortedCosts[j]!=0)
					System.out.println(pt.trim());
			}
		}
	}

	public void workAccordingToOption(String[] args) {
		Map options = inputParser(args);
		String[] pathList = null,countryAndCity = null;
		Path p = pathSetter(fileReader((String)options.get("pathFile")));
        if(isCityExists((String)options.get("start"),(String)options.get("destination"),p,(String)options.get("pathFile"))) {
            boolean status = p.pathFinder((String) options.get("start"), (String) options.get("destination"));
            System.out.println(status);
            if (status) {
                pathList = p.getPath((String) options.get("start"), (String) options.get("destination")).split("&&");
                calCulateCost(pathList, p);
                if (options.get("cityFile") != null) {
                    countryAndCity = fileReader((String) options.get("cityFile")).split("\r\n");
                    addCountryName(countryAndCity);
                }
                printPathAccordingToCost();
            }
        }
	}

    public boolean isCityExists(String start, String destination, Path p, String pathFile) {
        boolean startPresent = false,destinationPresent = false;
        String[] pathFilecontent = fileReader(pathFile).split("\r\n");
        for (String pathSet : pathFilecontent) {
            String[] cities = pathSet.split(",");
            if(start.equals(cities[0]) || start.equals(cities[1])) startPresent = true;

            if(destination.equals(cities[0]) || destination.equals(cities[1])) destinationPresent =  true;
        }
        if(startPresent == true && destinationPresent == true){
            return true;
        }
        if(!startPresent) System.out.println(start+" is not present in data base");
        if(!destinationPresent) System.out.println(destination+" is not present in data base");
        return false;
    }
}