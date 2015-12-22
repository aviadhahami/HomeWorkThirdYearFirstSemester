package encryptors;

/**
 * <h1>SHA1 class</h1> <br/>
 * This class implements the SHA1 algorithm.
 * 
 * @author aviadh
 *
 */
public class SHA1 {

	/**
	 * encode is the static function one would use in order to calculate SHA1
	 * value for a given string
	 * 
	 * @param input
	 *            - String
	 * @return SHA1 encoded string
	 */

	/*
	 * Bitwise rotate a 32-bit number to the left
	 */
	private static int rol(int num, int cnt) {
		return (num << cnt) | (num >>> (32 - cnt));
	}

	/*
	 * Take a string and return the base64 representation of its SHA-1.
	 */
	public static String encode(String str) {

		// Convert a string to a sequence of 16-word blocks, stored as an array.
		// Append padding bits and the length, as described in the SHA1 standard

		byte[] x = str.getBytes();
		int[] blks = new int[(((x.length + 8) >> 6) + 1) * 16];
		int i;

		for (i = 0; i < x.length; i++) {
			blks[i >> 2] |= x[i] << (24 - (i % 4) * 8);
		}

		blks[i >> 2] |= 0x80 << (24 - (i % 4) * 8);
		blks[blks.length - 1] = x.length * 8;

		// calculate 160 bit SHA1 hash of the sequence of blocks

		int[] w = new int[80];

		// Signed 32-bit ints
		int h0 = 1732584193;
		int h1 = -271733879;
		int h2 = -1732584194;
		int h3 = 271733878;
		int h4 = -1009589776;

		for (i = 0; i < blks.length; i += 16) {
			int olda = h0;
			int oldb = h1;
			int oldc = h2;
			int oldd = h3;
			int olde = h4;

			for (int j = 0; j < 80; j++) {
				w[j] = (j < 16) ? blks[i + j] : (rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1));

				int t = rol(h0, 5) + h4 + w[j]
						+ ((j < 20) ? 1518500249 + ((h1 & h2) | ((~h1) & h3))
								: (j < 40) ? 1859775393 + (h1 ^ h2 ^ h3)
										: (j < 60) ? -1894007588 + ((h1 & h2) | (h1 & h3) | (h2 & h3))
												: -899497514 + (h1 ^ h2 ^ h3));
				h4 = h3;
				h3 = h2;
				h2 = rol(h1, 30);
				h1 = h0;
				h0 = t;
			}

			h0 = h0 + olda;
			h1 = h1 + oldb;
			h2 = h2 + oldc;
			h3 = h3 + oldd;
			h4 = h4 + olde;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toHexString(h0));
		sb.append(Integer.toHexString(h1));
		sb.append(Integer.toHexString(h2));
		sb.append(Integer.toHexString(h3));
		sb.append(Integer.toHexString(h4));

		return sb.toString();
	}
}
