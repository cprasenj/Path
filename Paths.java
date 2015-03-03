import java.util.*;
import java.io.*;

public class Paths {
	private static Path path(String fileName) {
		Path p = new Path();
		FileReader f = new FileReader();
		String content = null;
		try{
			content = f.FileReader(fileName);
		}
		catch(Exception e){}
		String[] contentArray = content.split("\r\n");
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
		if(args[0]==null) {
			System.out.println("No input");
			return;
		}
		if(args.length == 4){
			if(args[0].equals("-f"))
				p = path(args[1]);
			String start = args[2];
			String destination = args[3];
			try{
				state = p.hasPath(start,destination);
			}
			catch(startNotFoundError e){
				throw new Error(e);
			}
			System.out.println(state);
			if(state){
				System.out.println(p.getPath());
			}
		}
		if(args.length == 6){
			String result = "";
			if(args[0].equals("-f"))
				p = path(args[1]);
			String start = args[4];
			String destination = args[5];
			try{
				state = p.hasPath(start,destination);
			}
			catch(startNotFoundError e){
				throw new Error(e);
			}
			System.out.println(state);
			FileReader f = new FileReader();
			String content = null;
			try{
				content = f.FileReader(args[3]);
			}
			catch(Exception e){}
			String[] contentArray = content.split("\r\n");
			if(state){
				String[] path = p.getPath().split("->");
				for(String st:path){
					for(String c : contentArray){
						if(c.split(",")[0].equals(st))
							result+=(st+"["+c.split(",")[1]+"] ");
					}
				} 
				System.out.println(result);
			}
		} 
	}	
}
