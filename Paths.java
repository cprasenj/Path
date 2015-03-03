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
		if(args[0]==null) {
			System.out.println("No input");
			return;
		} 
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
}
