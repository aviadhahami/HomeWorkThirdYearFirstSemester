package encryptors;

import IOHandlers.FileWriter;
import IOHandlers.FilesContentHolder;

public class HMAC {

	public static void verify() {
		// TODO Auto-generated method stub
		
	}

	public static void compute() {
		FileWriter.write(SHA1.encode(FilesContentHolder.getInputFileContent()));
	}

}
