package database;
import java.util.HashMap;
import java.util.Set;

public class Database {

	private static HashMap<String, String> records = new HashMap<>();

	static {
		records.put("Davie Jones", "100");
		records.put("Arik Benado", "40");
		records.put("Peres", "IntegerSizeOverflowException");
		records.put("Dave", "32");
		records.put("Ann", "20");
	}

	public static String get(String k) {
		String res = records.get(k);
		return res == null ? "" : res;
	}

	public static void insert(String k, String v) {
		records.put(k, v);
	}

	public static Set<String> getAll() {
		return records.keySet();
	}
}
