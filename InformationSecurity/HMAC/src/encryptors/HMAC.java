package encryptors;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;

public class HMAC {

	public static void verify() {
		String computed = SHA1.encode(FilesContentHolder.getInputFileContent());
		System.out.println(computed.equals(FilesContentHolder.getDigestFileContent()) ? "Accept" : "Reject");
		System.out.println(FilesContentHolder.getKeyFileContent());
	}

	public static void compute() {
		FileWriter.write(SHA1.encode(FilesContentHolder.getInputFileContent()));
		System.out.println("Done");
	}

	// function hmac (key, message)
	// if (length(key) > blocksize) then
	// key = hash(key) // keys longer than blocksize are shortened
	// end if
	// if (length(key) < blocksize) then
	// key = key ∥ [0x00 * (blocksize - length(key))] // keys shorter than
	// blocksize are zero-padded (where ∥ is concatenation)
	// end if
	//
	// o_key_pad = [0x5c * blocksize] ⊕ key // Where blocksize is that of the
	// underlying hash function
	// i_key_pad = [0x36 * blocksize] ⊕ key // Where ⊕ is exclusive or (XOR)
	//
	// return hash(o_key_pad ∥ hash(i_key_pad ∥ message)) // Where ∥ is
	// concatenation
	// end function

}
