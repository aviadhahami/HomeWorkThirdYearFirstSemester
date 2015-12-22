package encryptors;

import java.util.Arrays;

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

		byte[] key = FilesContentHolder.getKeyFileContent().getBytes();
		byte[] paddedKey = new byte[BLOCKSIZE];

		if (key.length > BLOCKSIZE) {
			// keys longer than 64byte are shortened
			paddedKey = SHA1.encode(new String(key)).getBytes();
		} else if (key.length < BLOCKSIZE) {
			// Keys shorter than BLOCKSIZE are zero-padded from the right to fit
			// the BLOCKSIZE
			paddedKey = new byte[BLOCKSIZE];
			Arrays.fill(paddedKey, (byte) 0x00);
			for (int i = 0; i < key.length; i++) {
				paddedKey[i] = key[i];
			}
		}

		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
		byte[] opad = Arrays.copyOf(paddedKey, BLOCKSIZE);
		byte[] ipad = Arrays.copyOf(paddedKey, BLOCKSIZE);
		for (int i = 0; i < opad.length; i++) {
			opad[i] = (byte) (opad[i] ^ 0x5c);
			ipad[i] = (byte) (ipad[i] ^ 0x36);
		}

		String message = FilesContentHolder.getInputFileContent();

		System.out.println("m=" + message + ";");
		System.out.println("key=" + FilesContentHolder.getKeyFileContent() + ";");

		System.out.println(Hex.encodeHexString(opad));
		System.out.println(Hex.encodeHexString(ipad));
		return SHA1.encode(new String(opad).concat(SHA1.encode(new String(ipad).concat(message))));
		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
	}

}
