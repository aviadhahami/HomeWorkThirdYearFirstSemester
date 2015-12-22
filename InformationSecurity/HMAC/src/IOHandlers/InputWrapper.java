package IOHandlers;

import java.io.File;

/**
 * InputWrapper supplies easy access to all input files required to run the
 * program. The wrapper will auto-use defaults in case of empty strings / null
 * inputs
 *
 */
public class InputWrapper {
	private static String HMAC = System.getProperty("user.dir") + File.separatorChar + "HMAC";
	private static String _inputFile = HMAC + File.separator + "input.txt";
	private static String _digestFile = HMAC + File.separator + "digest.txt";
	private static String _keyFile = HMAC + File.separator + "key.txt";
	private static String _functionOption = "compute";

	private static String inputFile = "";
	private static String digestFile = "";
	private static String keyFile = "";
	private static String functionOption = "";

	public static String getInputFile() {
		return inputFile;
	}

	public static void setInputFile(String inputFile) {
		InputWrapper.inputFile = inputFile == null || inputFile.equals("") ? _inputFile : inputFile;
	}

	public static String getDigestFile() {
		return digestFile;
	}

	public static void setDigestFile(String digestFile) {
		InputWrapper.digestFile = digestFile == null || digestFile.equals("") ? _digestFile : digestFile;
	}

	public static String getKeyFile() {
		return keyFile;
	}

	public static void setKeyFile(String keyFile) {
		InputWrapper.keyFile = keyFile == null || keyFile.equals("") ? _keyFile : keyFile;
	}

	public static String getFunctionOption() {
		return functionOption;
	}

	public static void setFunctionOption(String functionOption) {
		InputWrapper.functionOption = functionOption == null || functionOption.equals("") ? _functionOption
				: functionOption;
	}

}
