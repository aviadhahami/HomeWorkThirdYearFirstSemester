package encryptors;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.codec.binary.Hex;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;

public class HMAC {
	private static final int BLOCKSIZE = 64;

	public static void verify() {
		// TODO: implement this
	}

	public static void compute() {
		FileWriter.write(computeHmac(SHA1.encode(FilesContentHolder.getInputFileContent())));
		System.out.println("Done");
	}

	private static String computeHmac(byte[] bs) {

		byte[] key = FilesContentHolder.getKeyFileContent();
		byte[] paddedKey = null;

		// keys longer than 64byte are shortened
		// FIXME: convert ASCII bytes to actual bytes (0x39, 0x59, ...)

		if(key.length > BLOCKSIZE){
			byte[] hashedKey = SHA1.encode(key);
			Arrays.fill(paddedKey, (byte)0x00);
			for (int i = 0; i < hashedKey.length; i++) {
				paddedKey[i] = hashedKey[i];
			}
		}
		
		// FIXME: paddedKey is 20 bytes, should be 64

		// Keys shorter than BLOCKSIZE are zero-padded from the right to fit
		// the BLOCKSIZE

		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)

		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
		return new String(sha);
	}

}
