package server;

public class Console {

	public static void log(String msg){
		System.out.println(msg);
	}
	public static void log(int msg){
		System.out.println(msg);
	}
	
	public static void logErr(String msg){
		System.err.println(msg);
	}
	public static void logErr(int msg){
		System.err.println(msg);
	}
}
