package HMAC;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesContentHolder {
	private String inputFileContent;
	private String digestFileContent;
	private String KeyFileContent;

	public FilesContentHolder(String inputFile, String digestFile, String keyFile) {
		try {
			System.out.println( );
			this.setInputFileContent(new String(Files.readAllBytes(Paths.get(inputFile))));
			this.setDigestFileContent(new String(Files.readAllBytes(Paths.get(digestFile))));
			this.setKeyFileContent(new String(Files.readAllBytes(Paths.get(keyFile))));

		} catch (Exception e) {
			System.err.println("We hit exception while reading one of the files. \n Expection: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	public String getKeyFileContent() {
		return KeyFileContent;
	}

	public void setKeyFileContent(String keyFileContent) {
		KeyFileContent = keyFileContent;
	}

	public String getDigestFileContent() {
		return digestFileContent;
	}

	public void setDigestFileContent(String digestFileContent) {
		this.digestFileContent = digestFileContent;
	}

	public String getInputFileContent() {
		return inputFileContent;
	}

	public void setInputFileContent(String inputFileContent) {
		this.inputFileContent = inputFileContent;
	}

}
