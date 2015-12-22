package encryptors;

import org.apache.commons.codec.binary.Hex;

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
		byte[] paddedKey = new byte[BLOCKSIZE];
		int keyLength = key.length();

		if (keyLength > BLOCKSIZE) {
			// keys longer than 64byte are shortened
			paddedKey = SHA1.encode(key).getBytes();
		}

		if (keyLength < BLOCKSIZE) {
			// Keys shorter than BLOCKSIZE are zero-padded from the right to fit
			// the BLOCKSIZE
			sb = new StringBuilder();
			sb.append(Hex.encodeHexString(key.getBytes()));
			for (int i = 0; i < BLOCKSIZE - keyLength; i++) {
				sb.append(0);
			}
			paddedKey = sb.toString().getBytes();
		}

		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		byte[] o_key_pad = new byte[BLOCKSIZE];
		for (int i = 0; i < BLOCKSIZE; i++) {
			o_key_pad[i] = (byte) (paddedKey[i] ^ 0x5c);
		}

		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
		byte[] i_key_pad = new byte[BLOCKSIZE];
		for (int i = 0; i < BLOCKSIZE; i++) {
			i_key_pad[i] = (byte) (paddedKey[i] ^ 0x36);
		}
		String message = FilesContentHolder.getInputFileContent();
		System.out.println("m=" + message + ";");
		System.out.println("key=" + FilesContentHolder.getKeyFileContent() + ";");
		
		
		return SHA1.encode(new String(o_key_pad)
				.concat(SHA1.encode(new String(i_key_pad).concat(message))));

		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
	}

}
