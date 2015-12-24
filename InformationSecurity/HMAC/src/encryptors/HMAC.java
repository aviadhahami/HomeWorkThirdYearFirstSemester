package encryptors;

import java.util.Arrays;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;
import parsers.Utils;

public class HMAC {
	private static final int BLOCKSIZE = 64;

	public static void verify() {
		// TODO: implement this
	}

	public static void compute() {
		FileWriter.write(computeHmac());
		System.out.println("Done");
	}

	private static String computeHmac() {
		// function hmac (key, message)
		byte[] msg = FilesContentHolder.getInputFileContent();
		byte[] key = FilesContentHolder.getKeyFileContent();

		// if (length(key) > blocksize) then
		// key = hash(key) // keys longer than blocksize are shortened
		// end if
		if (key.length > 64) {
			byte[] tmpKey = SHA1.digestIt(key);
			key = new byte[20];
			key = tmpKey;
		}
		// if (length(key) < blocksize) then
		// key = key ∥ [0x00 * (blocksize - length(key))] // keys shorter than
		// blocksize are zero-padded (where ∥ is concatenation)
		// end if
		if (key.length < 64) {
			byte[] tmpKey = new byte[64];
			Arrays.fill(tmpKey, (byte) 0);
			System.arraycopy(key, 0, tmpKey, 0, key.length);
			key = new byte[64];
			key = tmpKey;
		}
	
		// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of
		// the underlying hash function
		// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
		//
		byte[] opad = new byte[64];
		for (int j = 0; j < opad.length; j++) {
			opad[j] = (byte) (((int) key[j]) ^ ((int) 0x5c));
		}
		byte[] ipad = new byte[64];
		for (int j = 0; j < ipad.length; j++) {
			ipad[j] = (byte) (((int) key[j]) ^ ((int) 0x36));
		}
		System.out.println(Utils.bytesToHex(ipad));
		
		byte[] context = new byte[ipad.length + msg.length];
		System.arraycopy(ipad, 0, context, 0, ipad.length);
		System.arraycopy(msg, 0, context, ipad.length, msg.length);

		byte[] firstSha = SHA1.digestIt(context);

		context = new byte[opad.length + firstSha.length];
		System.arraycopy(opad, 0, context, 0, opad.length);
		System.arraycopy(firstSha, 0, context, opad.length, firstSha.length);
		byte[] scndSha = SHA1.digestIt(context);
		// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
		// concatenation
		// end function
		return Utils.bytesToHex(scndSha);
	}

}
