package IOHandlers;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;

public class FilesContentHolder {
	private static byte[] inputFileContent;
	private static byte[] digestFileContent;
	private static byte[] KeyFileContent;

	public FilesContentHolder(String inputFile, String digestFile, String keyFile) {
		try {
			this.setInputFileContent(Files.readAllBytes(Paths.get(inputFile)));
			this.setDigestFileContent(Files.readAllBytes(Paths.get(digestFile)));
			this.setKeyFileContent(Base64.decodeBase64((Files.readAllBytes(Paths.get(keyFile)))));

		} catch (Exception e) {
			System.err.println("We hit exception while reading one of the files. \n Expection: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static byte[] getKeyFileContent() {
		return KeyFileContent;
	}

	public void setKeyFileContent(byte[] keyFileContent) {
		KeyFileContent = keyFileContent;
	}

	public static byte[] getDigestFileContent() {
		return digestFileContent;
	}

	public void setDigestFileContent(byte[] digestFileContent) {
		FilesContentHolder.digestFileContent = digestFileContent;
	}

	public static byte[] getInputFileContent() {
		return inputFileContent;
	}

	public void setInputFileContent(byte[] inputFileContent) {
		FilesContentHolder.inputFileContent = inputFileContent;
	}

}
