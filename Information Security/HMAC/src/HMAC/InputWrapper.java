package HMAC;

public class InputWrapper {
	private static String _inputFile = "";
	private static String _digestFile = "";
	private static String _keyFile = "";
	private static String _functionOption = "";
	
	private static String inputFile = "";
	private static String digestFile = "";
	private static String keyFile = "";
	private static String functionOption = "";
	
	public static String getInputFile() {
		return inputFile;
	}
	public static void setInputFile(String inputFile) {
		InputWrapper.inputFile = inputFile;
	}
	public static String getDigestFile() {
		return digestFile;
	}
	public static void setDigestFile(String digestFile) {
		InputWrapper.digestFile = digestFile;
	}
	public static String getKeyFile() {
		return keyFile;
	}
	public static void setKeyFile(String keyFile) {
		InputWrapper.keyFile = keyFile;
	}
	public static String getFunctionOption() {
		return functionOption;
	}
	public static void setFunctionOption(String functionOption) {
		InputWrapper.functionOption = functionOption;
	}

}
