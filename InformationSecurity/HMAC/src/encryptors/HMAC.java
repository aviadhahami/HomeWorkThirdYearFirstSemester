package encryptors;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;
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
		byte[] paddedKey = new byte[BLOCKSIZE];

		// keys longer than 64byte are shortened
		// FIXME: convert ASCII bytes to actual bytes (0x39, 0x59, ...)

		if (key.length > BLOCKSIZE) {
			byte[] hashedKey = SHA1.encode(key);
			Arrays.fill(paddedKey, (byte) 0x00);
			for (int i = 0; i < hashedKey.length; i++) {
				paddedKey[i] = hashedKey[i];
			}
			// Keys shorter than BLOCKSIZE are zero-padded from the right to fit
			// the BLOCKSIZE
		} else if (key.length < BLOCKSIZE) {
			Arrays.fill(paddedKey, (byte) 0x00);
			for (int i = 0; i < key.length; i++) {
				paddedKey[i] = key[i];
			}
		} else if (key.length == BLOCKSIZE) {
			paddedKey = key;
		}

		byte[] opad = new byte[BLOCKSIZE];
		byte[] ipad = new byte[BLOCKSIZE];
		// Arrays.fill(opad, 0x5c);
		// Arrays.fill(ipad, 0x36);
		int i = 0;
		for (byte b : paddedKey) {
			opad[i] = (byte) (b ^ 0x5c);
			ipad[i] = (byte) (b ^ 0x36);
			i++;
		}

		byte[] message = FilesContentHolder.getInputFileContent();

		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
		byte[] context = new byte[ipad.length + message.length];
		System.arraycopy(ipad, 0, context, 0, ipad.length);
		System.arraycopy(message, 0, context, ipad.length, message.length);
		byte[] firstSha1 = SHA1.encode(context);

		byte[] secondSha = new byte[firstSha1.length + opad.length];
		System.arraycopy(opad, 0, secondSha, 0, opad.length);
		System.arraycopy(firstSha1, 0, secondSha, opad.length, firstSha1.length);
		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
		System.out.println(new String(Base64.encodeBase64(secondSha)));
		for (byte b : SHA1.encode(secondSha)) {
			System.out.print((int)b);
			System.out.print("_");
		}

		return new String(SHA1.encode(secondSha));
	}

}
