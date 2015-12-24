package encryptors;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;

public class HMAC {
	private static final int BLOCKSIZE = 64;

	public static void verify() {
		if (Base64.encodeBase64String(computeHmac()).equals(new String(FilesContentHolder.getDigestFileContent()))) {
			System.out.println("Accept");
		} else {
			System.out.println("Reject");
		}

	}

	public static void compute() {
		FileWriter.write(computeHmac());
		System.out.println("Done");
	}

	private static byte[] computeHmac() {
		// function hmac (key, message)
		byte[] msg = FilesContentHolder.getInputFileContent();
		byte[] key = FilesContentHolder.getKeyFileContent();

		// if (length(key) > blocksize) then
		// key = hash(key) // keys longer than blocksize are shortened
		// end if
		if (key.length > BLOCKSIZE) {
			byte[] tmpKey = SHA1.digestIt(key);
			key = new byte[20];
			key = tmpKey;
		}
		// if (length(key) < blocksize) then
		// key = key ∥ [0x00 * (blocksize - length(key))] // keys shorter than
		// blocksize are zero-padded (where ∥ is concatenation)
		// end if
		if (key.length < BLOCKSIZE) {
			byte[] tmpKey = new byte[BLOCKSIZE];
			Arrays.fill(tmpKey, (byte) 0);
			System.arraycopy(key, 0, tmpKey, 0, key.length);
			key = new byte[BLOCKSIZE];
			key = tmpKey;
		}

		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
		//
		byte[] opad = new byte[BLOCKSIZE];
		for (int j = 0; j < opad.length; j++) {
			opad[j] = (byte) (((int) key[j]) ^ ((int) 0x5c));
		}
		byte[] ipad = new byte[BLOCKSIZE];
		for (int j = 0; j < ipad.length; j++) {
			ipad[j] = (byte) (((int) key[j]) ^ ((int) 0x36));
		}

		byte[] context = new byte[ipad.length + msg.length];
		for (int i = 0; i < ipad.length; i++) {
			context[i] = ipad[i];
		}
		for (int i = 0; i < msg.length; i++) {
			context[i + ipad.length] = msg[i];
		}

		byte[] firstSha = SHA1.digestIt(context);

		context = new byte[opad.length + firstSha.length];
		System.arraycopy(opad, 0, context, 0, opad.length);
		System.arraycopy(firstSha, 0, context, opad.length, firstSha.length);
		byte[] scndSha = SHA1.digestIt(context);
		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
		return scndSha;
	}

}
