package HMAC;

import java.io.File;

public class Parser {

	public static InputWrapper parseInput(String[] args) {
		InputWrapper parsed = new InputWrapper();
		String inputFile = "";
		String digestFile = "";
		String keyFile = "";
		String functionOption = "";
		if (args.length == 4) {

			// Parse from args
			inputFile = args[0].replace("/", File.pathSeparator).replace("\\", File.pathSeparator);
			digestFile = args[1].replace("/", File.pathSeparator).replace("\\", File.pathSeparator);
			keyFile = args[2].replace("/", File.pathSeparator).replace("\\", File.pathSeparator);
			functionOption = args[3];
		}

		// Populate
		InputWrapper.setInputFile(inputFile);
		InputWrapper.setDigestFile(digestFile);
		InputWrapper.setKeyFile(keyFile);
		InputWrapper.setFunctionOption(functionOption);

		return parsed;
	}

}
