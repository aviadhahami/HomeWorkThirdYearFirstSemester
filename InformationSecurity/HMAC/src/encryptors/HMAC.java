package encryptors;

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;
import parsers.Utils;

public class HMAC {
	private static final int BLOCKSIZE = 64;

	public static void verify() {
		byte[] computed = SHA1.encode(FilesContentHolder.getInputFileContent());
		System.out.println(computed.equals(FilesContentHolder.getDigestFileContent()) ? "Accept" : "Reject");
		System.out.println(FilesContentHolder.getKeyFileContent());
	}

	public static void compute() {
		FileWriter.write(computeHmac(SHA1.encode(FilesContentHolder.getInputFileContent())));
		System.out.println("Done");
	}

	private static String computeHmac(byte[] bs) {

		byte[] key = FilesContentHolder.getKeyFileContent();
		byte[] paddedKey = new byte[BLOCKSIZE];

		if (key.length > BLOCKSIZE) {
			// keys longer than 64byte are shortened
			// FIXME: convert ASCII bytes to actual bytes (0x39, 0x59, ...)

			paddedKey = SHA1.encode(key);
			// FIXME: paddedKey is 20 bytes, should be 64
		}
		
		if (key.length < BLOCKSIZE) {
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

		byte[] message = FilesContentHolder.getInputFileContent();

		System.out.println(Hex.encodeHexString(opad));
		System.out.println(Hex.encodeHexString(ipad));

		byte[] context = new byte[ipad.length + message.length];

		for (int i = 0; i < context.length / 2; i++) {
			context[i] = ipad[i];// ipad
		}
		for (int i = 64; i < context.length; i++) {
			context[i] = message[i];
		}

		byte[] sha = SHA1.encode(context);

		context = new byte[BLOCKSIZE + sha.length];
		System.arraycopy(opad, 0, context, 0, opad.length);
		System.arraycopy(sha, 0, context, 64, sha.length);

		sha = SHA1.encode(context);

		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
		return new String(sha);
	}

}
