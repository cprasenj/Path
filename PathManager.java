import java.util.*;
import java.io.*;

public class PathManager{
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
}