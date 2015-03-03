public class Paths {
	private static Path path() {
		Path p = new Path();
		p.insertPath("Bangalore","Singapore");
		p.insertPath("Singapore","Seoul");
		p.insertPath("Singapore","Dubai");
		p.insertPath("Seoul","Beijing");
		p.insertPath("Beijing","Tokyo");
		p.insertPath("Singapore","Tokyo");
		return p;
	}

	public static void main(String[] args) {
		Path p = path();
		boolean state = false;
		if(args!=null) {
			String start = args[0];
			String destination = args[1];
			try{
				state = p.hasPath(start,destination);
			}
			catch(startNotFoundError e){
				throw new Error(e);
			}
			System.out.println(state);
		}		
	}	
}
