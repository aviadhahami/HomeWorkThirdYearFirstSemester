package parsers;

import java.io.File;

import IOHandlers.InputWrapper;

public class Parser {

	public static InputWrapper parseInput(String[] args) {
		InputWrapper parsed = new InputWrapper();
		String inputFile = "";
		String digestFile = "";
		String keyFile = "";
		String functionOption = "";
		if (args.length == 4) {

			// Parse from args
			inputFile = args[0].replace("/", File.separator).replace("\\", File.separator);
			digestFile = args[1].replace("/", File.separator).replace("\\", File.separator);
			keyFile = args[2].replace("/", File.separator).replace("\\", File.separator);
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
