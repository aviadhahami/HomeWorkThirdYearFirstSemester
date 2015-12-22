package IOHandlers;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;

public class FilesContentHolder {
	private static String inputFileContent;
	private static String digestFileContent;
	private static String KeyFileContent;

	public FilesContentHolder(String inputFile, String digestFile, String keyFile) {
		try {
			System.out.println();
			this.setInputFileContent(new String(Files.readAllBytes(Paths.get(inputFile))));
			this.setDigestFileContent(new String(Files.readAllBytes(Paths.get(digestFile))));
			this.setKeyFileContent(new String(Base64.decodeBase64((Files.readAllBytes(Paths.get(keyFile))))));

		} catch (Exception e) {
			System.err.println("We hit exception while reading one of the files. \n Expection: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static String getKeyFileContent() {
		return KeyFileContent;
	}

	public void setKeyFileContent(String keyFileContent) {
		KeyFileContent = keyFileContent;
	}

	public static String getDigestFileContent() {
		return digestFileContent;
	}

	public void setDigestFileContent(String digestFileContent) {
		FilesContentHolder.digestFileContent = digestFileContent;
	}

	public static String getInputFileContent() {
		return inputFileContent;
	}

	public void setInputFileContent(String inputFileContent) {
		FilesContentHolder.inputFileContent = inputFileContent;
	}

}
