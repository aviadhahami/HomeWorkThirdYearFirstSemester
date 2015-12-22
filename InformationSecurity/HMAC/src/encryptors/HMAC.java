package encryptors;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;

public class HMAC {
	private static final int BLOCKSIZE = 64;

	public static void verify() {
		String computed = SHA1.encode(FilesContentHolder.getInputFileContent());
		System.out.println(computed.equals(FilesContentHolder.getDigestFileContent()) ? "Accept" : "Reject");
		System.out.println(FilesContentHolder.getKeyFileContent());
	}

	public static void compute() {
		FileWriter.write(computeHmac(SHA1.encode(FilesContentHolder.getInputFileContent())));
		System.out.println("Done");
	}

	private static String computeHmac(String string) {
		StringBuilder sb;

		String key = FilesContentHolder.getKeyFileContent();
		int keyLength = key.length();

		if (keyLength > BLOCKSIZE) {
			// keys longer than 64byte are shortened
			key = SHA1.encode(key);

		}
		if (keyLength < BLOCKSIZE) {
			// Keys shorter than BLOCKSIZE are zero-padded from the right to fit
			// the BLOCKSIZE
			sb = new StringBuilder();
			sb.append(key);
			for (int i = 0; i < BLOCKSIZE - keyLength; i++) {
				sb.append("0");
			}
			key = sb.toString();
		}
		StringBuilder o_key_pad = new StringBuilder();
		for (byte b : key.getBytes()) {
			o_key_pad.append(Integer.toHexString(xor(0x5c, b)));
		}
		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the
		// underlying hash function

		StringBuilder i_key_pad = new StringBuilder();
		for (byte b : key.getBytes()) {
			i_key_pad.append(Integer.toHexString(xor(0x36, b)));
		}

		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)

		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function

		return SHA1.encode(o_key_pad.toString()
				.concat(SHA1.encode(i_key_pad.toString().concat(FilesContentHolder.getInputFileContent()))));
	}

	/**
	 * Very not special bitwise XOR
	 * 
	 * @param i
	 *            - first number to XOR
	 * @param b
	 *            - second number to XOR
	 * @return byte value of XOR operation
	 */
	private static byte xor(int i, byte b) {
		return (byte) (i ^ b);
	}

}
