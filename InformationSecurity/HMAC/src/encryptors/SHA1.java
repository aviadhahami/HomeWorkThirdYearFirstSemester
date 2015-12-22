package encryptors;

/**
 * <h1>SHA1 class</h1> <br/>
 * This class implements the SHA1 algorithm.
 * 
 * @author aviadh
 *
 */
public class SHA1 {

	// SHA1 constants
	private int h0 = 0x67452301;
	private int h1 = 0xEFCDAB89;
	private int h2 = 0x98BADCFE;
	private int h3 = 0x10325476;
	private int h4 = 0xC3D2E1F0;

	/**
	 * encode is the static function one would use in order to calculate SHA1
	 * value for a given string
	 * 
	 * @param input
	 *            - String
	 * @return SHA1 encoded string
	 */
	public static String encode(String input) {
		// Break into chars
		String[] brokenInput = input.split("");

		// Convert letters into ASCII
		Integer[] asciiEncodedInput = new Integer[brokenInput.length];
		for (int i = 0; i < brokenInput.length; i++) {
			asciiEncodedInput[i] = (int) brokenInput[i].charAt(0);
		}

		// Convert ASCII numbers into binary
		// FIXME : verify this

		// Concat the binary values;
		long b = 0;
		for(int i : asciiEncodedInput){
			
		}

		return null;
	}
}