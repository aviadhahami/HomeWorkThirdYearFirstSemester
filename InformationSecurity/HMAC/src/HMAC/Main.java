package HMAC;

import IOHandlers.FilesContentHolder;
import IOHandlers.InputWrapper;
import encryptors.HMAC;
import parsers.Parser;

/**
 * Main class to run the HMAC. <br/>
 * The program receives the following (via CLI, in this order):
 * <ol>
 * <li>Input file(ASCII encoded)</li>
 * <li>Digest file</li>
 * <li>Key file(Which contains the key)</li>
 * <li>Functional option ("compute" in order to compute HMAC or "verify" in
 * order to verify HMACed file with given digest</li>
 * </ul>
 * 
 * @author aviadh & Aviyas
 *
 */
public class Main {
	/**
	 * Intro point for the app. The app receives 4 CLI arguments :
	 * <ol>
	 * <li>Path to input file (The message to encode)</li>
	 * <li>Path to digest file (The "dump" for the hash)</li>
	 * <li>Path to key file (The encryption key)</li>
	 * <li>Functionality pick ("compute" or "verify")</li>
	 * </ol>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Parser.parseInput(args);
		new FilesContentHolder(InputWrapper.getInputFile(), InputWrapper.getDigestFile(), InputWrapper.getKeyFile());
		if (InputWrapper.getFunctionOption().toLowerCase().equals("verify")) {
			System.err.println("VERIFY");
			HMAC.verify();
		} else if (InputWrapper.getFunctionOption().toLowerCase().equals("compute")) {
			System.err.println("COMPUTE");
			HMAC.compute();
		} else {
			System.err.println("Not valid option!");
			System.exit(1);
		}
	}
}
